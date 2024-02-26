package com.touheed.ecommerceserver.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.touheed.ecommerceserver.entities.ImageModel;
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
}
