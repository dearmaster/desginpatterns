package com.master.design.create.prototype.extension;

import java.util.ArrayList;
import java.util.List;

public class DeepCloneClient {
    public static void main(String[] args) {
        Something something = new Something("Lucy", new ArrayList());
        Something cloneSomething = null;
        try {
            cloneSomething = something.clone();
            cloneSomething.getList().add("Lily");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(something + ", " + something.getList());
        System.out.println(cloneSomething + ", " + cloneSomething.getList());
    }

}

/**
 * 并不是所有的对象都可以进行深度克隆的<br>
 * 如StringBuffer没有重载Clone方法，而且StringBuffer还是一个final类，所以没办法实现深度克隆
 */
class Something implements Cloneable {

    private String name;
    private List<String> list = new ArrayList<>();

    public Something(String name, List list) {
        this.name = name;
        this.list = list;
    }

    @Override
    protected Something clone() throws CloneNotSupportedException {
        Something something = (Something) super.clone();
        something.list = (List<String>) ((ArrayList<String>) this.list).clone();
        return something;
    }

    public List<String> getList() {
        return list;
    }
}