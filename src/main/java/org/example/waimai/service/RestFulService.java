package org.example.waimai.service;

import org.example.waimai.entity.*;
import org.example.waimai.form.ContractForm;
import org.example.waimai.form.HousesForm;
import org.example.waimai.form.LeaveMessageForm;

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
