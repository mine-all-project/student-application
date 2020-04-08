package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.dao.AudioFileRepository;
import cn.crabapples.tuole.dao.GoodsRepository;
import cn.crabapples.tuole.dao.OrderRepository;
import cn.crabapples.tuole.dao.SysUserRepository;
import cn.crabapples.tuole.entity.AudioFile;
import cn.crabapples.tuole.entity.Orders;
import cn.crabapples.tuole.entity.Goods;
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
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final SysUserRepository sysUserRepository;

    public RestFulServiceImpl(AudioFileRepository audioFileRepository, GoodsRepository goodsRepository, OrderRepository orderRepository, SysUserRepository sysUserRepository) {
        this.audioFileRepository = audioFileRepository;
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.sysUserRepository = sysUserRepository;
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
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        if (sysUser == null) {
            sysUser = sysUserRepository.findById("001").orElse(null);
        }
        Orders orders = new Orders();
        List<Goods> goods = new ArrayList<>();
        goods.add(tickets);
        orders.setGoods(goods);
        orders.setSysUser(sysUser);
        orders.setCreateTime(LocalDateTime.now());
        orders.setUpdateTime(LocalDateTime.now());
        orders.setOrderTime(LocalDate.now());
        Orders exist = orderRepository.findAllBySysUserAndOrderTime(sysUser, LocalDate.now()).orElse(null);
        if (exist != null) {
            throw new ApplicationException("每个用户每日只能购买一张门票");
        }
        orderRepository.save(orders);
        return orders;
    }
}
