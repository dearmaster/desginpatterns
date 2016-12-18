package com.master.design.behavior.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式又叫发表-订阅模式(publish-subscribe)、模型-视图模式(model-view)、源-收听者模式(source-listener)
 * 或者从属模式(dependents)
 * <br>
 * 观察者模式定义了一个一对多的依赖关系，让一个或者多个观察者对象监察一个主题对象。这样，一个主题
 * 对象在状态上的变化能够通知所有依赖于次对象的哪些观察者对象，使这些观察者对象能够自动更新
 * <br><br>
 *  Java中提供了{@link Observable}和{@link Observer}接口来实现所谓的观察者模式
 *  实现Observable接口的类允许在自身发生改变时，通知其他对象（实现Observer接口的观察者）做出相应操作
 *  <br><br>
 *  观察者模式使用了备忘录模式，将观察者对象存储在被观察对象中
 */
public class ObserverClient {
    public static void main(String[] args) {
        Watched watched1 = new Watched();
        Watched watched2 = new Watched();
        Watcher watcher = new Watcher(watched1);

        watched2.addObserver(watcher);

        watched1.changeData("Hello");
        watched1.changeData("How are you");
        watched1.changeData("Hi");

        watched2.changeData("1");
        watched2.changeData("2");
        watched2.changeData("3");
    }
}

/**
 * 被观察者
 */
class Watched extends Observable {

    private String data = "";

    public String retrieveData() {
        return this.data;
    }

    public void changeData(String data) {
        if(!this.data.equals(data)) {
            this.data = data;
            setChanged();
        }
        notifyObservers();
    }

}

/**
 * 观察者
 */
class Watcher implements Observer{

    public Watcher(Watched watched) {
        watched.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Data has been changed to: '" + ((Watched) o).retrieveData() + "'");
    }
}