package com.ibrplanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibrplanner.entities.Product;
import com.ibrplanner.repositories.ProductRepository;
import com.ibrplanner.utils.ResponseModel;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseModel responseModel;

    /* List */
    public Iterable<Product> list() {
	return productRepository.findAll();
    }

    /* Find Id */
    public ResponseEntity<?> findById(Long id) {

	Product result = productRepository.findById(id).get();
	return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    /* Insert/Update */
    public ResponseEntity<?> save(Product product, String action) {

	if (product.getName().equals("")) {
	    responseModel.setMessage("Nome obrigatório!");
	    return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
	} else if (product.getBrand().equals("")) {
	    responseModel.setMessage("Marca obrigatório!");
	    return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
	} else {

	    if (action.equals("insert")) {
		// Insert on Database
		return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
	    } else {
		// Update on Database
		return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.OK);
	    }
	}
    }

    /* Delete Product */
    public ResponseEntity<ResponseModel> delete(Long id) {
	productRepository.deleteById(id);
	responseModel.setMessage("Product deletado com sucesso!");
	return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

}
