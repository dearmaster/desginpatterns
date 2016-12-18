package com.master.design.structure.proxy;

/**
 * 代理模式<br><br>
 *  这是一种使用频率相当高的模式，为其他对象提供一种代理，以用来控制对这个对象的访问<br>
 *  有静态代理和动态代理，在这里先demo静态代理<br><br><br>
 *  静态代理缺点：静态代理的一个接口只服务于一种类型的对象，如果要代理的方法很多，势必要为每一种方法都进行代理，静态代理
 *  在程序规模稍大时就无法胜任了；如果接口增加一个方法，除了所有实现类需要实现这个方法之外，所有代理类也需要实现该方法，增加了
 *  代码维护的复杂程度
 */
public class ProxyClient {

    public static void main(String[] args) {
        Subject subject = SubjectFactory.getInstance();
        subject.request();
    }

}

/**
 * 生成静态代理的工厂类
 */
class SubjectFactory {

    public static Subject getInstance() {
        return new ProxySubject(new RealSubject());
    }

}

interface Subject {
    void request();
}

/**
 * 委托类
 */
class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("dealing the request in RealSubject.....");
    }

}

/**
 * 代理类
 */
class ProxySubject implements Subject {

    private Subject target;

    public ProxySubject(Subject target) {
        this.target = target;
    }

    @Override
    public void request() {
        this.before();
        this.target.request();
        this.after();
    }

    private void after() {
        System.out.println("add-on process after real subject dealing the request");
    }

    private void before() {
        System.out.println("add-on process before real subject dealing the request");
    }

}