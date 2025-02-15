package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.iotstar.entiry.Category;
import vn.iotstar.entiry.Product;

@Service
public interface IproductService {
	List<Product> findByCategoryId(Long categoryId);

	List<Product> findTop10BestSelling();

	List<Product> findTop10NewlyCreated();
}
