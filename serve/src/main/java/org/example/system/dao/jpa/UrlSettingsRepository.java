package org.example.system.dao.jpa;


import org.example.system.entity.UrlSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlSettingsRepository extends JpaRepository<UrlSettings, String> {
    UrlSettings findByKeyWords(String keywords);

    @Override
    <S extends UrlSettings> S saveAndFlush(S entity);
}
