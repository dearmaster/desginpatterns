package com.master.design.behavior.memento.multicheckpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * 多重检查点<br><br>
 * 常见的需求可能要存储很多个状态，或者说有多个检查点。备忘录模式可以将发起人的多个状态存储到备忘录里
 * 以便将发起人对象恢复到备忘录对象存储的某一个检查点上
 */
public class MutiCheckPointMementoClient {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker(originator);

        originator.setState("state 1");
        caretaker.createMememto();
        originator.setState("state 2");
        caretaker.createMememto();
        originator.setState("state 3");
        caretaker.createMememto();
        originator.setState("state 4");
        caretaker.createMememto();

        caretaker.displayMementos();
        System.out.println("----------------------------");
        caretaker.restoreMememto(caretaker.retrieveMento(2));
        caretaker.displayMementos();
        System.out.println("----------------------------");
        caretaker.restoreMememto(caretaker.retrieveMento(0));
        caretaker.displayMementos();
    }
}

class Originator {
    private String state;
    public Memento createMememto() {
        return new Memento(state);
    }

    public void setState(String state) {
        this.state = state;
    }
}

class Caretaker {

    private List<Memento> mementos;
    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
        mementos = new ArrayList<Memento>();
    }

    public Memento retrieveMemento() {
        return retrieveMento(mementos.size() - 1);
    }

    public Memento createMememto() {
        Memento memento = originator.createMememto();
        mementos.add(memento);
        return memento;
    }

    public Memento retrieveMento(int index) {
        return mementos.get(index);
    }

    public void restoreMememto(Memento memento) {
        originator.setState(memento.getState());
        mementos.remove(memento);
    }

    public void displayMementos() {
        for(Memento memento : mementos) {
            System.out.println(memento.getState());
        }
    }

    public void saveMemento(Memento memento) {
        mementos.add(memento);
    }
}

class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}