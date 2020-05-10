package org.example.fangwuzulin.service;

import org.example.fangwuzulin.entity.*;
import org.example.fangwuzulin.form.ContractForm;
import org.example.fangwuzulin.form.HousesForm;
import org.example.fangwuzulin.form.LeaveMessageForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RestFulService extends BaseService{
    List<Houses> getHousesList();

    List<Houses> getHousesListByTitle(String title);

    Houses getHousesById(String id);

    List<Houses> getHousesByUser();

    void removeHousesById(String id);

    void saveHousesInfo(HousesForm form);

    List<Contracts> getContractsList();

    Contracts getContractsById(String id);

    void removeContractsById(String id);

    void saveContractInfo(ContractForm form);


    AudioFiles uploadFile(HttpServletRequest request);

    void saveLeaveMessage(LeaveMessageForm form);

    List<LeaveMessage> getLeaveMessage();

    void removeLeaveMessage(String id);
}
