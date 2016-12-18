package com.master.design.create.prototype.extension;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅拷贝
 */
public class ShallowCloneClient {

    public static void main(String[] args) {
        Thing thing = new Thing("Lucy", new ArrayList());
        Thing cloneThing = null;
        try {
            cloneThing = thing.clone();
            cloneThing.getList().add("Lily");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(thing + ", " + thing.getList());
        System.out.println(cloneThing + ", " + cloneThing.getList());
    }

}

class Thing implements Cloneable {

    private String name;
    private List<String> list = new ArrayList<>();

    public Thing(String name, List list) {
        this.name = name;
        this.list = list;
    }

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        return (Thing) super.clone();
    }

    public List<String> getList() {
        return list;
    }
}