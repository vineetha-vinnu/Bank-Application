package com.lti.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.Exception.ResourceNotFoundException;
import com.lti.Service.ProductService;
import com.lti.model.Customer_Bank;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bank")
public class ProducerController {

	private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Autowired
	ProductService ProductService;

	@PostMapping("/customer")
	public Customer_Bank PostCustomer(@RequestBody Customer_Bank customer) {
		return ProductService.PostCustomer(customer);

	}

	@GetMapping("/getCustomer")
	public List<Customer_Bank> getCustomers() {

		logger.info("****************Contrller Class :get customers****************");
		return ProductService.getCustomers();
	}

	@GetMapping("/getCustomer/{id}")
	public Customer_Bank getCustomersByID(@PathVariable(value = "id") int custid) throws ResourceNotFoundException {
		// List<Customer> custlist = new ArrayList<Customer>();

		return ProductService.getCustomersByID(custid);
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCust(@PathVariable(value = "id") int custid) throws ResourceNotFoundException {

		return ProductService.deleteCust(custid);
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer_Bank> updateProduct(@PathVariable(value = "id") int custid,
			@RequestBody Customer_Bank custDetails) throws ResourceNotFoundException {

		return ProductService.updateCustomer(custid, custDetails);
	}
}
