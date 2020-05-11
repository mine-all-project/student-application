package org.example.yaopin.dao;

import org.example.yaopin.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PapersDAO {
//    private final PapersRepository papersRepository;
//
//    public PapersDAO(PapersRepository papersRepository) {
//        this.papersRepository = papersRepository;
//    }
//
//    public List<Papers> findAllByKeyWords(String keyWord) {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return papersRepository.findByKeyWords(keyWord, sort);
//    }
//
//    public List<Papers> findByKeyWordsAndSysUser(SysUser user, String keyWord) {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return papersRepository.findByKeyWords(keyWord, sort).stream().filter(e -> e.getSysUser() != null && user.getId().equals(e.getSysUser().getId())).collect(Collectors.toList());
//    }
//
//    public Papers findById(String id) {
//        return papersRepository.findById(id).orElse(new Papers());
//    }
//
//    public void savePapers(Papers papers) {
//        papersRepository.saveAndFlush(papers);
//    }
//
//    public void removePapersById(String id) {
//        papersRepository.deleteById(id);
//    }


}
