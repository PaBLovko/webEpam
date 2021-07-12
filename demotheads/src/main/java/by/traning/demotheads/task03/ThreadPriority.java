package by.traning.demotheads.task03;

import lombok.NonNull;

public class ThreadPriority extends Thread{
    public ThreadPriority(@NonNull String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++){
            System.out.println(getName() + " " + i);
            try {
                Thread.sleep(1); // попробовать sleep(1),sleep(0),sleep(10)
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }

}
