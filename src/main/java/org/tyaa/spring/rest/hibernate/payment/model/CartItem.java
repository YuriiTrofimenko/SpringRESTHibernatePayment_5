/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.spring.rest.hibernate.payment.model;

/**
 *
 * @author tyaa
 */
public class CartItem {
    public int id;
    public String name;
    public int count;

    public CartItem(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
