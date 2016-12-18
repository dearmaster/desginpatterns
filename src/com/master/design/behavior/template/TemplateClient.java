package com.master.design.behavior.template;

import java.util.Scanner;

/**
 * 模版方法<br><br>
 * 模版方法定义一个操作中算法的骨架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义
 * 该算法的某些特定步骤<br><br>
 * 模版方法模式中有如下种类方法：<br>
 * 基本方法， 也称为基本操作，是由子类实现的方法，并且在模版方法中被调用<br>
 * 模版方法，模版方法可以有一个或者多个，一般是一个具体方法，也就是一个骨架，实现对基本方法的调度，完成固定的逻辑。
 * 为了防止恶意操作，一般模版方法上都加上final关键字不允许被复写<br>
 * 钩子方法，钩子方法由抽象类声明并实现。但是子类可以去扩展，子类可以通过扩展钩子方法来影响模版方法的逻辑。
 * <br>
 * 抽象类的任务是搭建逻辑框架，一般由经验丰富的人员编写，因为抽象类的好坏直接决定了程序是否稳定
 * <br><br>
 * 模版方法优缺点：
 * 容易扩展。一般来说抽象类中的模版方法是不易发生改变的部分，而抽象方法是容易发生改变的部分，因此，通过增加实现类
 * 一般可以很容易实现功能的扩展，复合开闭原则<br>
 * 便于维护。对于模版方法模式来说，正式因为他们的主要逻辑相同，才使用了模版方法，加入不使用模版方法，任由这些相同的
 * 代码散乱地分布在不同的类中，则维护起来是相当不方便的<br>
 * 比较灵活。因为有钩子方法，因此，子类的实现也可以影响父类主要逻辑的运行。但是，在灵活的同时，由于子类影响到了父类，
 * 违反了里氏替换原则，也会给程序带来风险
 */
public class TemplateClient {
    public static void main(String[] args) {
        Template templateA = new ConcreteA();
        Template templateB = new ConcreteB();

        templateA.pre(); //外观模式
        templateB.pre();
    }
}

/**
 * 抽象模版
 */
abstract class Template {

    String name = "lily";

    final public void pre() { //final不允许子类改变其框架
        printName();
        if(hasOtherOperation()) //用钩子技术控制是否要执行other操作
            other();
    }

    protected abstract void other();

    public boolean hasOtherOperation() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if("yes".equalsIgnoreCase(input))
            return true;
        return false;
    }

    public void printName() {
        System.out.println(name);
    }
}

/**
 * 具体模版
 */
class ConcreteA extends Template {

    @Override
    protected void other() {
        System.out.println("This is other operation from ConcreteA....");
    }

}

/**
 * 具体模版
 */
class ConcreteB extends Template {

    @Override
    protected void other() {
        System.out.println("This is other operation from ConcreteB...");
    }

}