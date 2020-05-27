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
    List<LineUps> getLineUpsList();
    List<LineUps> getLineUpsListByUser();
    void saveLineUps(LineUpsForm form);
    void closeLineUpsById(String id);
    void startLineUpsById(String id);
    void endLineUpsById(String id, String imgSrc);

    List<Rooms> getRoomsList();
    void saveRoomsInfo(RoomsForm form);
    void delRoomsById(String id);
    Rooms getRoomsById(String id);


    Machines getMachinesById(String id);
    List<Machines> getMachinesList();
    void saveMachinesInfo(MachinesForm form);
    void delMachinesById(String id);

}
