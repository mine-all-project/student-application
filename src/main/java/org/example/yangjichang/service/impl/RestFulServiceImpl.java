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
import java.util.List;
import java.util.stream.Collectors;

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
    @RequiresPermissions("login")
    public Orders createOrder(Orders orders) {
        orders.setSysUser(getUser());
        String orderNumber = String.valueOf(System.currentTimeMillis());
        orders.setOrderNumber(orderNumber);
        Animal animal = animalRepository.findById(orders.getGoodsId()).orElse(null);
        if (animal != null) {
            int remaining = animal.getRemaining() - orders.getCounts();
            if (remaining < 0) {
                throw new ApplicationException("超过购买限制");
            }
            animal.setRemaining(remaining);
            orders.setAnimals(animalRepository.save(animal));
            return orderRepository.save(orders);
        }
        throw new ApplicationException("商品状态异常");
    }

    @Override
    @RequiresPermissions("manage")
    public List<Orders> getOrdersList() {
        return orderRepository.findAll();
    }

    @Override
    @RequiresPermissions("manage")
    public void removeOrdersById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    @RequiresPermissions("login")
    public List<Orders> getOrdersListByUser() {
        SysUser sysUser = getUser();
        return orderRepository.findAll().stream().filter(e -> sysUser.getId().equals(e.getSysUser().getId())).collect(Collectors.toList());
    }
}
