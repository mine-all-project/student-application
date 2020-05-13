package org.example.yaopin.service;

import org.example.yaopin.entity.Goods;
import org.example.yaopin.entity.Messages;
import org.example.yaopin.entity.Purchases;
import org.example.yaopin.entity.Sales;
import org.example.yaopin.form.MessagesForm;
import org.example.yaopin.form.PurchasesForm;

import java.util.List;


public interface RestFulService extends BaseService{
    List<Purchases> getPurchasesList();
    List<Purchases> getPurchasesListByStatus();
    List<Purchases> getPurchasesListByStatusNot();
    Purchases getPurchasesById(String id);
    void savePurchasesInfo(PurchasesForm form);
    void flagDelPurchasesById(String id);

    List<Goods> getGoodsListByFlag();
    void addGoodsCountsById(String id);
    void reduceGoodsCountsById(String id);


    void saveMessages(MessagesForm form);
    List<Messages> getMessageList();
    Messages getMessagesById(String id);

    List<Sales> getSalesList();

}
