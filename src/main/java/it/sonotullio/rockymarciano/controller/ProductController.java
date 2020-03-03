package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Product;
import it.sonotullio.rockymarciano.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable String id) throws Exception {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new Exception("Not found: " + id);
        }
    }

    @GetMapping
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }
}
