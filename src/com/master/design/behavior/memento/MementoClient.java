package com.master.design.behavior.memento;

/**
 * 备忘录模式<br>
 * 备忘录模式是在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样
 * 以后就可以将该对象恢复到原先保存的状态
 * <br><br>
 * 备忘录模式是专门用来存放对象历史状态的，所以，在命令模式中，undo和redo功能可以配合备忘录模式来实现
 */
public class MementoClient {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("Off");
        caretaker.saveMemento(originator.createMemento());
        originator.setState("On");
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}

//白箱模式备忘录
//备忘录角色对任何对象都提供一个接口，即宽接口，通过该接口，备忘录角色内部所存储的状态就对所有对象公开。白箱想实现
//将发起人角色的状态存储在一个大家都看得到的地方，因此是破坏封装性的

/**
 * 发起人角色
 */
class Originator {

    private String state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento memento) {
        this.state = memento.getState();
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("当前状态：" + state);
    }
}

/**
 * 实现备忘录角色
 */
class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}

/**
 * 实现负责人角色
 */
class Caretaker {

    private Memento memento;

    public Memento retrieveMemento() {
        return this.memento;
    }

    public void saveMemento(Memento memento) {
        this.memento = memento;
    }

}