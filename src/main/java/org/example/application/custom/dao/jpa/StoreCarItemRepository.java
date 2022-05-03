package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.StoreCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCarItemRepository extends JpaRepository<StoreCar.StoreCarItem, String> {

}
