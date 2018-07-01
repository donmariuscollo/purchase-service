package com.inventory.purchaseservice.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.inventory.purchaseservice.model.Product;
import com.inventory.purchaseservice.model.PurchaseBean;
import com.inventory.purchaseservice.proxy.ProductServiceProxy;
import com.inventory.purchaseservice.service.PurchaseBeanService;

@Produces("application/json")
@Consumes("application/json")
@RestController
public class PurchaseBeanController {
	
	
	@Autowired
	private PurchaseBeanService purchaseBeanService;	
		
	@PostMapping("/api/purchase")
	public ResponseEntity<PurchaseBean> save(@RequestBody PurchaseBean purchaseBean){
		
		String path="http://localhost:8001/api/product";
		String url=path+"/{itemName}";
		
		RestTemplate rest=new RestTemplate();
		ResponseEntity<Product> response=null;
		try{
			response=rest.getForEntity(url, Product.class,purchaseBean.getItemName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (!response.getStatusCode().equals(HttpStatus.FOUND)){
			return new ResponseEntity<PurchaseBean>(HttpStatus.BAD_REQUEST);
		}

		Product product=response.getBody();
		
		int totalQuantity=product.getQuantity()+purchaseBean.getQuantity();
		product.setQuantity(totalQuantity);
		
		//save product
		product=rest.postForObject(path,new HttpEntity<Product>(product), Product.class);
		
		//save purchase bean
		purchaseBean.setPurchasePrice(product.getCostPrice());
		purchaseBean.setStatus(1);
		PurchaseBean newPurchaseBean=purchaseBeanService.save(purchaseBean);		

		return new ResponseEntity<PurchaseBean>(newPurchaseBean,HttpStatus.CREATED);
		
	}
	
}
