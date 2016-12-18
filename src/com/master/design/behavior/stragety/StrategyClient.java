package com.master.design.behavior.stragety;

/**
 * 策略模式又称算法族模式，就是定义了不同的算法族，并且相互之间可以替换<br>
 * 此模式让算法的变化独立于使用算法的客户，其好处就是可以动态地改变对象的行为<br>
 * 设计的原则是把一个类中经常改变或者将来有可能会改变的部分提取出来作为一个接口，然后在类中包含这个接口的实例
 * 这样就可以随意调用实现了这个接口的类的行为<br>
 * 策略模式跟状态模式一样，可以减少代码中的条件语句
 * <br><br><br>
 * 目前没弄明白策略模式和状态模式的区别
 */
public class StrategyClient {

    public static void main(String[] args) {
        Strategy strategyA = new ConcreteStrategyA();
        Context ctxA = new Context(strategyA);
        ctxA.calculate();

        Strategy strategyB = new ConcreteStrategyB();
        Context ctxB = new Context(strategyB);
        ctxB.calculate();
    }

}

class Context {
    private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public void calculate() {
        strategy.algorithm();
    }
}

interface Strategy {
    void algorithm();
}

class ConcreteStrategyA implements Strategy {

    @Override
    public void algorithm() {
        //business logic
        System.out.println("ConcreteStrategyA");
    }

}

class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithm() {
        //business logic
        System.out.println("ConcreteStrategyB");
    }

}