package org.example.yangjichang.service.impl;

import org.example.yangjichang.config.ApplicationException;
import org.example.yangjichang.config.SmsUtils;
import org.example.yangjichang.dao.AnimalRepository;
import org.example.yangjichang.dao.AudioFileRepository;
import org.example.yangjichang.dao.MessageRepository;
import org.example.yangjichang.dao.OrderRepository;
import org.example.yangjichang.entity.*;
import org.example.yangjichang.service.RestFulService;
import org.example.yangjichang.utils.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${usePhone}")
    private boolean usePhone;
    @Value("${filePath}")
    private String filePath;
    private final AudioFileRepository audioFileRepository;
    private final AnimalRepository animalRepository;
    private final OrderRepository orderRepository;
    private final MessageRepository messageRepository;
    private final SmsUtils smsUtils;
    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(AudioFileRepository audioFileRepository,
                              OrderRepository orderRepository,
                              MessageRepository messageRepository, SmsUtils smsUtils,
                              AnimalRepository animalRepository) {
        this.audioFileRepository = audioFileRepository;
        this.orderRepository = orderRepository;
        this.smsUtils = smsUtils;
        this.messageRepository = messageRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> getAnimalList(String type) {
        return animalRepository.findAllByType(type);
    }

    @Override
    public Animal getAnimalById(String id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            throw new ApplicationException("商品信息不存在");
        }
        return animal;
    }

    @Override
    @RequiresPermissions("manage")
    public void removeAnimalById(String id) {
        animalRepository.deleteById(id);
    }

    @Override
    @RequiresPermissions("manage")
    public void saveAnimalInfo(Animal animal) {
        animalRepository.saveAndFlush(animal);
    }

    @Override
    @RequiresPermissions("manage")
    public AudioFile uploadFile(HttpServletRequest request, String id) {
        String path = getfilePath(request,filePath);
        AudioFile picture = audioFileRepository.findById(id).orElse(new AudioFile());
        picture.setUrl(path);
        return audioFileRepository.save(picture);
    }

    @Override
    @RequiresPermissions("manage")
    public AudioFile saveAudioFile(HttpServletRequest request, AudioFile audioFile, String id) {
        if (request instanceof MultipartHttpServletRequest) {
            String path = getfilePath(request,filePath);
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
    @RequiresPermissions("manage")
    public Map<String, String> uploadShopFile(HttpServletRequest request) {
        Map<String, String> path = new HashMap<>(1);
        path.put("path", getfilePath(request,filePath));
        return path;
    }

    @Override
    public Orders submitOrder(String shop) {
        return null;
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
    @RequiresPermissions("manage")
    public void removeAudioFileById(String id) {
        audioFileRepository.deleteById(id);
    }


//    @Override
//    public List<Goods> getGoodsList(String keyword) {
//        return goodsRepository.findAllByKeyWord(keyword);
//    }
//
//
//    @Override
//    @RequiresPermissions("login")
//    public Orders submitOrder(String goodsId) {
//        Goods goods = goodsRepository.findById(goodsId).orElse(null);
//        if (goods == null) {
//            throw new ApplicationException("商品信息异常");
//        }
//        SysUser sysUser = getUser();
//        Orders orders = new Orders();
//        List<Goods> goodsList = new ArrayList<>();
//        goodsList.add(goods);
//        orders.setGoods(goodsList);
//        orders.setSysUser(sysUser);
//        orders.setKeyword(goods.getKeyWord());
//        List<Orders> exist = orderRepository.findAllBySysUserAndOrderTime(sysUser, LocalDate.now());
//        exist.forEach(e -> {
//            if ("tickets".equals(e.getKeyword())) {
//                throw new ApplicationException("每个用户每日只能购买一张门票");
//            }
//        });
//        try {
//            if (usePhone) {
//                smsUtils.sendNoticeMessage(sysUser.getPhone(), sysUser.getName(), goods.getName());
//            } else {
//                String title = "通知邮件";
//                String content = String.format("亲爱的 [%s] ,您的 [%s] 已经预约成功", sysUser.getName(), goods.getName());
//                MailUtils.sendMail(title, content, sysUser.getMail());
//            }
//            orderRepository.save(orders);
//            return orders;
//        } catch (Exception e) {
//            logger.warn("出现异常:[{}]\n", e.getMessage(), e);
//            throw new ApplicationException("通知邮件发送异常");
//        }
//    }

    @Override
    public List<Message> getMessages(Integer area) {
        return messageRepository.findAllByArea(area);
    }

    @Override
    @RequiresPermissions("login")
    public Message submitMessage(Message message) {
        SysUser sysUser = getUser();
        message.setUser(sysUser.getName());
        message.setLevel(1);
        return messageRepository.save(message);
    }


    @Override
    @RequiresPermissions("manage")
    public void removeMessageById(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    @Transactional
    @RequiresPermissions("manage")
    public void addMessage(Message message, String id) {
        Message parent = messageRepository.findById(id).orElse(null);
        if (parent == null) {
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


}
