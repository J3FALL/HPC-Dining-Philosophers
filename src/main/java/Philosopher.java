import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@Data
@RequiredArgsConstructor
public class Philosopher implements Runnable {

    @NonNull
    private String name;

    @NonNull
    private Object leftFork;

    @NonNull
    private Object rightFork;

    private Long maxStarvationTime = 0L;

    @NonNull
    private Long lastMealTime;

    public void run() {
        try {
            while (true) {
                //thinking
                doAction(System.nanoTime() + " : Thinking", State.THINKING);
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Picked up left fork", State.PICK_LEFT_FORK);

                    synchronized (rightFork) {
                        //eating
                        doAction(System.nanoTime() + ": Picked up right fork - eating", State.PICK_RIGHT_FORK);

                        doAction(System.nanoTime() + ": Put down right fork", State.PUT_DOWN_RIGHT_FORK);
                    }

                    //back to thinking
                    doAction(System.nanoTime() + ": Put down left fork. Back to thinking", State.PUT_DOWN_LEFT_FORK);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void doAction(String action, State state) throws InterruptedException {
        System.out.println(name + " " + action);

        if (state.equals(State.PICK_RIGHT_FORK)) {
            Long currentTime = System.nanoTime();
            Long starvationTime = currentTime - lastMealTime;
            if (maxStarvationTime < starvationTime) {
                maxStarvationTime = starvationTime;
            }
            lastMealTime = currentTime;

            System.out.println(name + " Max starvation time : " + getTimeInSeconds(maxStarvationTime));
        }

        Thread.sleep(100);
    }

    private Double getTimeInSeconds(Long nanoTime) {
        return (((double) nanoTime) / 1000000000.0);
    }
    enum State {
        THINKING,
        PICK_LEFT_FORK,
        PICK_RIGHT_FORK,
        PUT_DOWN_RIGHT_FORK,
        PUT_DOWN_LEFT_FORK
    }
}
