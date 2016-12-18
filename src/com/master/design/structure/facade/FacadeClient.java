package com.master.design.structure.facade;

/**
 * 外观模式<br>
 * 外观模式是为了实现客户端和子系统之间的解耦<br>
 * 外观模式是由代理模式发展而来的。与代理模式类似，代理模式是一对一的代理，而外观模式是一对多的代理<br>
 * 与装饰模式不同的是，装饰模式为对象增加功能，而外观模式则是提供一个简化的调用方式<br>
 * 一个系统可以有多个外观类（门面类），每个外观类都只有一个实例，可以用单例模式来实现
 */
public class FacadeClient {

    public static void main(String[] args) {
        TourFacade tourForLily = new TourFacade("Lily");
        TourFacade tourForLucy = new TourFacade("Lucy");
        tourForLily.travel();
        tourForLucy.travel();
    }

}

class ScheduleFlight {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void book() {
        System.out.println(name + " - 预订机票");
    }

    public void takeOff() {
        System.out.println(name + " - 飞往目的地");
    }

    public void back() {
        System.out.println(name + " - 返回");
    }

}

class ScheduleHotel {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void book() {
        System.out.println(name + " - 预订酒店");
    }

    public void checkIn() {
        System.out.println(name + " - 住酒店");
    }

    public void checkout() {
        System.out.println(name + " - 离开酒店");
    }

}

class Tour {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void start() {
        System.out.println(name + " - 开始游玩");
    }

}

class TourFacade {

    private ScheduleFlight scheduleFlight;
    private ScheduleHotel scheduleHotel;
    private Tour tour;

    public TourFacade(String name) {
        scheduleFlight = new ScheduleFlight();
        scheduleHotel = new ScheduleHotel();
        tour = new Tour();

        scheduleFlight.setName(name);
        scheduleHotel.setName(name);
        tour.setName(name);
    }

    public void travel() {
        scheduleFlight.book();
        scheduleFlight.takeOff();
        scheduleHotel.book();
        scheduleHotel.checkIn();
        tour.start();
        scheduleHotel.checkout();
        scheduleFlight.back();
    }

}