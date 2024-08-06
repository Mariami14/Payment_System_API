package com.marie.paymentsystem.service;

import com.marie.paymentsystem.model.model.Product;
import com.marie.paymentsystem.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    //პროდუქტს ქმნის ადმინისტრატორი
    //გაწერა ველები : სახელი, მინ თანხა, მაქს თანხა
    // პროდუქტები მაქვს :
    //1. მობილური - ა) ბალანსის შევსება  (მინ თანხა -  2; მაქს -50ლ.) ; ბ)ინტერნეტის ყიდვა (მინ. თანხა -9ლ, მაქს -100ლ)
    //2.კომუნალურები - ა)დენი ,ბ) წყალი, გ)გაზი (მინ თანხა 2 ლარი, მაქს 2000 ლ.)
    //3. გადარიცხვა - ა)საკუთარ ანგარიშზე (მინ -1ლ, მაქს- 4000)
    //ბ) სხვასთან -(მინ-1ლ, მაქს 3000ლ) გ)ბიუჯეტში (მინ იყოს 10 ლარი, მაქს- 1000)
    // პროდუქტების ჩამონათვალი იქნება ბაზაში და ადმინისტრატორი აარჩევს რომელი გააქტიუროს?

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public boolean updateProduct(Product product) {
        Product oldProduct = productRepository.findById(product.getId()).orElse(null);
        oldProduct.setName(product.getName());
        oldProduct.setMinDeposit(product.getMinDeposit());
        oldProduct.setMaxDeposit(product.getMaxDeposit());
        return true;
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);

    }
}
