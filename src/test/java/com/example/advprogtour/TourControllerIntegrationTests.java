package com.example.advprogtour;

import com.example.advprogtour.model.Tour;
import com.example.advprogtour.repository.TourRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TourControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TourRepository tourRepository;

    private Tour tour1 = new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4);
    private Tour tour2 = new Tour("2", "BE19580318", "BET002", 1.5, 15.50, "Tour", "Full tour", 2500, 3.5);
    private Tour tour3 = new Tour("3", "FR18890331", "FRT001", 1.5, 25.10, "Guided", "Full tour with guide", 2000, 3.8);
    private Tour tourToDelete = new Tour("4", "deleteMe", "deleteMe", 0.1, 1000, "DeleteMe", "DeleteMe", 0, 0);

    @BeforeEach
    public void beforeAllTests() {
        tourRepository.deleteAll();
        tourRepository.save(tour1);
        tourRepository.save(tour2);
        tourRepository.save(tour3);
        tourRepository.save(tourToDelete);
    }

    @AfterEach
    public void afterAllTests() {
        tourRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void whenGetTours_thenReturnJsonTours() throws Exception {

        mockMvc.perform(get("/tours"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))

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
                .andExpect(jsonPath("$[1].score", is(3.5)))

                .andExpect(jsonPath("$[2].id", is("3")))
                .andExpect(jsonPath("$[2].monuCode", is("FR18890331")))
                .andExpect(jsonPath("$[2].tourCode", is("FRT001")))
                .andExpect(jsonPath("$[2].tourTime", is(1.5)))
                .andExpect(jsonPath("$[2].entryFee", is(25.10)))
                .andExpect(jsonPath("$[2].title", is("Guided")))
                .andExpect(jsonPath("$[2].description", is("Full tour with guide")))
                .andExpect(jsonPath("$[2].avgCustomer", is(2000)))
                .andExpect(jsonPath("$[2].score", is(3.8)));
    }

    @Test
    public void whenGetToursByTop_thenReturnJsonTours() throws Exception {

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
    public void givenPrice_whenGetTourstByPrice_thenReturnJsonTours() throws Exception {

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
    public void whenPostTour_thenReturnJsonTour() throws Exception {

        Tour newTour = new Tour("5", "BE19580318", "BET003", 1, 50, "Exclusive tour", "Exclusive tour", 50, 5);

        mockMvc.perform(post("/tours")
                        .content(mapper.writeValueAsString(newTour))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("5")))
                .andExpect(jsonPath("$.monuCode", is("BE19580318")))
                .andExpect(jsonPath("$.tourCode", is("BET003")))
                .andExpect(jsonPath("$.tourTime", is(1.0)))
                .andExpect(jsonPath("$.entryFee", is(50.0)))
                .andExpect(jsonPath("$.title", is("Exclusive tour")))
                .andExpect(jsonPath("$.description", is("Exclusive tour")))
                .andExpect(jsonPath("$.avgCustomer", is(50)))
                .andExpect(jsonPath("$.score", is(5.0)));
    }

    @Test
    public void givenTour_whenPutTour_thenReturnJsonTour() throws Exception {

        Tour updatedTour = new Tour("1", "BE19580318", "BET001", 3, 30, "test", "test", 10, 2.0);

        mockMvc.perform(put("/tours")
                        .content(mapper.writeValueAsString(updatedTour))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.monuCode", is("BE19580318")))
                .andExpect(jsonPath("$.tourCode", is("BET001")))
                .andExpect(jsonPath("$.tourTime", is(3.0)))
                .andExpect(jsonPath("$.entryFee", is(30.0)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.description", is("test")))
                .andExpect(jsonPath("$.avgCustomer", is(10)))
                .andExpect(jsonPath("$.score", is(2.0)));
    }

    @Test
    public void givenTour_whenDeleteTour_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/tours/{tourCode}", "deleteMe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoTour_whenDeleteTour_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/monuments/{monuCode}", "notFound")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
