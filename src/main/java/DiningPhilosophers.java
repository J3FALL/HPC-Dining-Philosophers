import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiningPhilosophers {

    private static final Integer PHILOSOPHERS_AMOUNT = 5;

    public static void main(String[] args) {
        List<Philosopher> philosophers = new ArrayList<Philosopher>();
        List<Object> forks = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object());
        List<String> philosophersNames = Arrays.asList("Socrate", "Descartes", "Sartre", "Baudrillard", "Aristotle");

        for (int index = 0; index < PHILOSOPHERS_AMOUNT; index++) {

            Object leftFork = forks.get(index);
            Object rightFork = forks.get((index + 1) % forks.size());

            if (index == PHILOSOPHERS_AMOUNT - 1) {
                philosophers.add(new Philosopher(philosophersNames.get(index), rightFork, leftFork, System.nanoTime()));
            } else {
                philosophers.add(new Philosopher(philosophersNames.get(index), leftFork, rightFork, System.nanoTime()));
            }
            Thread t = new Thread(philosophers.get(index), "Philosopher " + (index + 1));
            t.start();
        }
    }
}
