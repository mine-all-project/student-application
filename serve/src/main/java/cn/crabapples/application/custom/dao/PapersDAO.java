package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.elasticsearch.ElasticSearchUtils;
import cn.crabapples.application.custom.entity.Papers;
import cn.hutool.core.bean.BeanUtil;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 15:04
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
public class PapersDAO extends BaseDAO {
    private final String INDEX_NAME = "papers";
    private final ElasticSearchUtils elasticSearchUtils;

    public PapersDAO(ElasticSearchUtils elasticSearchUtils) {
        this.elasticSearchUtils = elasticSearchUtils;
    }

    public List<Map<String, Object>> getAll() {
        try {
            SearchHits hits = elasticSearchUtils.findAll(INDEX_NAME);
            List<Map<String, Object>> papersList = new ArrayList<>();
            hits.forEach(e -> {
                Map<String, Object> row = (Map<String, Object>) e.getSourceAsMap().get("papers");
                row.put("id", e.getId());
                papersList.add(row);
            });
            return papersList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ApplicationException("暂未实现");
    }

    public void removeById(String id) {
        System.err.println(id);
        throw new ApplicationException("暂未实现");
    }

    public void savePapers(Papers entity) {
        try {
            Map<String, Object> dataMap = BeanUtil.beanToMap(entity);
            System.err.println(dataMap.get("tagsList"));
            System.err.println(dataMap.get("fileList"));
            elasticSearchUtils.insert(INDEX_NAME, dataMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
