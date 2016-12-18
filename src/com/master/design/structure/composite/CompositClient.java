package com.master.design.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式<br>
 * 组合模式体现了整体与部分的关系，典型的应用就是树形结构<br>
 * 组合模式的设计目的是让用户以用统一的接口来处理单个对象以及对象组合，组合模式把整体和部分的关系用树形结构
 * 表示出来，使得客户端可以把一个单独的成分对象和符合对象同等看待<br><br>
 *
 * 组合模式的实现根据所实现接口的区别分为两种形式：安全方式和透明方式
 *
 * 用户使用component结构与组合结构中的对象进行交互。如果接收者是一个叶子节点，则直接处理请求。如果接收者是一个
 * Component，则通常将请求发送给他的叶子部件，在转发请求之前或者之后可能会执行一些辅助操作<br><br>
 */
public class CompositClient {

    public static void main(String[] args) {
        demoSecure();
    }

    public static void demoSecure() {
        FileModel file1 = new FileModel("test.txt");
        FileModel file2 = new FileModel("sample.csv");

        FolderModel root = new FolderModel("C:");
        FolderModel sys = new FolderModel("system");
        FolderModel drivers = new FolderModel("drivers");
        FolderModel tmp = new FolderModel("tmp");
        FolderModel home = new FolderModel("home");

        root.addChild(sys);
        root.addChild(drivers);
        drivers.addChild(home);
        home.addChild(tmp);
        tmp.addChild(file2);
        drivers.addChild(file1);

        root.display("");
    }

    public static void demoTransparent() {
        Root root1 = new Folder("C:");
        Root root2 = new Folder("D:");
        Root win = new Folder("windows");
        Root sys = new Folder("system");
        Root tmp = new Folder("temp");
        Root test = new Folder("test");
        Root hw = new File("HelloWord.java");
        Root feed = new File("upstream.feed.csv");

        test.addChild(feed);
        tmp.addChild(test);
        win.addChild(tmp);
        root1.addChild(win);
        root1.addChild(tmp);

        sys.addChild(hw);
        root2.addChild(sys);

        root1.display("");
        root2.display("");
    }

}

//透明方式
// 所有的构件都有相同的接口，在客户端看来，树叶对象和合成对象的区别在接口层次上消失了
//客户端可以同等地看待所有对象
//这种方式的缺点是不安全，因为树叶对象和组合对象在本质上是有区别的

//抽象构件 Component
/**
 * File和Folder的共有接口
 */
interface Root {

    boolean addChild(Root file);
    boolean remoteChild(Root file);
    List<Root> getChildren();
    void display(String blank);

}

//树叶构件 Leaf
class File implements Root {

    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public boolean addChild(Root file) {
        return false;
    }

    @Override
    public boolean remoteChild(Root file) {
        return false;
    }

    @Override
    public List<Root> getChildren() {
        return null;
    }

    @Override
    public void display(String blank) {
        System.out.println(blank + "|_" + name);
    }

}

//树枝构件 Composite
class Folder implements Root {

    private String name;
    private List<Root> children;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<Root>();
    }

    @Override
    public boolean addChild(Root file) {
        return children.add(file);
    }

    @Override
    public boolean remoteChild(Root file) {
        return children.remove(file);
    }

    @Override
    public List<Root> getChildren() {
        return children;
    }

    @Override
    public void display(String blank) {
        System.out.println(name);
        String childBlank = getChildBlank(blank, name);
        for(Root child : children) {
            if(child instanceof Folder) {
                System.out.print(blank + "|_ ");
            }
            child.display(childBlank);
        }
    }

    private String getChildBlank(String blank, String name) {
        int len = blank.length() + name.length()/2;
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<len; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }
}


//安全方式
//在composite类里面声明所有用来管理子类对象的方法，而树叶对象则不声明对子类管理的方法
//树叶类和合成类有不同的接口
interface IFile {
    void display(String blank);
}

class FileModel implements IFile {

    private String name;

    public FileModel(String name) {
        this.name = name;
    }

    @Override
    public void display(String blank) {
        System.out.println(blank + "|_" + name);
    }
}

class FolderModel implements IFile {

    private String name;
    private List<IFile> children;

    public FolderModel(String name) {
        this.name = name;
        this.children = new ArrayList<IFile>();
    }

    public boolean addChild(IFile file) {
        return children.add(file);
    }

    public boolean remoteChild(Root file) {
        return children.remove(file);
    }

    public List<IFile> getChildren() {
        return children;
    }

    @Override
    public void display(String blank) {
        System.out.println(name);
        String childBlank = getChildBlank(blank, name);
        for(IFile child : children) {
            if(child instanceof FolderModel) {
                System.out.print(blank + "|_ ");
            }
            child.display(childBlank);
        }
    }

    private String getChildBlank(String blank, String name) {
        int len = blank.length() + name.length()/2;
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<len; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }

}