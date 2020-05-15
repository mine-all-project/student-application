package org.example.waimai.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.waimai.config.ApplicationException;
import org.example.waimai.entity.*;
import org.example.waimai.form.ContractForm;
import org.example.waimai.form.HousesForm;
import org.example.waimai.form.LeaveMessageForm;
import org.example.waimai.mapping.*;
import org.example.waimai.service.IndexService;
import org.example.waimai.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${isDebug}")
    private boolean isDebug;

    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final IndexService indexService;
    private final HousesMapping housesMapping;
    private final AudioFilesMapping audioFilesMapping;
    private final LeaveMessageMapping leaveMessageMapping;
    private final SysUserMapping sysUserMapping;
    private final ContractsMapping contractsMapping;

    private final Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(IndexService indexService, HousesMapping housesMapping,
                              AudioFilesMapping audioFilesMapping,
                              LeaveMessageMapping leaveMessageMapping,
                              SysUserMapping sysUserMapping, ContractsMapping contractsMapping) {
        this.indexService = indexService;
        this.housesMapping = housesMapping;
        this.audioFilesMapping = audioFilesMapping;
        this.leaveMessageMapping = leaveMessageMapping;
        this.sysUserMapping = sysUserMapping;
        this.contractsMapping = contractsMapping;
    }

    private Houses setUserInfo(Houses houses) {
        houses.setUser(sysUserMapping.findById(houses.getUser_id()));
        return houses;
    }

    private Houses setContractInfo(Houses houses) {
        houses.setContracts(contractsMapping.findById(houses.getContract_id()));
        return houses;
    }

    private Houses setLeaveMessagesInfo(Houses houses) {
        List<LeaveMessage> messages = leaveMessageMapping.findAllByHousesId(houses.getId());
        messages = messages.stream().peek(e -> {
            e.setReturnMessage(leaveMessageMapping.findByParentId(e.getId()));
            e.setUser(sysUserMapping.findById(e.getUser_id()));
        }).collect(Collectors.toList());
        houses.setMessages(messages);
        return houses;
    }

    @Override
    public List<Houses> getHousesList() {
        return housesMapping.findAll().stream().peek(e -> {
            setUserInfo(e);
            setContractInfo(e);
            setLeaveMessagesInfo(e);
        }).collect(Collectors.toList());
    }


    @Override
    public List<Houses> getHousesListByTitle(String title) {
        return housesMapping.findAllByTitle(title).stream().peek(e -> {
            setUserInfo(e);
            setContractInfo(e);
            setLeaveMessagesInfo(e);
        }).collect(Collectors.toList());
    }

    @Override
    public Houses getHousesById(String id) {
        Houses houses = housesMapping.getHousesById(id);
        setUserInfo(houses);
        setContractInfo(houses);
        setLeaveMessagesInfo(houses);
        return houses;
    }

    @Override
    public List<Houses> getHousesByUser() {
        SysUser sysUser = indexService.getUserInfo();
        return housesMapping.findAllByUser(sysUser.getId()).stream().peek(e -> {
            setUserInfo(e);
            setContractInfo(e);
            setLeaveMessagesInfo(e);
        }).collect(Collectors.toList());
    }

    @Override
    public void removeHousesById(String id) {
        Integer count = housesMapping.removeHousesById(id);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public void saveHousesInfo(HousesForm form) {
        Houses houses = form.toEntity();
        houses.setUser_id(indexService.getUserInfo().getId());
        if (houses.getId() == null) {
            houses.setId(UUID.randomUUID().toString());
            Integer count = housesMapping.insertHousesInfo(houses);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        } else {
            Integer count = housesMapping.updateHousesInfo(houses);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        }
    }

    @Override
    public List<Contracts> getContractsList() {
        return contractsMapping.findAllContracts();
    }

    @Override
    public Contracts getContractsById(String id) {
        return contractsMapping.findById(id);
    }

    @Override
    public void removeContractsById(String id) {
        Integer count = contractsMapping.removeContractsById(id);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public void saveContractInfo(ContractForm form) {
        Contracts entity = form.toEntity();
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
            Integer count = contractsMapping.insertContracts(entity);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        } else {
            Integer count = contractsMapping.updateContracts(entity);
            if (count <= 0) {
                throw new ApplicationException("操作失败");
            }
        }
    }

    @Override
    public AudioFiles uploadFile(HttpServletRequest request) {
        String path = getFilePath(request, filePath, virtualPath);
        AudioFiles audioFiles = new AudioFiles();
        audioFiles.setId(UUID.randomUUID().toString());
        audioFiles.setUrl(path);
        Integer count = audioFilesMapping.insertAudioFiles(audioFiles);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
        return audioFiles;
    }

    @Override
    public void saveLeaveMessage(LeaveMessageForm form) {
        LeaveMessage leaveMessage = form.toEntity();
        leaveMessage.setUser_id(indexService.getUserInfo().getId());
        Integer count = leaveMessageMapping.insertMessage(leaveMessage);
        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }

    @Override
    public List<LeaveMessage> getLeaveMessage() {
        List<LeaveMessage> list = leaveMessageMapping.findAll();
        Map<String, String> userMap = sysUserMapping.findAllUser().stream().collect(Collectors.toMap(SysUser::getId, SysUser::getName));
        Map<String, String> houseMap = housesMapping.findAll().stream().collect(Collectors.toMap(Houses::getId, Houses::getTitle));
        List<LeaveMessage> removeList = new ArrayList<>();
        list.forEach(message -> {
            message.setUserName(userMap.get(message.getUser_id()));
            message.setHouses(houseMap.get(message.getHouses_id()));
            list.forEach(msg -> {
                if (StringUtils.equals(msg.getParent_id(), message.getId())) {
                    removeList.add(msg);
                    message.setReturnMessage(msg);
                }
            });
        });
        list.removeAll(removeList);
        return list;
    }

    @Override
    public void removeLeaveMessage(String id) {
        Integer count = leaveMessageMapping.removeById(id);
        leaveMessageMapping.removeByPid(id);

        if (count <= 0) {
            throw new ApplicationException("操作失败");
        }
    }
}
