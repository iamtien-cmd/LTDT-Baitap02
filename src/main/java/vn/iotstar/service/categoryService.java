package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entiry.Category;
import vn.iotstar.repository.CategoryRepository;

@Service
public class categoryService implements IcategoryService {
	@Autowired
	public CategoryRepository repository;
	@Override
	public Optional<Category> findByName(String categoryname) {

		return repository.findByName(categoryname);
	}
	@Override
	public Optional<Category> findById(Long cateid) {

		return repository.findById(cateid);
	}
	@Override
	public Category save(Category cate) {
		return repository.save(cate);
	}
	@Override
	public void delete(Category category) {
		repository.delete(category);
	}
	@Override
	public Object findAll(){
		return repository.findAll();
	}


}
