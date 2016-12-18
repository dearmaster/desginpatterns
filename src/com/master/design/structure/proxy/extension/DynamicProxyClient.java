package com.master.design.structure.proxy.extension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理<br><br>
 * 动态代理类的源码是在程序运行期间由JVM根据反射动态生成的，所以不存在代理类的字节码.class文件<br><br>
 * 动态代理类的命名规则为$ProxyN，其中N是一个逐一增加的阿拉伯数字，代表Proxy类第N次生成的动态代理类，但是
 * 并不是每次调用Proxy的静态方法创建动态代理类都会使N值增加，原因是如果对同一组接口(包括接口排列的顺序相同)
 * 试图重复创建代理类，它会很聪明地返回先前已经创建好的代理类的类对象，而不会尝试去创建一个全新的代理类，这样
 * 可以节省不必要的重复代码生成，提高了代理类的创建效率<br><br>
 * 优点：
 * 与静态代理相比，最大的好处就是接口中声明的所有方法都被转移到调用处理器一个集中的方法处理(InvocationHandler.invoke)
 * 这样，在接口方法数量很多的时候，我们可以进行灵活处理，而不需要想静态代理那样每一个方法中进行中转<br><br>
 * 缺点：
 * 无法摆脱仅支持interface的桎梏，因为生成的代理类有一个公共的父类Proxy，由于Java的继承机制不允许多继承，所以无法对class动态代理
 *
 */
public class DynamicProxyClient {

    public static void main(String[] args) {
        Subject subject = (Subject) ObjectFactory.getInstance("subject");
        UserService userService = (UserService) ObjectFactory.getInstance("userService");
        subject.request("123");
        userService.operation();
    }

}

class ObjectFactory {
    public static Object getInstance(String name) {
        Object delegate = null;
        if("subject".equals(name)) {
            delegate = new RealSubject();
        } else if("userService".equals(name)) {
            delegate = new UserServiceImpl();
        } else {
            throw new IllegalArgumentException("Unsupported bean - " + name);
        }
        InvocationHandler handler = new SubjectInvocationHandler(delegate);
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), handler);
    }
}

interface Subject {
    void request(String str);
}

interface UserService {
    public void operation();
}

class RealSubject implements Subject {

    @Override
    public void request(String str) {
        System.out.println("dealing the request in RealSubject.....");
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class SubjectInvocationHandler implements InvocationHandler {

    private Object delegate; //委托对象

    public SubjectInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long duration = -System.currentTimeMillis();
        Object obj = method.invoke(delegate, args);
        duration += System.currentTimeMillis();
        System.out.println("it takes " + duration + " milliseconds to deal with the request");
        return obj;
    }

}

class UserServiceImpl implements UserService {

    @Override
    public void operation() {
        System.out.println("operation");
    }

}