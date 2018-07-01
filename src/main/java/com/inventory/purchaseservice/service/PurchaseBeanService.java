package com.inventory.purchaseservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.purchaseservice.model.PurchaseBean;
import com.inventory.purchaseservice.repository.PurchaseBeanRepository;

@Service
public class PurchaseBeanService {
	@Autowired
	PurchaseBeanRepository purchaseBeanRepository;
	
	public PurchaseBean save(PurchaseBean purchaseBean){		
		return purchaseBeanRepository.save(purchaseBean);
	}
	
	public List<PurchaseBean> getList(){
		return (List<PurchaseBean>) purchaseBeanRepository.findAll();
	}
	
	
}
