package com.contaazul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldTestEndpointWithMovementWithRightRotations() throws Exception {
        String input = "MMRMMRMM";
        mvc.perform(post(String.format("/rest/mars/%s", input)))
                .andExpect(content().string("(2, 0, S)"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldTestEndpointWithMovementToTheLeft() throws Exception {
        String input = "MML";
        mvc.perform(post(String.format("/rest/mars/%s", input)))
                .andExpect(content().string("(0, 2, W)"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldTestEndpointWithRepetitionOfMovementToTheLeft() throws Exception {
        String input = "MML";
        mvc.perform(post(String.format("/rest/mars/%s", input)))
                .andExpect(content().string("(0, 2, W)"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldTestEndpointWithInvalidCommand() throws Exception {
        String input = "AAA";
        mvc.perform(post(String.format("/rest/mars/%s", input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldTestEndpointWithInvalidPosition() throws Exception {
        String input = "MMMMMMMMMMMMMMMMMMMMMMMM";
        mvc.perform(post(String.format("/rest/mars/%s", input)))
                .andExpect(status().isBadRequest());
    }
}
