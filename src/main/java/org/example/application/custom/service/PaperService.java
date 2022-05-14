package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Message;
import org.example.application.custom.entity.Paper;
import org.example.application.custom.form.PaperForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PaperService extends BaseService {
    List<Paper> getAll();
    List<Paper> getListByType(String type);

    List<Paper> search(String type, String keywords);

    Paper save(HttpServletRequest request, PaperForm form);

    void deleteById(String id);

    List<Paper> getMineAll(HttpServletRequest request);

    List<Paper> getMineListByType(HttpServletRequest request, String type);

    void check(PaperForm form);

    void addComment(HttpServletRequest request, Map<String, Object> comment);

    List<Message> getCommentList(HttpServletRequest request);

    void recomment(HttpServletRequest request,Message message);

    Paper getById(String id);
}
