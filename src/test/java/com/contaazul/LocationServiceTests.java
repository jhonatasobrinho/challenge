package com.contaazul;

import com.contaazul.service.location.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTests {

    @Autowired
    LocationService locationService;

    @Test
    public void shouldTestMovementWithRightRotations() {
        String input = "MMRMMRMM";
        locationService.performMovements(input);

        assertEquals("(2, 0, S)", locationService.current());
    }

    @Test
    public void shouldTestMovementToTheLeft() {
        String input = "MML";
        locationService.performMovements(input);

        assertEquals("(0, 2, W)", locationService.current());
    }

    @Test
    public void shouldTestRepetitionOfMovementToTheLeft() {
        // only checks if the state is not being recorded
        String input = "MML";
        locationService.performMovements(input);

        assertEquals("(0, 2, W)", locationService.current());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTestInvalidCommand() {
        String input = "AAA";
        locationService.performMovements(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTestInvalidPosition() {
        String input = "MMMMMMMMMMMMMMMMMMMMMMMM";
        locationService.performMovements(input);
    }
}
