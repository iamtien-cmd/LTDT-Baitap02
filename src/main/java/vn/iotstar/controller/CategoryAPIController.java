package vn.iotstar.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.iotstar.entiry.Category;
import vn.iotstar.entiry.Response;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.IcategoryService;

@Controller
@RequestMapping("v1/api/category")
public class CategoryAPIController {
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private IcategoryService categoryService;
	@GetMapping("list")
	public ResponseEntity<?>getAllCategory(){
		return new ResponseEntity<Response>(new Response(true,"Thành công", categoryService.findAll()), HttpStatus.OK);
	}
	@RequestMapping("")// // 1. Hiển thị tất cả danh mục của đề tài
	@ResponseBody
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping("/get")
	public ResponseEntity<Optional<Category>> getCategory(
			@RequestParam(value = "name", required = false) String categoryname) {
		if (categoryname == null || categoryname.isEmpty()) {
			return ResponseEntity.badRequest().body(null); // Trả về lỗi nếu không có name
		}
		Optional<Category> category = categoryService.findByName(categoryname);
		if (category == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(category);
	}
	@GetMapping(path="/get3") //Tạo class Response.java để chứa các trường thông tin muốn tùy chỉnh khi response về client.
	public ResponseEntity<?>getCategory(@Validated @RequestParam("id") Long categoryid){
		Optional<Category> category = categoryService.findById(categoryid);
		if (category.isPresent()) {
			return new ResponseEntity<Response>(new Response(true,"Thành công", category.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Response>(new Response(false,"Thất bại",null), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public Category saveCate(@Valid @RequestBody Category cate) {
		return categoryService.save(cate);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryid,
			@Valid @RequestBody Category category) {
		Optional<Category> cate = categoryService.findById(categoryid);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		cate.get().setName(category.getName());
		Category updatedContact = categoryService.save(cate.get());
		return ResponseEntity.ok(updatedContact);
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Category>
	deleteCategory(@PathVariable(value = "id")
	Long categoryid) {
	Optional<Category> cate =
	categoryService.findById(categoryid);
	if(cate == null) {
	return ResponseEntity.notFound().build();
	}
	categoryService.delete(cate.get());
	return ResponseEntity.ok().build();
	}
}
