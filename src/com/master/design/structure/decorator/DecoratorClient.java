package com.master.design.structure.decorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 装饰者模式<br>
 * 装饰者模式在不必改变原类文件的情况下，动态扩展一个对象的功能。
 * 装饰者模式是通过创建一个包装对象来实现的，也就是用包装包裹真实对象
 * <br>
 * 一般来说，一个装饰器只实现一个功能，这样实现装饰器变得简单，也有利于装饰器的功能复用，可以给一个对象添加多个
 * 不一样的装饰器，也可以把一个装饰器用来装饰多个不同的对象
 *<p>
 * 装饰模式的应用可以参考{@link java.io.InputStream}及其子类
 * 其中，{@link java.io.InputStream}是Component, {@link java.io.FilterInputStream}是Decorator,
 * 其子类{@link java.io.BufferedInputStream}、{@link java.io.DataInputStream}等为Concrete Component
 * </p>
 */
public class DecoratorClient {

    public static void main(String[] args) {
        GoodSaleInterface goodSale = new AuthenticationDecorator(new LogDecorator(new GoodSaleImpl()));
        //GoodSaleInterface goodSale = new LogDecorator(new AuthenticationDecorator(new GoodSaleImpl()));
        SaleModel model1 = new SaleModel("香蕉", 5);
        SaleModel model2 = new SaleModel("苹果", 5);
        goodSale.sale("lily", model1);
        goodSale.sale("lucy", model2);
    }

}

//Component
interface GoodSaleInterface {
    boolean sale(String user, SaleModel model);
}

//Concrete Component
class GoodSaleImpl implements GoodSaleInterface {

    @Override
    public boolean sale(String user, SaleModel model) {
        //business logic here
        return true;
    }

}

//Decorator
abstract class Decorator implements GoodSaleInterface {

    protected GoodSaleInterface gsi;

    public Decorator(GoodSaleInterface gsi) {
        this.gsi = gsi;
    }

}

//Concrete Decorator
class AuthenticationDecorator extends Decorator {

    public AuthenticationDecorator(GoodSaleInterface gsi) {
        super(gsi);
    }

    @Override
    public boolean sale(String user, SaleModel model) {
        if("lily".equals(user)) {
            return this.gsi.sale(user, model);
        } else {
            System.out.println("对不起, " + user + " 没有权限");
            return false;
        }
    }

}

//Concrete Decorator
class LogDecorator extends Decorator {

    public LogDecorator(GoodSaleInterface gsi) {
        super(gsi);
    }

    @Override
    public boolean sale(String user, SaleModel model) {
        boolean flag = this.gsi.sale(user, model);
        if(flag) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()) + ": " + user + " 购买了 " + model);
        }
        return flag;
    }

}

class SaleModel {
    private String good; //商品明晨
    private int count; //商品数量

    public SaleModel(String good, int count) {
        this.good = good;
        this.count = count;
    }

    @Override
    public String toString() {
        return "SaleModel{" +
                "good='" + good + '\'' +
                ", count=" + count +
                '}';
    }
}