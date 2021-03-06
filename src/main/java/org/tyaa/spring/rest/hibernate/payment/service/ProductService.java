package org.tyaa.spring.rest.hibernate.payment.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.spring.rest.hibernate.payment.dao.CategoryHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.dao.ProductHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.entity.Product;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;
import org.tyaa.spring.rest.hibernate.payment.model.Cart;
import org.tyaa.spring.rest.hibernate.payment.model.CartItem;
import org.tyaa.spring.rest.hibernate.payment.model.ProductFilter;
import org.tyaa.spring.rest.hibernate.payment.model.ProductRequest;
import org.tyaa.spring.rest.hibernate.payment.model.ProductResponse;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductHibernateDAO productHibernateDAO;

    @Autowired
    private CategoryHibernateDAO categoryHibernateDAO;

    public ProductResponse create(ProductRequest productRequest) {

        Category category
                = categoryHibernateDAO.get(productRequest.getCategoryId());

        Product product
                = new Product(
                        productRequest.getTitle(),
                        productRequest.getDescription(),
                        productRequest.getPrice(),
                        productRequest.getQuantity(),
                        productRequest.getImage(),
                        category
                );

        productHibernateDAO.create(product);
        ProductResponse response = new ProductResponse();
        response.setStatus("success");
        response.setMessage(
                String.format("Product %s created", product.getName())
        );
        return response;
    }

    public ProductResponse delete(int productId) {

        Product product = productHibernateDAO.get(productId);
        productHibernateDAO.remove(product);
        ProductResponse response = new ProductResponse();
        response.setStatus("success");
        response.setMessage(
                String.format("Product %s deleted", product.getName())
        );
        return response;
    }

    public ProductResponse getAll() {

        ProductResponse response = new ProductResponse();
        List<Product> products = productHibernateDAO.getAll();
        response.setStatus("success");
        response.setData(products);
        return response;
    }

    public ProductResponse getFiltered(ProductFilter filter) {

        ProductResponse response = new ProductResponse();
        List<Product> products = productHibernateDAO.getFiltered(filter);
        response.setStatus("success");
        response.setData(products);
        return response;
    }
    
    public AbstractResponse<Product> get(int id) {

        AbstractResponse<Product> response = new AbstractResponse<Product>();
        Product product = productHibernateDAO.get(id);
        response.setStatus("success");
        response.setData(product);
        return response;
    }
    
    public AbstractResponse<List<CartItem>> getCartItems(Cart cart) {
        return new AbstractResponse<List<CartItem>>(){
            @Override
            public List<CartItem> getData() {
                return cart.getCartItems();
            }

            @Override
            public String getMessage() {
                return "Cart items";
            }

            @Override
            public String getStatus() {
                return "success";
            }
        };
    }
    
    public AbstractResponse<List<CartItem>> changeCartItemCount(
            Cart cart
            , int id
            , CartItem.Action action
    ) {
        
        CartItem currentCartItem = null;
        Product product = productHibernateDAO.get(id);

        List<CartItem> currentCartItemList =
            cart.getCartItems().stream().filter((i) -> {
                    return i.id == id;
                }).collect(Collectors.toList());
        if (currentCartItemList.size() > 0) {
            currentCartItem = currentCartItemList.get(0);
        } else {
            currentCartItem = new CartItem(id, product.getName(), 0);
            cart.getCartItems().add(currentCartItem);
        }
        if (action == CartItem.Action.ADD) {
            currentCartItem.count++;
        } else if (action == CartItem.Action.NEG) {
            currentCartItem.count--;
            if (currentCartItem.count <= 0) {
                cart.getCartItems().remove(currentCartItem);
            }
        } else if (action == CartItem.Action.REM) {
            cart.getCartItems().remove(currentCartItem);
        }
        
        return new AbstractResponse<List<CartItem>>(){
            @Override
            public List<CartItem> getData() {
                return cart.getCartItems();
            }

            @Override
            public String getMessage() {
                return "Cart items";
            }

            @Override
            public String getStatus() {
                return "success";
            }
        };
    }
}
