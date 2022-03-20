package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.LostItems;
import org.example.application.custom.form.LostItemsForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LostItemsService extends BaseService {
    List<LostItems> getAll();

    List<LostItems> search(String keywords);

    LostItems save(HttpServletRequest request, LostItemsForm form);

    List<LostItems> getListByMine(HttpServletRequest request);

    void deleteById(String id);

    void checkPass(LostItemsForm form);

    void checkFail(LostItemsForm form);
}
