package org.example.gongjiao.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.gongjiao.dao.LinesDAO;
import org.example.gongjiao.dao.PapersDAO;
import org.example.gongjiao.dao.StandsDAO;
import org.example.gongjiao.dao.SysUserDAO;
import org.example.gongjiao.dao.jpa.MessageRepository;
import org.example.gongjiao.entity.*;
import org.example.gongjiao.form.LinesForm;
import org.example.gongjiao.form.PapersForm;
import org.example.gongjiao.form.StandsForm;
import org.example.gongjiao.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestFulServiceImpl implements RestFulService {
    @Override
    public SysUser getUserInfo() {
        return sysUserDAO.findByUsername("user");
    }

    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final StandsDAO standsDAO;
    private final LinesDAO linesDAO;
    private final PapersDAO papersDAO;
    private final SysUserDAO sysUserDAO;
    private final MessageRepository messageRepository;

    private final Logger logger = LoggerFactory.getLogger(RestFulServiceImpl.class);

    public RestFulServiceImpl(StandsDAO standsDAO, LinesDAO linesDAO, PapersDAO papersDAO,
                              SysUserDAO sysUserDAO, MessageRepository messageRepository
    ) {
        this.standsDAO = standsDAO;
        this.linesDAO = linesDAO;
        this.papersDAO = papersDAO;
        this.sysUserDAO = sysUserDAO;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Linees> getLinesList() {
        return linesDAO.findAll();
    }

    @Override
    public Linees getLinesById(String id) {
        return linesDAO.findById(id);
    }

    @Override
    @RequiresPermissions("manage")
    public void saveLinesInfo(LinesForm form) {
        linesDAO.saveLines(form);
    }

    @Override
    @RequiresPermissions("manage")
    public void removeLinesById(String id) {
        linesDAO.removeLinesById(id);
    }


    @Override
    public List<Stands> getStandsList() {
        return standsDAO.findAll();
    }

    @Override
    public Stands getStandsById(String id) {
        return standsDAO.findById(id);
    }

    @Override
    public void saveStandsInfo(StandsForm form) {
        standsDAO.saveStands(form);
    }

    @Override
    @RequiresPermissions("manage")
    public void removeStandsById(String id) {
        standsDAO.removeStandsById(id);
    }

    @Override
    public List<Linees> searchLinesByNumber(String name) {
        return linesDAO.findByNumber(name);
    }

    @Override
    public List<Linees> searchLinesByStands(String name) {
        List<Linees> source = linesDAO.findAll();
        List<Linees> target = new ArrayList<>();
        List<List<Boolean>> exists = linesDAO.findAll().stream().map(e -> e.getStandsList().stream().map(t -> t.getName().contains(name)).collect(Collectors.toList())).collect(Collectors.toList());
        for (int i = 0; i < exists.size(); i++) {
            for (int j = 0; j < exists.get(i).size(); j++) {
                if (exists.get(i).get(j)) {
                    target.add(source.get(i));
                    break;
                }
            }
        }
        return target;
    }

    @Override
    public List<Papers> getPapersByKeyWords(String keyWord) {
        return papersDAO.findAllByKeyWords(keyWord);
    }

    @Override
    public List<Papers> getMinePapersByKeyWords(String keyWord) {
        SysUser user = getUserInfo();
        return papersDAO.findByKeyWordsAndSysUser(user, keyWord);
    }

    @Override
    public Papers getPapersById(String id) {
        return papersDAO.findById(id);
    }


    @Override
    public void savePapers(PapersForm form) {
        papersDAO.savePapers(form);
    }

    @Override
    public void removePapersById(String id) {
        papersDAO.removePapersById(id);
    }
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
