import lombok.SneakyThrows;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeTest {

    @Test
    @SneakyThrows
    public void testTransformNanoTimeToSeconds() {

        Long firstMoment = System.nanoTime();
        Thread.sleep(500);
        Long secondMoment = System.nanoTime();

        assertThat(Integer.valueOf((int) (secondMoment - firstMoment / 1000)))
                .isInstanceOf(Integer.class);

    }
}
