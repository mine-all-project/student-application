package cn.crabapples.tuole.service.impl;

import cn.crabapples.tuole.dao.PictureRepository;
import cn.crabapples.tuole.entity.Picture;
import cn.crabapples.tuole.service.RestFulService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestFulServiceImpl implements RestFulService {
    private final PictureRepository pictureRepository;

    public RestFulServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> getPictures() {
        List<Picture> pictures = pictureRepository.findAllByKeyFlagOrderBySort("picture");
        return pictures;
    }
}
