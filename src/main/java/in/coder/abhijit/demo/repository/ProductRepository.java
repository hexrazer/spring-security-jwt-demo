package in.coder.abhijit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.coder.abhijit.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
