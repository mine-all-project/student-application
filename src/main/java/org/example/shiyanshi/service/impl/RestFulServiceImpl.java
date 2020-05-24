package org.example.shiyanshi.service.impl;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.shiyanshi.config.ApplicationException;
import org.example.shiyanshi.dao.*;
import org.example.shiyanshi.entity.*;
import org.example.shiyanshi.form.LineUpsForm;
import org.example.shiyanshi.form.MachinesForm;
import org.example.shiyanshi.form.RoomsForm;
import org.example.shiyanshi.service.RestFulService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestFulServiceImpl implements RestFulService {
    public SysUser getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        String username;
        if (user == null) {
            if (isDebug) {
                username = "admin";
            } else {
                throw new ApplicationException("用户未登录");
            }
        } else {
            username = user.getUsername();
        }
        return sysUserDAO.findByUsername(username);
    }

    @Value("${isDebug}")
    private boolean isDebug;
    @Value("${filePath}")
    private String filePath;
    @Value("${virtualPath}")
    private String virtualPath;
    private final LineUpsDAO lineUpsDAO;
    private final SysUserDAO sysUserDAO;
    private final RoomsDAO roomsDAO;
    private final MachinesDAO machinesDAO;

    public RestFulServiceImpl(LineUpsDAO lineUpsDAO, SysUserDAO sysUserDAO,
                              RoomsDAO roomsDAO, MachinesDAO machinesDAO) {
        this.lineUpsDAO = lineUpsDAO;
        this.sysUserDAO = sysUserDAO;
        this.roomsDAO = roomsDAO;
        this.machinesDAO = machinesDAO;
    }

    @Override
    public List<LineUps> getLineUpsListByUser() {
        SysUser user = getUserInfo();
        return lineUpsDAO.findAllByUser(user);
    }

    @Override
    public void saveLineUps(LineUpsForm form) {
        SysUser user = getUserInfo();
        LineUps lineUps = new LineUps();
        BeanUtils.copyProperties(form, lineUps);
        Machines machines = new Machines();
        BeanUtils.copyProperties(lineUps.getMachines(), machines);
        if (machinesDAO.findById(machines.getId()).getStatus() == 1) {
            throw new ApplicationException("改设备已被预约");
        }
        machines.setStatus(1);
        machines.setLineCount(machines.getLineCount() + 1);
        machinesDAO.saveData(machines);
        lineUps.setMachines(machines);
        lineUps.setStatus(0);
        lineUps.setUser(user);
        lineUpsDAO.saveData(lineUps);
    }

    @Override
    public void closeLineUpsById(String id) {
        LineUps lineUps = lineUpsDAO.findById(id);
        Machines machines = lineUps.getMachines();
        machines.setStatus(0);
        machinesDAO.saveData(machines);
        lineUps.setMachines(machines);
        lineUps.setStatus(3);
        lineUpsDAO.saveData(lineUps);
    }

    @Override
    public void startLineUpsById(String id) {
        LineUps lineUps = lineUpsDAO.findById(id);
        Machines machines = lineUps.getMachines();
        int time = machines.getTime();
        machines.setUseCount(machines.getUseCount() + 1);
        lineUps.setStatus(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(time);
        lineUps.setReallyStartTime(startTime);
        lineUps.setReallyEndTime(endTime);
        lineUpsDAO.saveData(lineUps);
    }

    @Override
    public void endLineUpsById(String id) {
        LineUps lineUps = lineUpsDAO.findById(id);
        Machines machines = lineUps.getMachines();
        machines.setStatus(0);
        machinesDAO.saveData(machines);
        lineUps.setMachines(machines);
        lineUps.setStatus(2);
        lineUpsDAO.saveData(lineUps);
    }

    @Override
    public List<Rooms> getRoomsList() {
        return roomsDAO.getRoomsList();
    }

    @Override
    public void saveRoomsInfo(RoomsForm form) {
        Rooms rooms = new Rooms();
        BeanUtils.copyProperties(form, rooms);
        try {
            roomsDAO.saveData(rooms);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("选定的设备中有一个或多个设备已经归属于其他实验室，请检查后重试");
        }
    }

    @Override
    public void delRoomsById(String id) {
        roomsDAO.delById(id);
    }

    @Override
    public Rooms getRoomsById(String id) {
        return roomsDAO.findById(id);
    }

    @Override
    public Machines getMachinesById(String id) {
        return machinesDAO.findById(id);
    }

    @Override
    public List<Machines> getMachinesList() {
        return machinesDAO.findAll();
    }

    @Override
    public void saveMachinesInfo(MachinesForm form) {
        Machines machines = new Machines();
        machines.setLineCount(0);
        machines.setUseCount(0);
        BeanUtils.copyProperties(form, machines);
        machinesDAO.saveData(machines);
    }

    @Override
    public void delMachinesById(String id) {
        machinesDAO.delById(id);
    }

    //
//    @Override
//    public List<Purchases> getPurchasesListByStatusNot() {
//        return purchasesDAO.getPurchasesListByStatusNot();
//    }

//
//    @Override
//    public void flagDelPurchasesById(String id) {
//        Goods goods = purchasesDAO.findById(id).getGoods();
//        goods.setDelFlag(1);
//        goodsDAO.saveData(goods);
//        purchasesDAO.flagDelById(id);
//    }
//
//    @Override
//    public List<Goods> getGoodsListByFlag() {
//        return goodsDAO.getAllByFlag();
//    }

//
//    @Override
//    public void reduceGoodsCountsById(String id) {
//        Purchases purchases = purchasesDAO.findById(id);
//        Goods goods = purchases.getGoods();
//        goods.setCounts(0L);
//        goodsDAO.saveData(goods);
//        purchases.setStatus(0);
//        purchasesDAO.saveData(purchases);
//    }
//
//    @Override
//    public void saveMessages(MessagesForm form) {
//        Messages messages = new Messages();
//        BeanUtils.copyProperties(form, messages);
//        messages.setFormAs(getUserInfo());
//        messages.setStatus(0);
//        messageDAO.saveData(messages);
//    }
//
//    @Override
//    public List<Messages> getMessageList() {
//        SysUser user = getUserInfo();
//        String role = user.getRole();
//        return messageDAO.findByToAsIn(role);
//    }
//
//    @Override
//    public Messages getMessagesById(String id) {
//        Messages messages = messageDAO.findById(id);
//        String objectId = messages.getObjectId();
//        switch (messages.getType()) {
//            case 0: {
//                Goods goods = goodsDAO.findDataById(objectId);
//                messages.setContent(goods.getName() + "缺货");
//                break;
//            }
//            case 1: {
//                Goods goods = goodsDAO.findDataById(objectId);
//                messages.setContent(goods.getName() + "报损");
//                break;
//            }
//        }
//        messages.setStatus(1);
//        messageDAO.saveData(messages);
//        return messages;
//    }
//
//    @Override
//    public List<Sales> getSalesList() {
//        return salesDAO.getAll();
//    }
//
//    @Override
//    public void addSalesInfo(SalesForm form) {
//        Goods goods = goodsDAO.findDataById(form.getGoodsId());
//        goods.setCounts(goods.getCounts() - form.getCounts());
//        Sales sales = new Sales();
//        BeanUtils.copyProperties(form, sales);
//        sales.setTempCount(goods.getCounts());
//        sales.setGoods(goods);
//        sales.setStatus(0);
//        salesDAO.saveData(sales);
//    }
//
//    @Override
//    public void reduceSalesInfo(String id) {
//        Sales sales = salesDAO.findById(id);
//        Goods goods = sales.getGoods();
//        goods.setCounts(goods.getCounts() + sales.getCounts());
//        goodsDAO.saveData(goods);
//        sales.setGoods(goods);
//        sales.setStatus(1);
//        salesDAO.saveData(sales);
//    }
}
