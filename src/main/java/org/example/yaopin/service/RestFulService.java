package org.example.yaopin.service;

import org.example.yaopin.entity.*;
import org.example.yaopin.form.LinesForm;
import org.example.yaopin.form.PapersForm;
import org.example.yaopin.form.StandsForm;

import java.util.List;

public interface RestFulService extends BaseService{
    List<Linees> getLinesList();
    Linees getLinesById(String id);
    void saveLinesInfo(LinesForm form);
    void removeLinesById(String id);


    List<Stands> getStandsList();
    Stands getStandsById(String id);
    void saveStandsInfo(StandsForm form);
    void removeStandsById(String id);
    List<Linees> searchLinesByNumber(String name);
    List<Linees> searchLinesByStands(String name);




    List<Papers> getPapersByKeyWords(String keyWords);
    List<Papers> getMinePapersByKeyWords(String keyWords);
    Papers getPapersById(String id);
    void savePapers(PapersForm form);
    void removePapersById(String id);



//    Orders createOrder(Orders orders);
//    List<Orders> getOrdersList();
//    void removeOrdersById(String id);
//    List<Orders> getOrdersListByUser();

}
