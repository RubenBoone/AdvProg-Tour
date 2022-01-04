package com.example.advprogtour.repository;

import com.example.advprogtour.model.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TourRepository extends MongoRepository<Tour, String> {
    Tour findTourByTourCode(String tourCode);
    List<Tour> findAllByOrderByScoreDesc();
    List<Tour> findAllByEntryFeeIsLessThanEqual(double entryFee);
}
