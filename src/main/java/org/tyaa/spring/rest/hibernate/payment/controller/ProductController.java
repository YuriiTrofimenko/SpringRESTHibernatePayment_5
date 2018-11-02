package org.tyaa.spring.rest.hibernate.payment.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.spring.rest.hibernate.payment.entity.Product;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;
import org.tyaa.spring.rest.hibernate.payment.model.Cart;
import org.tyaa.spring.rest.hibernate.payment.model.CartItem;
import org.tyaa.spring.rest.hibernate.payment.model.ProductFilter;
import org.tyaa.spring.rest.hibernate.payment.model.ProductRequest;
import org.tyaa.spring.rest.hibernate.payment.model.ProductResponse;
import org.tyaa.spring.rest.hibernate.payment.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

        @Autowired
        private HttpSession httpSession;
        
	@Autowired
	private ProductService productService;

        /* Product Actions */
        
	@PostMapping("/create")
	public ProductResponse create(@RequestBody ProductRequest productRequest) {
            return productService.create(productRequest);
	}
        
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ProductResponse delete(@PathVariable("id") int id) {
            return productService.delete(id);
	}

	@GetMapping("/get-all")
	public ProductResponse getAll() {
            return productService.getAll();
	}
        
        @GetMapping("/get-filtered")
	public ProductResponse getFiltered(@RequestBody ProductFilter filter) {
            return productService.getFiltered(filter);
	}
        
        /* Cart Actions */
        
        @GetMapping("/cart/get-all")
	public AbstractResponse<List<CartItem>> getCartItems() {
            return productService.getCartItems(
                    (Cart) httpSession
                            .getAttribute("CART")
            );
	}
        
        @GetMapping("/cart/add/{id}")
	public AbstractResponse<List<CartItem>> addCartItemCount(@PathVariable("id") int id) {
            
            Cart cart =
                (Cart) httpSession.getAttribute("CART");
            CartItem currentCartItem = null;
            Product product = productService.get(id);
            
            List<CartItem> currentCartItemList =
                cart.getCartItems().stream().filter((i) -> {
                        return i.id == id;
                    }).collect(Collectors.toList());
            if (currentCartItemList.size() > 0) {
                currentCartItem = currentCartItemList.get(0);
            } else {
                currentCartItem = new CartItem(id, name, id);
            }
            return productService.getCartItems(
                    (Cart) httpSession
                            .getAttribute("CART")
            );
	}
}
