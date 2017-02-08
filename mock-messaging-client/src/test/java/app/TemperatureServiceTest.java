package app;

import io.reactivex.schedulers.TestScheduler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.MessageChannel;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceTest {

    private TemperatureService temperatureService;
    private TestScheduler testScheduler;
    @Mock private MessageChannel mockMessageChannel;
    @Mock private TemperatureSource mockTemperatureSource;

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();
        temperatureService = new TemperatureService(mockTemperatureSource, testScheduler);
    }

    @Test
    public void produce() {
        when(mockTemperatureSource.testChannel()).thenReturn(mockMessageChannel);

        temperatureService.produceTemperatures();

        testScheduler.advanceTimeBy(20, SECONDS);
        verify(mockMessageChannel, times(4)).send(any());
    }
}








