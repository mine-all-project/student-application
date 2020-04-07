package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.dao.AudioFileRepository;
import cn.crabapples.tuole.dao.OrderRepository;
import cn.crabapples.tuole.dao.ShopRepository;
import cn.crabapples.tuole.entity.AudioFile;
import cn.crabapples.tuole.entity.Order;
import cn.crabapples.tuole.entity.Shop;
import cn.crabapples.tuole.entity.SysUser;
import cn.crabapples.tuole.service.RestFulService;
import cn.crabapples.tuole.utils.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Value("${filePath}")
    private String filePath;
    private final AudioFileRepository audioFileRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    public RestFulServiceImpl(AudioFileRepository audioFileRepository, ShopRepository shopRepository, OrderRepository orderRepository) {
        this.audioFileRepository = audioFileRepository;
        this.shopRepository = shopRepository;
        this.orderRepository = orderRepository;
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
            picture.setUpdateTime(LocalDateTime.now());
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
            picture.setCreateTime(LocalDateTime.now());
            picture.setUpdateTime(LocalDateTime.now());
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
                byId.setUpdateTime(LocalDateTime.now());
                return audioFileRepository.save(byId);
            }
            throw new ApplicationException("文件上传失败");
        } else {
            audioFile.setCreateTime(LocalDateTime.now());
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
            audioFile.setCreateTime(LocalDateTime.now());
            audioFile.setUpdateTime(LocalDateTime.now());
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
    public Shop getShopInfo(String keyword) {
        return shopRepository.findAllByKeyWord(keyword);
    }

    @Override
    public Shop saveShopInfo(Shop shop) {
        return shopRepository.saveAndFlush(shop);
    }

    @Override
    public Order submitOrder(Shop shop) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        Order order = new Order();
        List<Shop> shops = new ArrayList<>();
        shops.add(shop);
        order.setShops(shops);
        order.setSysUser(sysUser);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        Order order1 = orderRepository.findAllBySysUserNotAndCreateTimeNot(sysUser, LocalDate.now()).orElse(null);
        if (order1 == null) {
            orderRepository.save(order);
        }
        return order;
    }
}
