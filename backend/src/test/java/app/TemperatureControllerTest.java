package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTest {

    @Mock private TemperatureService mockTemperatureService;
    private TemperatureController temperatureController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        temperatureController = new TemperatureController(this.mockTemperatureService);
        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getTemperature() throws Exception {
        Temperature temperature = new Temperature(LocalDateTime.of(1,2,3,4,5), 21.74);
        when(mockTemperatureService.getTemperature()).thenReturn(temperature);

        mockMvc.perform(get("/temperature"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(mockTemperatureService).getTemperature();
    }
}