package by.traning.demotheads.task02;

public class ThreadRunnablePerson extends Person implements Runnable {

    public ThreadRunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println(getName() + ": Hello World");
        }
    }

    public static void main(String[] args) {
        ThreadRunnablePerson p1 =  new ThreadRunnablePerson("Alice");
        Thread t1 = new Thread(p1);
        t1.start();
        ThreadRunnablePerson p2 = new ThreadRunnablePerson("Bob");
        Thread t2 = new Thread(p2);
        t2.start();
    }
}
