package com.ibrplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrplanner.entities.Product;
import com.ibrplanner.services.ProductService;
import com.ibrplanner.utils.ResponseModel;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String StatusAPI() {
	return "Product API is running...";
    }

    @GetMapping(value = "/list")
    public Iterable<Product> list() {

	return productService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
	return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Product product) {
	return productService.save(product, "insert");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product product) {
	return productService.save(product, "update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
	return productService.delete(id);
    }

}