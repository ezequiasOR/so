package philosophers.monitor;

public class Dinner {
    public static int THINKING = 0;
    public static int HUNGRY = 1;
    public static int EATING = 2;
    public static int PHILOSOPHERS_NUMBER;
    public static Philosopher[] philosophers;

    public Dinner(int n) {
        this.PHILOSOPHERS_NUMBER = n;
        this.philosophers = new Philosopher[PHILOSOPHERS_NUMBER];
        this.createPhilosophers();
    }

    private void createPhilosophers() {
        for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
            philosophers[i] = new Philosopher(i);
        }
    }
    public static void dropCutlery(int id) throws InterruptedException {
        philosophers[id].state = THINKING;
        if (philosophers[right(id)].state == HUNGRY && philosophers[right(right(id))].state != EATING) {
            philosophers[right(id)].state = EATING;

            synchronized (philosophers[right(id)]) {
                philosophers[right(id)].notify();
            }
        }

        if (philosophers[left(id)].state == HUNGRY && philosophers[left(left(id))].state != EATING) {
            philosophers[left(id)].state = EATING;
            synchronized (philosophers[left(id)]) {
                philosophers[left(id)].notify();
            }
        }
    }

    public static void getCutlery(int id) throws InterruptedException {
        philosophers[id].state = HUNGRY;
        if(philosophers[left(id)].state != EATING && philosophers[right(id)].state != EATING) {
            philosophers[id].state = EATING;
        } else synchronized (philosophers[id]) {
            philosophers[id].wait();
        }
    }

    public static int left(int i) {
        return (i + 1) % PHILOSOPHERS_NUMBER;
    }

    public static int right(int i) {
        return (i - 1 + PHILOSOPHERS_NUMBER) % PHILOSOPHERS_NUMBER;
    }
}
