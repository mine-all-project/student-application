package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.dao.*;
import cn.crabapples.tuole.entity.*;
import cn.crabapples.tuole.service.RestFulService;
import cn.crabapples.tuole.utils.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${filePath}")
    private String filePath;
    private final AudioFileRepository audioFileRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final SysUserRepository sysUserRepository;
    private final MessageRepository messageRepository;

    public RestFulServiceImpl(AudioFileRepository audioFileRepository, GoodsRepository goodsRepository, OrderRepository orderRepository, SysUserRepository sysUserRepository, MessageRepository messageRepository) {
        this.audioFileRepository = audioFileRepository;
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.sysUserRepository = sysUserRepository;
        this.messageRepository = messageRepository;
    }

    private String getfilePath(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath);
        return fileUtils.saveFile(multipartFile);
    }

    @Override
    public AudioFile uploadFile(HttpServletRequest request, String id) {
        String path = getfilePath(request);
        AudioFile picture = audioFileRepository.findById(id).orElse(null);
        if (null != picture) {
            picture.setUrl(path);
            audioFileRepository.save(picture);
            return audioFileRepository.save(picture);
        }
        throw new ApplicationException("文件上传失败");
    }

    @Override
    public Map<String, String> uploadShopFile(HttpServletRequest request) {
        Map<String, String> path = new HashMap<>(1);
        path.put("path", getfilePath(request));
        return path;
    }

    @Override
    public List<AudioFile> getPictures() {
        List<AudioFile> pictures = audioFileRepository.findAllByKeyWordOrderBySort("picture");
        for (int i = pictures.size(); i < 9; i++) {
            AudioFile picture = new AudioFile();
            picture.setId(UUID.randomUUID().toString().replace("-", ""));
            picture.setKeyWord("picture");
            picture.setSort(i);
            pictures.add(audioFileRepository.save(picture));
        }
        return pictures;
    }

    @Override
    public AudioFile saveAudioFile(HttpServletRequest request, AudioFile audioFile, String id) {
        if (request instanceof MultipartHttpServletRequest) {
            String path = getfilePath(request);
            AudioFile byId = audioFileRepository.findById(id).orElse(audioFile);
            if (null != byId) {
                byId.setUrl(path);
                return audioFileRepository.save(byId);
            }
            throw new ApplicationException("文件上传失败");
        } else {
            return audioFileRepository.save(audioFile);
        }
    }

    @Override
    public AudioFile getAudioFileByKeyWord(String keyWord) {
        List<AudioFile> audioFiles = audioFileRepository.findAllByKeyWord(keyWord);
        if (audioFiles != null && audioFiles.size() == 1) {
            return audioFiles.get(0);
        } else {
            AudioFile audioFile = new AudioFile();
            audioFile.setId(UUID.randomUUID().toString().replace("-", ""));
            audioFile.setKeyWord(keyWord);
            return audioFileRepository.save(audioFile);
        }
    }

    @Override
    public AudioFile getAudioFileById(String id) {
        AudioFile audioFile = audioFileRepository.findById(id).orElse(null);
        if (audioFile == null) {
            throw new ApplicationException("数据获取失败");
        }
        return audioFile;
    }

    @Override
    public void removeAudioFileById(String id) {
        audioFileRepository.deleteById(id);
    }

    @Override
    public List<AudioFile> getAudioFileListByKeyWord(String keyWord) {
        return audioFileRepository.findAllByKeyWordOrderByCreateTime(keyWord);
    }

    @Override
    public List<AudioFile> getAudioFileListNot(String keyWord, String id) {
        return audioFileRepository.findAllByKeyWordAndIdNotOrderByCreateTime(keyWord, id);
    }

    @Override
    public List<Goods> getGoodsList(String keyword) {
        return goodsRepository.findAllByKeyWord(keyword);
    }

    @Override
    public Goods getGoodsById(String id) {
        Goods goods = goodsRepository.findById(id).orElse(null);
        if (goods == null) {
            throw new ApplicationException("商品信息不存在");
        }
        return goods;
    }

    @Override
    public Goods saveGoodsInfo(Goods goods) {
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    public Orders submitOrder(String ticketsId) {
        Goods tickets = goodsRepository.findById(ticketsId).orElse(null);
        SysUser sysUser = getUser();
        Orders orders = new Orders();
        List<Goods> goods = new ArrayList<>();
        goods.add(tickets);
        orders.setGoods(goods);
        orders.setSysUser(sysUser);
        Orders exist = orderRepository.findAllBySysUserAndOrderTime(sysUser, LocalDate.now()).orElse(null);
        if (exist != null) {
            throw new ApplicationException("每个用户每日只能购买一张门票");
        }
        orderRepository.save(orders);
        return orders;
    }

    @Override
    public List<Message> getMessages(Integer area) {
        return messageRepository.findAllByArea(area);
    }

    @Override
    public Message submitMessage(Message message) {
        SysUser sysUser = getUser();
        message.setUser(sysUser.getName());
        message.setLevel(1);
        return messageRepository.save(message);
    }


    @Override
    public void removeMessageById(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMessage(Message message, String id) {
        Message parent = messageRepository.findById(id).orElse(null);
        if(parent == null){
            throw new ApplicationException("数据异常");
        }
        message.setUser(getUser().getName());
        message.setLevel(2);
        List<Message> children = parent.getChildren();
        children.add(message);
        parent.setChildren(children);
        messageRepository.saveAndFlush(message);
        messageRepository.saveAndFlush(parent);
    }

    private SysUser getUser(){
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        if (sysUser == null) {
            sysUser = sysUserRepository.findById("001").orElse(null);
        }
        return sysUser;
    }


}
