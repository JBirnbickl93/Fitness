package org.birnbickl.fitness.training.service;

import org.birnbickl.fitness.training.repository.WorkoutRepository;
import org.springframework.stereotype.Service;



@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }
}
