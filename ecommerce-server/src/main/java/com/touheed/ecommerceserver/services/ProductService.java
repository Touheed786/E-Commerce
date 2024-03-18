package com.touheed.ecommerceserver.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.exception.ServerException;
import com.touheed.ecommerceserver.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<Product> addNewProduct(Product product) throws ServerException
	{
		
		if(product.getActaulPrice() != null && product.getActaulPrice().trim() != "")
		{
			if(!isValidPrice(product.getActaulPrice())) {
				throw new ServerException("Enter Proper Actual Price");
			}
		}
		
		if(product.getProductDiscountedPrice() != null && product.getProductDiscountedPrice().trim() != "")
		{
			if(!isValidPrice(product.getProductDiscountedPrice())) {
				throw new ServerException("Enter Properx Discounted Price");
			}
		}
		
		return ResponseEntity.ok(productRepository.save(product));
	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels = new HashSet<>();
		for(MultipartFile file:multipartFiles) {
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),file.getContentType(),file.getBytes()
					);
			imageModels.add(imageModel);
		}
		return imageModels;
	}
	
	public boolean isValidPrice(String price) {

		String pattern = "1234567890,. ";
		for (int i = 0; i < price.length(); i++) {
			if (pattern.indexOf(price.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	 
	 public List<Product> getAllProducts()
	 {
		 return productRepository.findAll();
	 }
	 
	 public int deleteProduct(Integer productId) throws ServerException {
		 List<Product> products = productRepository.findAll();
		 if(products != null) {
			 for(Product singleProd:products) {
				 if(singleProd.getProductId() == productId) {
					 Product product = productRepository.findById(productId).get();
					 productRepository.delete(product);
					 return product.getProductId();
				 }
			 }
		 }
		 throw new ServerException("Product is not Exists");
	 }
}
