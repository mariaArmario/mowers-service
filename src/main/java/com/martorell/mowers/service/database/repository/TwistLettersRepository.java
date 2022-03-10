package com.martorell.mowers.service.database.repository;

import com.martorell.mowers.service.database.entity.TwistLetters;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TwistLettersRepository extends CrudRepository<TwistLetters, Integer> {

    List<TwistLetters> findAll();
}
