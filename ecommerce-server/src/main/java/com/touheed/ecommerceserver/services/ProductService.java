package com.touheed.ecommerceserver.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
import com.touheed.ecommerceserver.entities.Product;
import com.touheed.ecommerceserver.exception.InvalidPriceException;
import com.touheed.ecommerceserver.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<Product> addNewProduct(Product product) throws InvalidPriceException
	{
		
		if(product.getActaulPrice() != null && product.getActaulPrice().trim() != "")
		{
			if(!isValidPrice(product.getActaulPrice())) {
				throw new InvalidPriceException("Enter Proper Actual Price");
			}
		}
		
		if(product.getProductDiscountedPrice() != null && product.getProductDiscountedPrice().trim() != "")
		{
			if(!isValidPrice(product.getProductDiscountedPrice())) {
				throw new InvalidPriceException("Enter Proper Discounted Price");
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
//	        return price.matches("^[-+]?\\d{1,3}(,\\d{3})*(\\.\\d+)?$");
		 try {
		        Double.parseDouble(price);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
	    }
}
