package vn.iotstar.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iotstar.entiry.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	 // Hiển thị tất cả sản phẩm theo từng danh mục
	 // do CategoryId trong Category là khóa chính, khi gọi thuộc tính phải viết hoa chữ cái đầu 
	  List<Product> findByCategory_CategoryId(Long categoryId);

    // Hiển thị 10 sản phẩm có số lượng bán nhiều nhất
    List<Product> findTop10ByOrderBySoldCountDesc();

    // Hiển thị 10 sản phẩm được tạo trong vòng 7 ngày
    @Query("SELECT p FROM Product p WHERE p.createdAt >= :date")
    List<Product> findTop10ByCreatedAtAfter(@Param("date") LocalDateTime date);
}
