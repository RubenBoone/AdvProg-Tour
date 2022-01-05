package com.example.advprogtour;

import com.example.advprogtour.model.Tour;
import com.example.advprogtour.repository.TourRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TourControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourRepository tourRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void whenGetTours_thenReturnJsonTours() throws Exception {
        Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);
        Tour tour2 = new Tour("2", "BE19580318", "BET002", 1.5, 15.50, "Tour", "Full tour", 2500, 3.5);

        List<Tour> tourList = new ArrayList<>();
        tourList.add(tour1);
        tourList.add(tour2);

        given(tourRepository.findAll()).willReturn(tourList);

        mockMvc.perform(get("/tours"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[0].tourCode", is("BET001")))
                .andExpect(jsonPath("$[0].tourTime", is(2.5)))
                .andExpect(jsonPath("$[0].entryFee", is(20.0)))
                .andExpect(jsonPath("$[0].title", is("DinnerTour")))
                .andExpect(jsonPath("$[0].description", is("Full tour with dinner at the end")))
                .andExpect(jsonPath("$[0].avgCustomer", is(1000)))
                .andExpect(jsonPath("$[0].score", is(4.0)))

                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[1].tourCode", is("BET002")))
                .andExpect(jsonPath("$[1].tourTime", is(1.5)))
                .andExpect(jsonPath("$[1].entryFee", is(15.50)))
                .andExpect(jsonPath("$[1].title", is("Tour")))
                .andExpect(jsonPath("$[1].description", is("Full tour")))
                .andExpect(jsonPath("$[1].avgCustomer", is(2500)))
                .andExpect(jsonPath("$[1].score", is(3.5)));
    }

    @Test
    public void whenGetToursByTop_thenReturnJsonTours() throws Exception {
        Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);
        Tour tour2 = new Tour("2", "BE19580318", "BET002", 1.5, 15.50, "Tour", "Full tour", 2500, 3.5);
        Tour tour3 = new Tour("3", "FR18890331", "FRT001", 1.5, 25.10, "Guided", "Full tour with guide", 2000, 3.8);

        List<Tour> tourList = new ArrayList<>();
        tourList.add(tour1);
        tourList.add(tour3);
        tourList.add(tour2);

        given(tourRepository.findAllByOrderByScoreDesc()).willReturn(tourList);

        mockMvc.perform(get("/tours/top"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[0].tourCode", is("BET001")))
                .andExpect(jsonPath("$[0].tourTime", is(2.5)))
                .andExpect(jsonPath("$[0].entryFee", is(20.0)))
                .andExpect(jsonPath("$[0].title", is("DinnerTour")))
                .andExpect(jsonPath("$[0].description", is("Full tour with dinner at the end")))
                .andExpect(jsonPath("$[0].avgCustomer", is(1000)))
                .andExpect(jsonPath("$[0].score", is(4.0)))

                .andExpect(jsonPath("$[2].id", is("2")))
                .andExpect(jsonPath("$[2].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[2].tourCode", is("BET002")))
                .andExpect(jsonPath("$[2].tourTime", is(1.5)))
                .andExpect(jsonPath("$[2].entryFee", is(15.50)))
                .andExpect(jsonPath("$[2].title", is("Tour")))
                .andExpect(jsonPath("$[2].description", is("Full tour")))
                .andExpect(jsonPath("$[2].avgCustomer", is(2500)))
                .andExpect(jsonPath("$[2].score", is(3.5)))

                .andExpect(jsonPath("$[1].id", is("3")))
                .andExpect(jsonPath("$[1].monuCode", is("FR18890331")))
                .andExpect(jsonPath("$[1].tourCode", is("FRT001")))
                .andExpect(jsonPath("$[1].tourTime", is(1.5)))
                .andExpect(jsonPath("$[1].entryFee", is(25.10)))
                .andExpect(jsonPath("$[1].title", is("Guided")))
                .andExpect(jsonPath("$[1].description", is("Full tour with guide")))
                .andExpect(jsonPath("$[1].avgCustomer", is(2000)))
                .andExpect(jsonPath("$[1].score", is(3.8)));
    }

    @Test
    public void givenEntryFee_whenGetToursByEntryFee_thenReturnJsonTours() throws Exception {
        Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);
        Tour tour2 = new Tour("2", "BE19580318", "BET002", 1.5, 15.50, "Tour", "Full tour", 2500, 3.5);

        List<Tour> tourList = new ArrayList<>();
        tourList.add(tour1);
        tourList.add(tour2);

        given(tourRepository.findAllByEntryFeeIsLessThanEqual(20)).willReturn(tourList);

        mockMvc.perform(get("/tours/price/{entryFee}", "20"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[0].tourCode", is("BET001")))
                .andExpect(jsonPath("$[0].tourTime", is(2.5)))
                .andExpect(jsonPath("$[0].entryFee", is(20.0)))
                .andExpect(jsonPath("$[0].title", is("DinnerTour")))
                .andExpect(jsonPath("$[0].description", is("Full tour with dinner at the end")))
                .andExpect(jsonPath("$[0].avgCustomer", is(1000)))
                .andExpect(jsonPath("$[0].score", is(4.0)))

                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].monuCode", is("BE19580318")))
                .andExpect(jsonPath("$[1].tourCode", is("BET002")))
                .andExpect(jsonPath("$[1].tourTime", is(1.5)))
                .andExpect(jsonPath("$[1].entryFee", is(15.50)))
                .andExpect(jsonPath("$[1].title", is("Tour")))
                .andExpect(jsonPath("$[1].description", is("Full tour")))
                .andExpect(jsonPath("$[1].avgCustomer", is(2500)))
                .andExpect(jsonPath("$[1].score", is(3.5)));
    }

    @Test
    public void whenPostTour_thenReturnJsonTour() throws Exception{
        Tour tour1 = new Tour("1", "BE19580318", "BET001", 3.0, 30, "Exclusive", "Exclusive", 10, 5);

        mockMvc.perform(post("/tours")
                        .content(mapper.writeValueAsString(tour1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.monuCode", is("BE19580318")))
                .andExpect(jsonPath("$.tourCode", is("BET001")))
                .andExpect(jsonPath("$.tourTime", is(3.0)))
                .andExpect(jsonPath("$.entryFee", is(30.0)))
                .andExpect(jsonPath("$.title", is("Exclusive")))
                .andExpect(jsonPath("$.description", is("Exclusive")))
                .andExpect(jsonPath("$.avgCustomer", is(10)))
                .andExpect(jsonPath("$.score", is(5.0)));
    }

    @Test
    public void givenTour_whenPutTour_thenReturnJsonTour() throws Exception{

        Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);

        given(tourRepository.findTourByTourCode("BET001")).willReturn(tour1);

        Tour tour2 = new Tour("1", "BE19580318", "BET001", 3.0, 30, "Exclusive", "Exclusive", 10, 5);

        mockMvc.perform(put("/tours")
                        .content(mapper.writeValueAsString(tour2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.monuCode", is("BE19580318")))
                .andExpect(jsonPath("$.tourCode", is("BET001")))
                .andExpect(jsonPath("$.tourTime", is(3.0)))
                .andExpect(jsonPath("$.entryFee", is(30.0)))
                .andExpect(jsonPath("$.title", is("Exclusive")))
                .andExpect(jsonPath("$.description", is("Exclusive")))
                .andExpect(jsonPath("$.avgCustomer", is(10)))
                .andExpect(jsonPath("$.score", is(5.0)));
    }

    @Test
    public void givenTour_whenDeleteTour_thenStatusOk() throws Exception{
        Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);

        given(tourRepository.findTourByTourCode("BET001")).willReturn(tour1);

        mockMvc.perform(delete("/tours/{tourCode}","BET001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoTour_whenDeleteTour_thenStatusNotFound() throws Exception{

        given(tourRepository.findTourByTourCode("IkBestaNiet")).willReturn(null);

        mockMvc.perform(delete("/tours/{tourCode}","IkBestaNiet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
