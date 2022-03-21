package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.News;
import org.example.application.custom.form.NewsForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NewsService extends BaseService {
    List<News> getAll();

    News save(HttpServletRequest request,NewsForm form);

    List<News> search(String keywords);

    List<News> getListByMine(HttpServletRequest request);

    void deleteById(String id);

    void checkPass(NewsForm form);

    void checkFail(NewsForm form);
}
