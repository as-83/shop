package com.abdsul;

import com.abdsul.domain.Order;
import com.abdsul.domain.Thing;
import com.abdsul.ejb.OrdersManagerBean;
import com.abdsul.ejb.ThingsManagerBean;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private Order order;
    private  String name;
    private  int quantity;

    @EJB
    OrdersManagerBean ordersManagerBean;

    @EJB
    ThingsManagerBean thingsManagerBean;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public  void createOrder(){
        if(order==null)
            order = ordersManagerBean.createOrder();

    }
    public  void createThing(){
        thingsManagerBean.createThing(name, quantity);

    }

    public List<Thing> getThings(){
        return thingsManagerBean.getThings();
    }

    public  void  addThing(Thing thing){
        if(order == null)
            return;
        ordersManagerBean.addToOrder(thing.getId(), order.getId(), quantity);
    }

    public List<Thing> getThingsInOrder(){
        if(order==null)
            return  Collections.emptyList();
        return ordersManagerBean.getThingsInOrder(order.getId());
    }

}
