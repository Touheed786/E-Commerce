package com.touheed.ecommerceserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product addNewProduct(Product product)
	{
		return productRepository.save(product);
	}
}
