package com.master.design.behavior.memento.blackbox;

/**
 * 备忘录模式黑盒实现<br><br>
 * 备忘录角色对发起人(Originator)提供一个宽接口，而为其他对象提供一个窄接口<br>
 * 在java语言中实现双重接口的方法就是将备忘录角色设计成发起人角色的内部成员类，再给外部提供一个标识接口给Caretaker以及其他
 * 对象。这样，Originator类看到的是Memento的所有接口，而Caretaker以及其他对象看到的仅仅是标识接口所曝露出来的接口
 */
public class BlackBoxMementoClient {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("On");
        caretaker.saveMemento(originator.createMemento());
        originator.setState("Off");
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}

class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("赋值状态：" + state);
    }

    public MementoInterface createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(MementoInterface memento) {
        this.setState(((Memento) memento).getState());
    }

    private class Memento implements MementoInterface {
        private  String state;
        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}

interface MementoInterface {
}

/**
 * 负责人角色<br><br>
 * 负责人角色能够得到的备忘录对象是以{@link MementoInterface}为接口的，由于这个
 * 接口仅仅是一个标识接口，因此，负责人角色不能改变这个备忘录对象的内容
 */
class Caretaker {

    private MementoInterface memento;

    public MementoInterface retrieveMemento() {
        return this.memento;
    }

    public void saveMemento(MementoInterface memento) {
        this.memento = memento;
    }

}