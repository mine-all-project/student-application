package org.example.yangjichang.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.yangjichang.config.ApplicationException;
import org.example.yangjichang.config.SmsUtils;
import org.example.yangjichang.dao.*;
import org.example.yangjichang.entity.*;
import org.example.yangjichang.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${usePhone}")
    private boolean usePhone;
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final AudioFileRepository audioFileRepository;
    private final AnimalRepository animalRepository;
    private final MessageRepository messageRepository;
    private final PaperRepository paperRepository;
    private final OrderRepository orderRepository;
    private final SmsUtils smsUtils;

    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(AudioFileRepository audioFileRepository,
                              OrderRepository orderRepository,
                              MessageRepository messageRepository, SmsUtils smsUtils,
                              AnimalRepository animalRepository, PaperRepository paperRepository) {
        this.audioFileRepository = audioFileRepository;
        this.orderRepository = orderRepository;
        this.smsUtils = smsUtils;
        this.messageRepository = messageRepository;
        this.animalRepository = animalRepository;
        this.paperRepository = paperRepository;
    }

    @Override
    public List<Animal> getAnimalList() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> getAnimalListByType(String type) {
        return animalRepository.findAllByType(type);
    }

    @Override
    public Animal getAnimalById(String id) {
        return animalRepository.findById(id).orElse(new Animal());
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
        String path = getfilePath(request, filePath, virtualPath);
        AudioFile picture = audioFileRepository.findById(id).orElse(new AudioFile());
        picture.setUrl(path);
        return audioFileRepository.save(picture);
    }

    @Override
    @RequiresPermissions("manage")
    public AudioFile updateFile(HttpServletRequest request, AudioFile audioFile) {
        return audioFileRepository.save(audioFile);
    }

    @Override
    public List<AudioFile> getFileListByKeyWord(String keyWord) {
        return audioFileRepository.findAllByKeyWord(keyWord);
    }

    @Override
    @RequiresPermissions("manage")
    public void removeFileById(String id) {
        audioFileRepository.deleteById(id);
    }

    @Override
    @RequiresPermissions("manage")
    public AudioFile getFileById(String id) {
        return audioFileRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paper> getPapersByKeyWord(String keyWord) {
        return paperRepository.findAllByKeyWord(keyWord);
    }

    @Override
    public void savePaper(Paper paper) {
        paperRepository.save(paper);
    }

    @Override
    public Paper getPaperById(String id) {
        return paperRepository.findById(id).orElse(new Paper());
    }

    @Override
    public void removePaperById(String id) {
        paperRepository.deleteById(id);
    }


    @Override
    @RequiresPermissions("manage")
    public Map<String, String> uploadShopFile(HttpServletRequest request) {
        Map<String, String> path = new HashMap<>(1);
        path.put("path", getfilePath(request, filePath, virtualPath));
        return path;
    }

    @Override
    public Orders submitOrder(String shop) {
        return null;
    }

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
