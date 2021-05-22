package br.com.ostrowskijr.apimovies;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ostrowskijr.apimovies.controllers.WinnersController;
import br.com.ostrowskijr.apimovies.data.CollectionWinners;
import br.com.ostrowskijr.apimovies.model.Winner;
import br.com.ostrowskijr.apimovies.utils.JsonUtils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WinnersController.class)
public class WinnersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CollectionWinners collectionWinners;

    @Test
    public void getWinnersIsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/winners")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getWinnersNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/winners/test"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getWinnersList() throws Exception {
        List<Winner> winners = new ArrayList<Winner>();
        //
        when(this.collectionWinners.getWinners()).thenReturn(winners);
        //
        this.mockMvc.perform(MockMvcRequestBuilders.get("/winners/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtils.parseToJson(winners)));

    }
}
