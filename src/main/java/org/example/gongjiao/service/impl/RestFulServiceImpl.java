package org.example.gongjiao.service.impl;

import org.example.gongjiao.dao.jpa.LineesRepository;
import org.example.gongjiao.dao.jpa.MessageRepository;
import org.example.gongjiao.dao.jpa.StandsRepository;
import org.example.gongjiao.entity.*;
import org.example.gongjiao.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final StandsRepository standsRepository;
    private final LineesRepository linesRepository;
    private final MessageRepository messageRepository;

    private Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(StandsRepository standsRepository,
                              MessageRepository messageRepository,
                              LineesRepository linesRepository) {
        this.standsRepository = standsRepository;
        this.messageRepository = messageRepository;
        this.linesRepository = linesRepository;
    }

    @Override
    public List<Linees> getLineesList() {
        return linesRepository.findAll();
    }

    @Override
    public List<Stands> getStandsList() {
        return standsRepository.findAll();
    }

    @Override
    public Linees getLineesById(String id) {
        return linesRepository.findById(id).orElse(new Linees());
    }
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
//    @Override
//    @RequiresPermissions("manage")
//    public AudioFile uploadFile(HttpServletRequest request, String id) {
//        String path = getfilePath(request, filePath, virtualPath);
//        AudioFile picture = audioFileRepository.findById(id).orElse(new AudioFile());
//        picture.setUrl(path);
//        return audioFileRepository.save(picture);
//    }
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
