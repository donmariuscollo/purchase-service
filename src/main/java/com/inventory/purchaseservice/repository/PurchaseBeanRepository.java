package com.inventory.purchaseservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.purchaseservice.model.PurchaseBean;

@Repository
public interface PurchaseBeanRepository extends CrudRepository<PurchaseBean,Long>{
	
}
