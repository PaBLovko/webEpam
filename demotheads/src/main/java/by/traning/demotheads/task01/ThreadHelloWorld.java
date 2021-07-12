package by.traning.demotheads.task01;

public class ThreadHelloWorld extends Thread{

    public void run(){
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        ThreadHelloWorld threadHelloWorld = new ThreadHelloWorld();
        threadHelloWorld.start();
    }
}
