package com.master.design.behavior.observer.extension;

import java.util.Observable;
import java.util.Observer;

/**
 * A demo of implement observer pattern
 */
public class ProductChangeObserverDemo {

    public static void main(String[] args) {
        Product product = new Product();
        Observer nameObserver = new NameObserver();
        Observer priceObserver = new PriceObserver();

        product.addObserver(nameObserver);
        product.addObserver(priceObserver);

        product.setName("Cake");
        product.setPrice(20.5f);
        product.setPrice(10.5f);
    }

}

class Product extends Observable {
    private String name;
    private float price;

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers(name);
    }

    public void setPrice(float price) {
        this.price = price;
        setChanged();
        notifyObservers(new Float(price));
    }

}

class NameObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            System.out.println("Name has been changed to: " + arg);
        }
    }
}

class PriceObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Float) {
            System.out.println("Price has been changed to: " + arg);
        }
    }
}