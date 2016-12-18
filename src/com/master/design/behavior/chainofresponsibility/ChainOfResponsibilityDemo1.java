package com.master.design.behavior.chainofresponsibility;

/**
 * 责任链模式
 */
public class ChainOfResponsibilityDemo1 {

    public static void main(String[] args) {
        ChainOfResponsibilityDemo1 demo = new ChainOfResponsibilityDemo1();
        AbstractRequest request1 = demo.new Request1();
        AbstractRequest request2 = demo.new Request2();
        AbstractRequest request3 = demo.new Request3();
        AbstractRequest request4 = demo.new Request4();

        AbstractHandler handler1 = demo.new Handler1();
        AbstractHandler handler2 = demo.new Handler2();
        AbstractHandler handler3 = demo.new Handler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
        handler1.handleRequest(request3);
        handler1.handleRequest(request4);
    }

    interface Level {
        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
        public static final int LEVEL_3 = 3;
        public static final int LEVEL_4 = 4;
    }

    abstract class AbstractRequest {
        protected abstract int getRequestLevel();
    }

    class Request1 extends AbstractRequest {
        @Override
        protected int getRequestLevel() {
            return Level.LEVEL_1;
        }
    }

    class Request2 extends AbstractRequest {
        @Override
        protected int getRequestLevel() {
            return Level.LEVEL_2;
        }
    }

    class Request3 extends AbstractRequest {
        @Override
        protected int getRequestLevel() {
            return Level.LEVEL_3;
        }
    }

    class Request4 extends AbstractRequest {
        @Override
        protected int getRequestLevel() {
            return Level.LEVEL_4;
        }
    }

    abstract class AbstractHandler {
        private AbstractHandler nextHandler;
        protected final void handleRequest(AbstractRequest request) {
            if(this.getHandlerLevel() == request.getRequestLevel()) {
                this.handle(request);
            } else {
                if(this.nextHandler != null) {
                    System.out.println("处理者 level " + this.getHandlerLevel() + " 不能处理请求 level " + request.getRequestLevel() + ", 传递给处理者 level " + nextHandler.getHandlerLevel());
                    this.nextHandler.handleRequest(request);
                } else {
                    System.out.println("责任链上的所有处理者都不能胜任该请求 level " + request.getRequestLevel());
                }
            }
        }
        protected abstract int getHandlerLevel();

        protected void handle(AbstractRequest request) {
            System.out.println("处理者 level " + getHandlerLevel() + " 处理请求 level " + request.getRequestLevel());
        }

        public void setNextHandler(AbstractHandler nextHandler) {
            this.nextHandler = nextHandler;
        }
    }

    class Handler1 extends AbstractHandler {
        @Override
        protected int getHandlerLevel() {
            return Level.LEVEL_1;
        }
    }

    class Handler2 extends AbstractHandler {
        @Override
        protected int getHandlerLevel() {
            return Level.LEVEL_2;
        }
    }

    class Handler3 extends  AbstractHandler {
        @Override
        protected int getHandlerLevel() {
            return Level.LEVEL_3;
        }
    }

}
