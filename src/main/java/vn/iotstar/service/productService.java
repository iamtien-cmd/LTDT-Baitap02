package vn.iotstar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entiry.Product;
import vn.iotstar.repository.ProductRepository;

@Service
public class productService implements IproductService {
	 @Autowired
	    private ProductRepository productRepository;

	    @Override
	    public List<Product> findByCategoryId(Long categoryId) {
	        return productRepository.findByCategory_CategoryId(categoryId);
	    }

	    @Override
	    public List<Product> findTop10BestSelling() {
	        return productRepository.findTop10ByOrderBySoldCountDesc();
	    }

	    @Override
	    public List<Product> findTop10NewlyCreated() {
	        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
	        return productRepository.findTop10ByCreatedAtAfter(sevenDaysAgo);
	    }
}
