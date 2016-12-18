package com.master.design.behavior.memento.historyonself;

/**
 * 自述历史模式<br><br>
 * 是备忘模式的一个变种<br>
 * 在备忘录模式中，发起人(Originator)角色、负责人(Caretaker)角色和备忘录(Memento)角色都是独立的角色。
 * 虽然在实现上备忘录类可以成为发起人类的内部成员类，但是备忘录类仍然保持作为一个角色的独立意义。
 * 在自述历史模式里面，发起人自己兼任负责人角色<br><br>
 * 自述历史模式可能是备忘录模式最为流行的实现形式
 */
public class HistoryOnSelfMementoClient {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.changeState("state 1");
        MementoInterface memento = originator.createMemento();
        originator.changeState("state 2");
        originator.restoreMemento(memento);
    }
}

interface MementoInterface {
}

class Originator {

    private String state;

    public void changeState(String state) {
        this.state = state;
        System.out.println("状态改变为：" + state);
    }

    public MementoInterface createMemento() {
        return new Memento(this);
    }

    public void restoreMemento(MementoInterface memento) {
        changeState(((Memento) memento).getState());
    }

    private class Memento implements MementoInterface {

        private String state;

        public Memento(Originator originator) {
            this.state = originator.state;
        }

        public String getState() {
            return state;
        }
    }

}