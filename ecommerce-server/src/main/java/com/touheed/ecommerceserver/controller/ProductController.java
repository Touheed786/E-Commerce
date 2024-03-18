package com.touheed.ecommerceserver.controller;


import java.awt.ImageCapabilities;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.exception.ServerException;
import com.touheed.ecommerceserver.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping(value = {"/add"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) throws ServerException, IOException
	{
//		return productService.addNewProduct(product);
		try {
			Set<ImageModel> images =  productService.uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
		} catch (ServerException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping({"/getAllProducts"})
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@DeleteMapping("/{id}")
	public int deleteProduct(@PathVariable("id") Integer id) throws ServerException {
		return productService.deleteProduct(id);
	}
	
	

}
