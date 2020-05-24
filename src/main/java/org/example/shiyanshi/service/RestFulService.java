package org.example.shiyanshi.service;


import org.example.shiyanshi.config.base.BaseService;
import org.example.shiyanshi.entity.LineUps;
import org.example.shiyanshi.entity.Machines;
import org.example.shiyanshi.entity.Rooms;
import org.example.shiyanshi.form.LineUpsForm;
import org.example.shiyanshi.form.MachinesForm;
import org.example.shiyanshi.form.RoomsForm;

import java.util.List;


public interface RestFulService extends BaseService {
    List<LineUps> getLineUpsListByUser();
    void saveLineUps(LineUpsForm form);

    List<Rooms> getRoomsList();
    void saveRoomsInfo(RoomsForm form);
    void delRoomsById(String id);
    Rooms getRoomsById(String id);


    Machines getMachinesById(String id);
    List<Machines> getMachinesList();
    void saveMachinesInfo(MachinesForm form);
    void delMachinesById(String id);



//    List<Purchases> getPurchasesListByStatus();
//    List<Purchases> getPurchasesListByStatusNot();
//    Purchases getPurchasesById(String id);
//    void savePurchasesInfo(PurchasesForm form);
//    void flagDelPurchasesById(String id);
//
//    List<Goods> getGoodsListByFlag();
//    void addGoodsCountsById(String id);
//    void reduceGoodsCountsById(String id);
//
//
//    void saveMessages(MessagesForm form);
//    List<Messages> getMessageList();
//    Messages getMessagesById(String id);
//
//    List<Sales> getSalesList();
//    void addSalesInfo(SalesForm form);
//    void reduceSalesInfo(String id);
}
