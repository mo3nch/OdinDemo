package odinDemo.communicationServer;

public class TickRunnable implements Runnable {
    int tick = 0;

    @Override
    public void run() {
        while (true) {
            if (tick >= 1000)
                tick = 0;

            ++tick;

            System.out.println("Inner tick = " + tick);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTick()
    {
        return tick;
    }
}
