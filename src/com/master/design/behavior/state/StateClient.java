package com.master.design.behavior.state;

/**
 * 状态模式<br><br>
 * 状态模式允许一个对象在改变其状态时，改变它的行为，看起来似乎修改了它的类<br>
 * 状态模式可以有效地替换充满在程序中的if else语句，将不同条件下的行为封装在一个类里面
 * 再给这些类一个统一的父类来约束他们<br><br>
 * 状态模式例子 VaRService中通过不同的ProductType选择不同的VaRMarker,看似还是在用if else，但是
 * 不可否认，很大程度上避免了多次使用if else
 */
public class StateClient {

    public static void main(String[] args) {
        State stateA = new ConcreteStateA();
        State stateB = new ConcreteStateB();
        Context ctx = new Context();
        ctx.setState(stateA);
        ctx.request("Hello");
        ctx.setState(stateB);
        ctx.request("Hello");
    }

}

/**
 * 环境类Context的行为request()是委派给一个具体状态类的，通过多态性原则，可以动态
 * 改变环境类Context的属性state的类容，使其从一个具体状态变换到另一个具体状态，从而
 * 使环境类的行为request()由不同的具体状态类来执行
 */
class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request(String request) {
        state.handle(request);
    }
}

interface State {
    public void handle(String param);
}

class ConcreteStateA implements State {
    @Override
    public void handle(String param) {
        System.out.println("ConcreteStateA handle: " + param);
    }
}

class ConcreteStateB implements State {
    @Override
    public void handle(String param) {
        System.out.println("ConcreteStateB handle: " + param);
    }
}