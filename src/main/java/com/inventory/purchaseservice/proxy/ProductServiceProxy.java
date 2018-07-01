package com.inventory.purchaseservice.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventory.purchaseservice.model.Product;

@FeignClient(name="product-service",url="localhost:8001")
public interface ProductServiceProxy {

	@GetMapping("/api/product/{itemName}")
	public ResponseEntity<Product> getProduct(@PathVariable("itemName") String itemName);
	
	@PostMapping("/api/product")
	public ResponseEntity<Product> save(@RequestBody Product product);
}
