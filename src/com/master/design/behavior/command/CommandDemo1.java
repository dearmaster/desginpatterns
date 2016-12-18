package com.master.design.behavior.command;

public class CommandDemo1 {

    public static void main(String[] args) {
        CommandDemo1 demo = new CommandDemo1();
        demo.process();
    }

    public void process() {
        MainBoardApi mainBoard = new GigaMainBoard();
        Command openCommand = new OpenCommand(mainBoard);
        Command resetCommand = new ResetCommand(mainBoard);
        Computer computer = new Computer();
        computer.setOpenCommand(openCommand);
        computer.setResetCommand(resetCommand);

        computer.pressOpenButton();
        computer.pressResetButton();
    }

    interface MainBoardApi {
        void open();
        void reset();
    }

    class GigaMainBoard implements MainBoardApi {

        @Override
        public void open() {
            System.out.println("华硕主板正在开机.....");
            System.out.println("接通电源.....");
            System.out.println("设备检查.....");
            System.out.println("装载系统.....");
            System.out.println("机器已正常打开");
        }

        @Override
        public void reset() {
            System.out.println("华硕主板正在重启系统....");
            System.out.println("重启完成,机器正常打开");
        }
    }

    interface Command {
        void execute();
    }

    class ResetCommand implements Command {

        private MainBoardApi mainBoard;

        public ResetCommand(MainBoardApi mainBoard) {
            this.mainBoard = mainBoard;
        }

        @Override
        public void execute() {
            this.mainBoard.reset();
        }
    }

    class OpenCommand implements Command {

        private MainBoardApi mainBoard;

        public OpenCommand(MainBoardApi mainBoard) {
            this.mainBoard = mainBoard;
        }

        @Override
        public void execute() {
            this.mainBoard.open();
        }
    }

    class Computer {

        private Command openCommand;
        private Command resetCommand;

        public void pressOpenButton() {
            this.openCommand.execute();
        }

        public void pressResetButton() {
            this.resetCommand.execute();
        }

        public void setOpenCommand(Command openCommand) {
            this.openCommand = openCommand;
        }

        public void setResetCommand(Command resetCommand) {
            this.resetCommand = resetCommand;
        }
    }

}
