package org.example.fangwuzulin.service.impl;

import org.example.fangwuzulin.config.ApplicationException;
import org.example.fangwuzulin.entity.*;
import org.example.fangwuzulin.form.HousesForm;
import org.example.fangwuzulin.mapping.AudioFilesMapping;
import org.example.fangwuzulin.mapping.HousesMapping;
import org.example.fangwuzulin.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${usePhone}")
    private boolean usePhone;
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final HousesMapping housesMapping;
    private final AudioFilesMapping audioFilesMapping;
//    private final MessageRepository messageRepository;
//    private final PaperRepository paperRepository;
//    private final OrderRepository orderRepository;

    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(HousesMapping housesMapping,
                              AudioFilesMapping audioFilesMapping
//                              MessageRepository messageRepository,
//                              AnimalRepository animalRepository, PaperRepository paperRepository
    ) {
        this.housesMapping = housesMapping;
        this.audioFilesMapping = audioFilesMapping;
//        this.messageRepository = messageRepository;
//        this.animalRepository = animalRepository;
//        this.paperRepository = paperRepository;
    }

    @Override
    public List<Houses> getHousesList() {
        return housesMapping.findAll();
    }

    @Override
    public List<Houses> getHousesListByName(String name) {
        return housesMapping.findAllByName(name);
    }

    @Override
    public Houses getHousesById(String id) {
        return housesMapping.getHousesById(id);
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
//        houses.setUser_id(getUser().getId());
        houses.setUser_id("001");
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

    //
//    @Override
//    public List<Animal> getAnimalListByType(String type) {
//        return animalRepository.findAllByType(type);
//    }
//
//    @Override
//    public Animal getAnimalById(String id) {
//        return animalRepository.findById(id).orElse(new Animal());
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public void removeAnimalById(String id) {
//        animalRepository.deleteById(id);
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public void saveAnimalInfo(Animal animal) {
//        animalRepository.saveAndFlush(animal);
//    }
//

//
//    @Override
//    @RequiresPermissions("manage")
//    public AudioFile updateFile(HttpServletRequest request, AudioFile audioFile) {
//        return audioFileRepository.save(audioFile);
//    }
//
//    @Override
//    public List<AudioFile> getFileListByKeyWord(String keyWord) {
//        return audioFileRepository.findAllByKeyWord(keyWord);
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public void removeFileById(String id) {
//        audioFileRepository.deleteById(id);
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public AudioFile getFileById(String id) {
//        return audioFileRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Paper> getPapersByKeyWord(String keyWord) {
//        return paperRepository.findAllByKeyWord(keyWord);
//    }
//
//    @Override
//    public void savePaper(Paper paper) {
//        paperRepository.save(paper);
//    }
//
//    @Override
//    public Paper getPaperById(String id) {
//        return paperRepository.findById(id).orElse(new Paper());
//    }
//
//    @Override
//    public void removePaperById(String id) {
//        paperRepository.deleteById(id);
//    }
//
//    @Override
//    @RequiresPermissions("login")
//    public Orders createOrder(Orders orders) {
//        orders.setSysUser(getUser());
//        String orderNumber = String.valueOf(System.currentTimeMillis());
//        orders.setOrderNumber(orderNumber);
//        Animal animal = animalRepository.findById(orders.getGoodsId()).orElse(null);
//        if (animal != null) {
//            int remaining = animal.getRemaining() - orders.getCounts();
//            if (remaining < 0) {
//                throw new ApplicationException("超过购买限制");
//            }
//            animal.setRemaining(remaining);
//            orders.setAnimals(animalRepository.save(animal));
//            return orderRepository.save(orders);
//        }
//        throw new ApplicationException("商品状态异常");
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public List<Orders> getOrdersList() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    @RequiresPermissions("manage")
//    public void removeOrdersById(String id) {
//        orderRepository.deleteById(id);
//    }
//
//    @Override
//    @RequiresPermissions("login")
//    public List<Orders> getOrdersListByUser() {
//        SysUser sysUser = getUser();
//        return orderRepository.findAll().stream().filter(e -> sysUser.getId().equals(e.getSysUser().getId())).collect(Collectors.toList());
//    }
}
