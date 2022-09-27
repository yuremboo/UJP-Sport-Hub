package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoOfTheDayRepository extends JpaRepository<PhotoOfTheDay, String> {
}
