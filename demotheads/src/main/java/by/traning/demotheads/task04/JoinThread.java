package by.traning.demotheads.task04;

class JoinThread extends Thread {
    public JoinThread(String name) {
        super(name);
    }

    public void run() {
        String nameT = getName();
        long timeout = 0;
        System.out.println("Старт потока " + nameT);
        try {
            timeout = switch (nameT) {
                case "First" -> 5_000;
                case "Second" -> 1_000;
                default -> throw new IllegalStateException("Unexpected value: " + nameT);
            };
            Thread.sleep(timeout);
            System.out.println("завершение потока " + nameT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

