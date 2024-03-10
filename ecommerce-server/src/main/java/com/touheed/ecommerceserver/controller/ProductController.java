package com.touheed.ecommerceserver.controller;


import java.awt.ImageCapabilities;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.exception.InvalidPriceException;
import com.touheed.ecommerceserver.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping(value = {"/add"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) throws InvalidPriceException, IOException
	{
//		return productService.addNewProduct(product);
		try {
			Set<ImageModel> images =  productService.uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
		} catch (InvalidPriceException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
