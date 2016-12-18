package com.master.design.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式<br><br>
 * 使用这种模式可以在不修改已有程序结构的前提下，通过添加额外的访问者来对已有代码的功能实现提升
 */
public class VisitorClient {
    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new NodeA());
        os.add(new NodeB());
        Visitor visitor = new VisitorA();
        os.action(visitor);
    }
}

interface Visitor {
    void visit(NodeA node);
    void visit(NodeB node);
}

class ObjectStructure {

    private List<Node> nodes = new ArrayList<Node>();

    public void action(Visitor visitor) {
        for(Node node : nodes) {
            node.accept(visitor);
        }
    }

    public void add(Node node) {
        nodes.add(node);
    }

}

class VisitorA implements Visitor {

    @Override
    public void visit(NodeA node) {
        System.out.println(node.operationA());
    }

    @Override
    public void visit(NodeB node) {
        System.out.println(node.operationB());
    }

}

class VisitorB implements Visitor {

    @Override
    public void visit(NodeA node) {
        System.out.println(node.operationA());
    }

    @Override
    public void visit(NodeB node) {
        System.out.println(node.operationB());
    }

}

abstract class Node {
    public abstract void accept(Visitor visitor);
}

class NodeA extends Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "NodeA";
    }

}

class NodeB extends Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "NodeB";
    }

}