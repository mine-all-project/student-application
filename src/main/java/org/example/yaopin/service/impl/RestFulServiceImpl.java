package org.example.yaopin.service.impl;


import org.example.yaopin.dao.*;
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
    private final SalesDAO salesDAO;

    public RestFulServiceImpl(PurchasesDAO purchasesDAO, SysUserDAO sysUserDAO, GoodsDAO goodsDAO, MessageDAO messageDAO, SalesDAO salesDAO) {
        this.purchasesDAO = purchasesDAO;
        this.messageDAO = messageDAO;
        this.sysUserDAO = sysUserDAO;
        this.goodsDAO = goodsDAO;
        this.salesDAO = salesDAO;
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

    @Override
    public List<Sales> getSalesList() {
        return salesDAO.getAll();
    }

}
