package vn.iotstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entiry.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Override
	List<Category> findAll();
	Optional<Category> findByName(String name);
	Category save(Category cate);
	Optional<Category> findById(Long id);
	
}


