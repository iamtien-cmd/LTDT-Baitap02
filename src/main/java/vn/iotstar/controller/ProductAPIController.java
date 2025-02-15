package vn.iotstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entiry.Product;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.IcategoryService;
import vn.iotstar.service.IproductService;
@RestController
@RequestMapping("/v1/api/products")
public class ProductAPIController {
	   @Autowired
	    private IproductService productService;

	    // 2. Hiển thị tất cả sản phẩm theo từng danh mục
	    @GetMapping("/category/{categoryId}")
	    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
	        List<Product> products = productService.findByCategoryId(categoryId);
	        return ResponseEntity.ok(products);
	    }

	    // 3. Hiển thị 10 sản phẩm có số lượng bán nhiều nhất
	    @GetMapping("/top-selling")
	    public ResponseEntity<List<Product>> getTop10BestSellingProducts() {
	        List<Product> products = productService.findTop10BestSelling();
	        return ResponseEntity.ok(products);
	    }

	    // 4. Hiển thị 10 sản phẩm được tạo trong vòng 7 ngày
	    @GetMapping("/new-products")
	    public ResponseEntity<List<Product>> getTop10NewProducts() {
	        List<Product> products = productService.findTop10NewlyCreated();
	        return ResponseEntity.ok(products);
	    }
}
