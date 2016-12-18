package com.master.design.create.factorymethod;

/**
 * 创建型
 * 工厂方法模式
 * 满足OCP
 */
public class FactoryMethodClient {

    private static FactoryMethodClient instance;
    private FruitFactory appleFactory, grapeFactory, strawberryFactory;

    public static void main(String[] args) {
        FactoryMethodClient client = getInstance();
        client.startBusiness();
    }

    private FactoryMethodClient() {
        appleFactory = new AppleFactoryImpl();
        grapeFactory = new GrapeFactoryImpl();
        strawberryFactory = new StrawberryFactoryImpl();
    }

    public static FactoryMethodClient getInstance() {
        if(null == instance) {
            synchronized (FactoryMethodClient.class) {
                if(null == instance) {
                    instance = new FactoryMethodClient();
                }
            }
        }
        return instance;
    }

    public void startBusiness() {
        Fruit apple, grape, strawberry;

        apple = appleFactory.getInstance();
        grape = grapeFactory.getInstance();
        strawberry = strawberryFactory.getInstance();

        apple.printCategory();
        grape.printCategory();
        strawberry.printCategory();
    }

}

interface Fruit {
    void printCategory();
}

interface FruitFactory {
    Fruit getInstance();
}

class AppleFactoryImpl implements FruitFactory {

    @Override
    public Fruit getInstance() {
        return new Apple();
    }

}

class GrapeFactoryImpl implements FruitFactory {

    @Override
    public Fruit getInstance() {
        return new Grape();
    }

}

class StrawberryFactoryImpl implements FruitFactory {

    @Override
    public Fruit getInstance() {
        return new Strawberry();
    }

}

class Apple implements Fruit {

    private final String name;

    public Apple() {
        this.name = "Apple";
    }

    @Override
    public void printCategory() {
        System.out.println(this.name);
    }

}

class Grape implements Fruit {

    private final String name;

    public Grape() {
        this.name = "Grape";
    }

    @Override
    public void printCategory() {
        System.out.println(this.name);
    }

}

class Strawberry implements Fruit {

    private final String name;

    public Strawberry() {
        this.name = "Strawberry";
    }

    @Override
    public void printCategory() {
        System.out.println(this.name);
    }

}