package org.example.yaopin.service.impl;

import org.example.yaopin.dao.GoodsDAO;
import org.example.yaopin.dao.MessageDAO;
import org.example.yaopin.dao.PurchasesDAO;
import org.example.yaopin.dao.SysUserDAO;
import org.example.yaopin.entity.*;
import org.example.yaopin.form.MessagesForm;
import org.example.yaopin.form.PurchasesForm;
import org.example.yaopin.service.RestFulService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestFulServiceImpl implements RestFulService {
    public SysUser getUserInfo() {
//        Subject subject = SecurityUtils.getSubject();
//        SysUser user = (SysUser) subject.getPrincipal();
//        String username = isDebug ? "user" : user.getUsername();
        return sysUserDAO.findByUsername("user");
    }

    @Value("${isDebug}")
    private boolean isDebug;
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final PurchasesDAO purchasesDAO;
    private final SysUserDAO sysUserDAO;
    private final GoodsDAO goodsDAO;
    private final MessageDAO messageDAO;

    public RestFulServiceImpl(PurchasesDAO purchasesDAO, SysUserDAO sysUserDAO, GoodsDAO goodsDAO, MessageDAO messageDAO) {
        this.purchasesDAO = purchasesDAO;
        this.messageDAO = messageDAO;
        this.sysUserDAO = sysUserDAO;
        this.goodsDAO = goodsDAO;
    }

    @Override
    public List<Purchases> getPurchasesList() {
        return purchasesDAO.findAll();
    }

    @Override
    public List<Purchases> getPurchasesListByStatus() {
        return purchasesDAO.getPurchasesListByStatus();
    }

    @Override
    public List<Purchases> getPurchasesListByStatusNot() {
        return purchasesDAO.getPurchasesListByStatusNot();
    }

    @Override
    public Purchases getPurchasesById(String id) {
        return purchasesDAO.findById(id);
    }

    @Override
    public void savePurchasesInfo(PurchasesForm form) {
        Purchases purchases = new Purchases();
        BeanUtils.copyProperties(form, purchases);
        Goods goods = purchases.getGoods();
        BeanUtils.copyProperties(purchases, goods);
        goods.setCounts(0L);
        purchases.setGoods(goodsDAO.saveData(goods));
        purchases.setStatus(0);
        purchasesDAO.saveData(purchases);
    }

    @Override
    public void flagDelPurchasesById(String id) {
        Goods goods = purchasesDAO.findById(id).getGoods();
        goods.setDelFlag(1);
        goodsDAO.saveData(goods);
        purchasesDAO.flagDelById(id);
    }

    @Override
    public List<Goods> getGoodsListByFlag() {
        return goodsDAO.getAllByFlag();
    }

    @Override
    public void addGoodsCountsById(String id) {
        Purchases purchases = purchasesDAO.findById(id);
        Goods goods = purchases.getGoods();
        goods.setCounts(purchases.getCounts());
        goodsDAO.saveData(goods);
        purchases.setStatus(1);
        purchasesDAO.saveData(purchases);
    }

    @Override
    public void reduceGoodsCountsById(String id) {
        Purchases purchases = purchasesDAO.findById(id);
        Goods goods = purchases.getGoods();
        goods.setCounts(0L);
        goodsDAO.saveData(goods);
        purchases.setStatus(0);
        purchasesDAO.saveData(purchases);
    }

    @Override
    public void saveMessages(MessagesForm form) {
        Messages messages = new Messages();
        BeanUtils.copyProperties(form, messages);
        messages.setFormAs(getUserInfo());
        messages.setStatus(0);
        messageDAO.saveData(messages);
    }

    @Override
    public List<Messages> getMessageList() {
        SysUser user = getUserInfo();
        String role = user.getRole();
        return messageDAO.findByToAsIn(role);
    }

    @Override
    public Messages getMessagesById(String id) {
        Messages messages = messageDAO.findById(id);
        String objectId = messages.getObjectId();
        switch (messages.getType()) {
            case 0: {
                Goods goods = goodsDAO.findDataById(objectId);
                messages.setContent(goods.getName() + "缺货");
                break;
            }
            case 1: {
                Goods goods = goodsDAO.findDataById(objectId);
                messages.setContent(goods.getName() + "报损");
                break;
            }
        }
        messages.setStatus(1);
        messageDAO.saveData(messages);
        return messages;
    }
//
//    @Override
//    public List<Stands> getStandsList() {
//        return standsDAO.findAll();
//    }
//
//    @Override
//    public Stands getStandsById(String id) {
//        return standsDAO.findById(id);
//    }
//
//    @Override
//    public void saveStandsInfo(StandsForm form) {
//        standsDAO.saveStands(form);
//    }
//

//
//    @Override
//    public List<Linees> searchLinesByNumber(String name) {
//        return linesDAO.findByNumber(name);
//    }
//
//    @Override
//    public List<Linees> searchLinesByStands(String name) {
//        List<Linees> source = linesDAO.findAll();
//        List<Linees> target = new ArrayList<>();
//        List<List<Boolean>> exists = linesDAO.findAll().stream().map(e -> e.getStandsList().stream().map(t -> t.getName().contains(name)).collect(Collectors.toList())).collect(Collectors.toList());
//        for (int i = 0; i < exists.size(); i++) {
//            for (int j = 0; j < exists.get(i).size(); j++) {
//                if (exists.get(i).get(j)) {
//                    target.add(source.get(i));
//                    break;
//                }
//            }
//        }
//        return target;
//    }
//
//    @Override
//    public List<Papers> getPapersByKeyWords(String keyWord) {
//        return papersDAO.findAllByKeyWords(keyWord);
//    }
//
//    @Override
//    public List<Papers> getMinePapersByKeyWords(String keyWord) {
//        SysUser user = getUserInfo();
//        return papersDAO.findByKeyWordsAndSysUser(user, keyWord);
//    }
//
//    @Override
//    public Papers getPapersById(String id) {
//        return papersDAO.findById(id);
//    }
//
//
//    @Override
//    public void savePapers(PapersForm form) {
//        Papers entity = form.toEntity();
//        entity.setSysUser(getUserInfo());
//        papersDAO.savePapers(entity);
//    }
//
//    @Override
//    public void removePapersById(String id) {
//        papersDAO.removePapersById(id);
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
