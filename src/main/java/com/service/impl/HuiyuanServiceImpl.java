package com.service.impl;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.HuiyuanDao;
import com.entity.HuiyuanEntity;
import com.service.HuiyuanService;
import com.entity.vo.HuiyuanVO;
import com.entity.view.HuiyuanView;

@Service("huiyuanService")
public class HuiyuanServiceImpl extends ServiceImpl<HuiyuanDao, HuiyuanEntity> implements HuiyuanService {

    @Override
    public void addJifen(HuiyuanEntity user) throws ParseException {
        Date lastLoginDate = user.getLastlogindate();
        LocalDate now = LocalDate.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (lastLoginDate != null) {
            String lastLoginDateStr = dateFormat.format(lastLoginDate);
            LocalDate localDate = LocalDate.parse(lastLoginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (localDate.isBefore(now)) {
                Long jifen = user.getJifen();
                if (null == jifen) {
                    jifen = 0L;
                }
                user.setJifen(jifen + 20);
                user.setLastlogindate(dateFormat.parse(nowStr));
            }
        }

        user.setLastlogindate(dateFormat.parse(nowStr));
        baseMapper.updateById(user);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HuiyuanEntity> page = this.selectPage(
                new Query<HuiyuanEntity>(params).getPage(),
                new EntityWrapper<HuiyuanEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<HuiyuanEntity> wrapper) {
        Page<HuiyuanView> page = new Query<HuiyuanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<HuiyuanVO> selectListVO(Wrapper<HuiyuanEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public HuiyuanVO selectVO(Wrapper<HuiyuanEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<HuiyuanView> selectListView(Wrapper<HuiyuanEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public HuiyuanView selectView(Wrapper<HuiyuanEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
