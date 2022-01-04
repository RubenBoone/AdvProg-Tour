package com.example.advprogtour.controller;

import com.example.advprogtour.model.Tour;
import com.example.advprogtour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @PostConstruct
    public void fillDB() {
        if (tourRepository.count() == 0) {
            tourRepository.save(new Tour("1", "BE19580318", "BET001", 2.5, 20, "DinnerTour", "Full tour with dinner at the end", 1000, 4));
            tourRepository.save(new Tour("2", "BE19580318", "BET002", 1.5, 15.50, "Tour", "Full tour", 2500, 3.5));
            tourRepository.save(new Tour("3", "FR18890331", "FRT001", 1.5, 25.10, "Guided", "Full tour with guide", 2000, 3.8));


            System.out.println("Tour test: " + tourRepository.findTourByTourCode("BET001").getTitle());
        }
    }


    // Get all tours.
    @GetMapping("/tours")
    public List<Tour> getAllTours(){
        return tourRepository.findAll();
    }

    // Get top 3 tours based on score.
    @GetMapping("/tours/top")
    public List<Tour> getTopTours(){
        List<Tour> tours = tourRepository.findAllByOrderByScoreDesc();
        List<Tour> top = null;
        top.add(tours.get(0));
        top.add(tours.get(1));
        top.add(tours.get(2));

        return top;
    }

    // Get Tours that are cheaper/equal to given entryfee
    @GetMapping("/tours/price/{entryFee}")
    public List<Tour> getToursBelowPrice(@PathVariable double entryFee){
        return tourRepository.findAllByEntryFeeIsLessThanEqual(entryFee);
    }

}
