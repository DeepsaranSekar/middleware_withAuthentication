package com.nuance.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuance.middleware.model.Product;
import com.nuance.middleware.service.JsonKafkaProducer;
import com.nuance.middleware.service.ProductService;
import com.nuance.middleware.utility.RequestResponseLogger;


@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private final ProductService productService;
	private JsonKafkaProducer kafkaProducer;
	
	public ProductController(ProductService productService, JsonKafkaProducer kafkaProducer) {
		
		super();
		this.productService = productService;
		this.kafkaProducer = kafkaProducer;
	}

	@GetMapping("/list")
	public ResponseEntity getProduct(@RequestParam(value="sessionid",defaultValue ="") String sessionid,
									 @RequestParam(value="locale",required = false) String locale,
									 @RequestParam(value="channel",defaultValue ="default",required = false) String channel,
									 @RequestParam(value="language",required = false) String language,
									 @RequestParam(value="library",defaultValue ="default",required = false) String library,
									HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("Entered getProduct()in ProductController");
		kafkaProducer.sendMessage(RequestResponseLogger.logMessage("INFO", "GET request received for getProduct() ", ""));
		kafkaProducer.sendMessage(RequestResponseLogger.logRequestResponse(request, response,sessionid,locale,channel,language,library));
	   
		ResponseEntity entity = new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity updateProduct( @RequestParam(value="sessionid",defaultValue ="") String sessionid,
								 @RequestParam(value="locale",required = false) String locale,
								 @RequestParam(value="channel",defaultValue ="default",required = false) String channel,
								 @RequestParam(value="language",required = false) String language,
								 @RequestParam(value="library",defaultValue ="default",required = false) String library,
								 @PathVariable("id") String id, @RequestBody Product product, 
								 HttpServletRequest request, HttpServletResponse response) {
		

		kafkaProducer.sendMessage(RequestResponseLogger.logMessage("INFO", "PUT request received for updateProduct() with ID: ", id));
		kafkaProducer.sendMessage(RequestResponseLogger.logRequestResponse(request, response,sessionid,locale,channel,language,library));
		
		Product updatedProduct = productService.updateProduct(id, product);
		ResponseEntity entity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		return entity;
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteProduct(@RequestParam(value="sessionid",defaultValue ="") String sessionid,
								 @RequestParam(value="locale",required = false) String locale,
								 @RequestParam(value="channel",defaultValue ="default",required = false) String channel,
								 @RequestParam(value="language",required = false) String language,
								 @RequestParam(value="library",defaultValue ="default",required = false) String library,
								 @PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {


		kafkaProducer.sendMessage(RequestResponseLogger.logMessage("INFO", "DELETE request received for deleteProduct() with ID: ", id));
		kafkaProducer.sendMessage(RequestResponseLogger.logRequestResponse(request, response,sessionid,locale,channel,language,library));
		
		productService.deleteProduct(id);
		ResponseEntity entity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   
		return entity;
	}

	@PostMapping("/{id}")
	public ResponseEntity createProduct(@RequestParam(value="sessionid",defaultValue="") String sessionid,
						 @RequestParam(value="locale",required = false) String locale,
						 @RequestParam(value="channel",defaultValue ="default",required = false) String channel,
						 @RequestParam(value="language",required = false) String language,
						 @RequestParam(value="library",defaultValue ="default",required = false) String library,
						 @PathVariable("id") String id, @RequestBody Product product, 
						 HttpServletRequest request, HttpServletResponse response) {
		


		kafkaProducer.sendMessage(RequestResponseLogger.logMessage("INFO", "POST request received for createProduct() with ID: ", id));
		Product createdProduct = productService.createProduct(id, product);
		
		ResponseEntity entity = new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
		kafkaProducer.sendMessage(RequestResponseLogger.logRequestResponse(request, response,sessionid,locale,channel,language,library));
		
		return entity;
	}
	
	@GetMapping("/free")
	public ResponseEntity getProductFree(@RequestParam(value="sessionid",defaultValue ="") String sessionid,
			@RequestParam(value="locale",required = false) String locale,
			@RequestParam(value="channel",defaultValue ="default",required = false) String channel,
			@RequestParam(value="language",required = false) String language,
			@RequestParam(value="library",defaultValue ="default",required = false) String library,
			HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("Entered getProductFree()in ProductController");
		kafkaProducer.sendMessage(RequestResponseLogger.logMessage("INFO", "GET request received for getProductFree() ", ""));
		kafkaProducer.sendMessage(RequestResponseLogger.logRequestResponse(request, response,sessionid,locale,channel,language,library));
		
		ResponseEntity entity = new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
		return entity;
	}
    
}