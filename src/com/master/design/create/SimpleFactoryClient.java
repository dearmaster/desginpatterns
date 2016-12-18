package com.master.design.create;

/**
 * 简单工厂模式
 * 不满足OCP原则
 */
public class SimpleFactoryClient {

    public static void main(String[] args) {
        Fruit apple1, apple2, grape1, grape2;
        apple1 = FruitFactory.getFruit(1);
        apple2 = FruitFactory.getFruit(1);
        grape1 = FruitFactory.getFruit(2);
        grape2 = FruitFactory.getFruit(2);

        System.out.println(apple1);
        System.out.println(apple2);
        System.out.println(grape1);
        System.out.println(grape2);
    }

}

class FruitFactory {

    private static Grape grapeHolder;

    public static Fruit getFruit(int type) {
        Fruit fruit = null;
        if(type == 1) {
            fruit = new Apple();
        } else if(type ==2) {
            fruit =  getGrape();
        }
        return fruit;
    }

    private static Grape getGrape() {
        if(null == grapeHolder) {
            synchronized (FruitFactory.class) {
                if(null == grapeHolder) {
                    grapeHolder = new Grape();
                }
            }
        }
        return grapeHolder;
    }

}

interface Fruit {
}

class Apple implements Fruit {
}

class Grape implements Fruit {
}