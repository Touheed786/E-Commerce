package com.touheed.ecommerceserver.controller;


import java.awt.ImageCapabilities;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@PostMapping(value = {"/add"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file)
	{
//		return productService.addNewProduct(product);
		try {
			Set<ImageModel> images =  productService.uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	

}
