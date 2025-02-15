package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import vn.iotstar.entiry.Category;

@Service
public interface IcategoryService {
	
	Optional<Category> findByName(String categoryname);
	Optional<Category> findById(Long cateid);
	Category save(Category cate);
	void delete(Category category);
	Object findAll();
}
