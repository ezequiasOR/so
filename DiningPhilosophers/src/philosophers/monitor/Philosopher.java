package philosophers.monitor;

public class Philosopher implements Runnable {
    public int id;
    public int state;

    public Philosopher(int id) {
        this.id = id;
        this.state = Dinner.THINKING;
        new Thread((Runnable) this, "Philosopher " + id).start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Philosopher " + id + " is thinking!");
                Thread.sleep(800);
                System.out.println("Philosopher " + id + " is hungry!");
                Dinner.getCutlery(this.id);
                System.out.println("Philosopher " + id + " is eating!");
                Thread.sleep(200);
                System.out.println("Philosopher " + id + " has finished eating!");
                Dinner.dropCutlery(this.id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
