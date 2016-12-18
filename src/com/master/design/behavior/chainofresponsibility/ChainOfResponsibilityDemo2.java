package com.master.design.behavior.chainofresponsibility;

public class ChainOfResponsibilityDemo2 {

    private static volatile boolean stop = false; //flag indicating game over

    public static void main(String[] args) {
        ChainOfResponsibilityDemo2 demo = new ChainOfResponsibilityDemo2();
        demo.process();
    }

    public void process() {
        Player player1 = new Player("张三");
        Player player2 = new Player("李四");
        Player player3 = new Player("王五");
        Player player4 = new Player("赵六");
        Player player5 = new Player("陈七");
        player1.setNext(player2);
        player2.setNext(player3);
        player3.setNext(player4);
        player4.setNext(player5);
        player5.setNext(player1);

        Thread timer = new Timer();
        Thread game = new GameThread(player1);

        timer.start();
        game.start();
    }

    public void startGame(Player startPlayer) {
        startPlayer.handle();
    }

    class Player {

        private String name;
        private Player next;

        public Player(String name) {
            this.name = name;
        }

        public void setNext(Player next) {
            this.next = next;
        }

        final void handle() {
            System.out.println(this.name + " 接棒");
            if(stop) {
                System.out.println("game over! " + name + " is the luckiest");
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.next.handle();
            }
        }
    }

    class Timer extends Thread {

        private long time;

        public Timer() {
            time = 1 + Math.round(Math.random() * 9); // sleep 1 - 10 seconds
            System.out.println("The game will be over in " + time + " seconds.");
        }
        @Override
        public void run() {
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop = true;
        }
    }

    class GameThread extends Thread {

        private Player player;

        public GameThread(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            startGame(player);
        }
    }

}