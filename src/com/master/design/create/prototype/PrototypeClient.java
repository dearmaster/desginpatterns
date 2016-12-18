package com.master.design.create.prototype;

public class PrototypeClient {

    public static void main(String[] args) {
        Prototype prototypeA1 = new ConcretePrototypeA("Jack");
        Prototype prototypeA2 = null;
        try {
            prototypeA2 = prototypeA1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(prototypeA1 + ", " + prototypeA1.getName());
        System.out.println(prototypeA2 + ", " + prototypeA2.getName());
    }

}

abstract class Prototype implements Cloneable {
    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    protected abstract String getName();
}

class ConcretePrototypeA extends Prototype {

    private String name;

    public ConcretePrototypeA(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return super.clone();
    }
}