package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.config.MailUtils;
import cn.crabapples.tuole.config.SmsUtils;
import cn.crabapples.tuole.dao.AudioFileRepository;
import cn.crabapples.tuole.dao.GoodsRepository;
import cn.crabapples.tuole.dao.MessageRepository;
import cn.crabapples.tuole.dao.OrderRepository;
import cn.crabapples.tuole.entity.*;
import cn.crabapples.tuole.service.RestFulService;
import cn.crabapples.tuole.utils.FileUtils;
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
import java.time.LocalDate;
import java.util.*;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${usePhone}")
    private boolean usePhone;
    @Value("${filePath}")
    private String filePath;
    private final AudioFileRepository audioFileRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final MessageRepository messageRepository;
    private final SmsUtils smsUtils;
    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(AudioFileRepository audioFileRepository,
                              GoodsRepository goodsRepository, OrderRepository orderRepository,
                              MessageRepository messageRepository, SmsUtils smsUtils) {
        this.audioFileRepository = audioFileRepository;
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.smsUtils = smsUtils;
        this.messageRepository = messageRepository;
    }

    private String getfilePath(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath);
        return fileUtils.saveFile(multipartFile);
    }

    @Override
    @RequiresPermissions("manage")
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
    @RequiresPermissions("manage")
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
    @RequiresPermissions("manage")
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
    @RequiresPermissions("manage")
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
    @RequiresPermissions("manage")
    public Goods saveGoodsInfo(Goods goods) {
        return goodsRepository.saveAndFlush(goods);
    }

    @Override
    @RequiresPermissions("login")
    public Orders submitOrder(String goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElse(null);
        if (goods == null) {
            throw new ApplicationException("商品信息异常");
        }
        SysUser sysUser = getUser();
        Orders orders = new Orders();
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);
        orders.setGoods(goodsList);
        orders.setSysUser(sysUser);
        orders.setKeyword(goods.getKeyWord());
        List<Orders> exist = orderRepository.findAllBySysUserAndOrderTime(sysUser, LocalDate.now());
        exist.forEach(e -> {
            if ("tickets".equals(e.getKeyword())) {
                throw new ApplicationException("每个用户每日只能购买一张门票");
            }
        });
        try {
            if (usePhone) {
                smsUtils.sendNoticeMessage(sysUser.getPhone(), sysUser.getName(), goods.getName());
            } else {
                String title = "通知邮件";
                String content = String.format("亲爱的 [%s] ,您的 [%s] 已经预约成功", sysUser.getName(), goods.getName());
                MailUtils.sendMail(title, content, sysUser.getMail());
            }
            orderRepository.save(orders);
            return orders;
        } catch (Exception e) {
            logger.warn("出现异常:[{}]\n", e.getMessage(), e);
            throw new ApplicationException("通知邮件发送异常");
        }
    }

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

    private SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }


}
