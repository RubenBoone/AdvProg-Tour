package com.example.advprogtour.repository;

import com.example.advprogtour.model.Tour;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface TourRepository extends CouchbaseRepository<Tour, String> {
}
