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
        
        @PostMapping("/get-filtered")
	public ProductResponse getFiltered(@RequestBody ProductFilter filter) {
            return productService.getFiltered(filter);
	}
        
        /* Cart Actions */
        
        @GetMapping("/cart/get-all")
	public AbstractResponse<List<CartItem>> getCartItems() {
            Cart cart = (Cart) httpSession.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            return productService.getCartItems(cart);
	}
        
        @PostMapping("/cart/add/{id}")
	public AbstractResponse<List<CartItem>> addCartItemCount(@PathVariable("id") int id) {
            Cart cart = (Cart) httpSession.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            AbstractResponse<List<CartItem>> response =
                    productService.changeCartItemCount(
                    cart
                    , id
                    , CartItem.Action.ADD
            );
            httpSession.setAttribute("CART", cart);
            return response;
	}
        
        @PostMapping("/cart/neg/{id}")
	public AbstractResponse<List<CartItem>> negCartItemCount(@PathVariable("id") int id) {
            Cart cart = (Cart) httpSession.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            AbstractResponse<List<CartItem>> response =
                    productService.changeCartItemCount(
                    cart
                    , id
                    , CartItem.Action.NEG
            );
            httpSession.setAttribute("CART", cart);
            return response;
	}
        
        @RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.DELETE)
	public AbstractResponse<List<CartItem>> deleteCartItem(@PathVariable("id") int id) {
            Cart cart = (Cart) httpSession.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            AbstractResponse<List<CartItem>> response =
                    productService.changeCartItemCount(
                    cart
                    , id
                    , CartItem.Action.REM
            );
            httpSession.setAttribute("CART", cart);
            return response;
	}
}
