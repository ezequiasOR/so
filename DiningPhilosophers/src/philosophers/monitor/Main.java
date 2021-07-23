package philosophers.monitor;

public class Main {
    public static int PHILOSOPHERS_NUMBER = 5;

    public static void main(String[] args) {
        new Dinner(PHILOSOPHERS_NUMBER);
    }
}
