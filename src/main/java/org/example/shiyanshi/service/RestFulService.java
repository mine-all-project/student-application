package org.example.shiyanshi.service;

import org.example.shiyanshi.entity.Goods;
import org.example.shiyanshi.entity.Messages;
import org.example.shiyanshi.entity.Purchases;
import org.example.shiyanshi.entity.Sales;
import org.example.shiyanshi.form.MessagesForm;
import org.example.shiyanshi.form.PurchasesForm;
import org.example.shiyanshi.form.SalesForm;

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
    void addSalesInfo(SalesForm form);
    void reduceSalesInfo(String id);
}
