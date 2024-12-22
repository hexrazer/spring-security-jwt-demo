package in.coder.abhijit.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.coder.abhijit.demo.entity.Product;
import in.coder.abhijit.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product updateProduct(Long id,Product updatedProduct){
        Product existingProduct  = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }



    
}
