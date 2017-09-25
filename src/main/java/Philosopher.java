import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Philosopher implements Runnable {

    private String name;

    private Object leftFork;
    private Object rightFork;

    public void run() {
        try {
            while (true) {
                //thinking
                doAction(System.nanoTime() + " : Thinking");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Picked up left fork");

                    synchronized (rightFork) {
                        //eating
                        doAction(System.nanoTime() + ": Picked up right fork - eating");

                        doAction(System.nanoTime() + ": Put down right fork");
                    }

                    //back to thinking
                    doAction(System.nanoTime() + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(name + " " + action);
        Thread.sleep((int) Math.random() * 100);
    }
}
