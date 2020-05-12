package org.example.yaopin.service;

import org.example.yaopin.entity.Purchases;
import org.example.yaopin.entity.Storage;
import org.example.yaopin.form.PurchasesForm;
import org.example.yaopin.form.StorageForm;

import java.util.List;


public interface RestFulService extends BaseService{
    List<Purchases> getPurchasesList();
    Purchases getPurchasesById(String id);
    void savePurchasesInfo(PurchasesForm form);
    void flagDelPurchasesById(String id);

    void saveStorageInfo(StorageForm form);

    List<Storage> getStorageList();

//
//
//    List<Stands> getStandsList();
//    Stands getStandsById(String id);
//    void saveStandsInfo(StandsForm form);
//    void removeStandsById(String id);
//    List<Linees> searchLinesByNumber(String name);
//    List<Linees> searchLinesByStands(String name);
//
//
//
//
//    List<Papers> getPapersByKeyWords(String keyWords);
//    List<Papers> getMinePapersByKeyWords(String keyWords);
//    Papers getPapersById(String id);
//    void savePapers(PapersForm form);
//    void removePapersById(String id);



//    Orders createOrder(Orders orders);
//    List<Orders> getOrdersList();
//    void removeOrdersById(String id);
//    List<Orders> getOrdersListByUser();

}
