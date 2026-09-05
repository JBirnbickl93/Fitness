package org.birnbickl.fitness.bootstrap;

import org.birnbickl.fitness.training.entity.ExerciseEntity;
import org.birnbickl.fitness.training.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExerciseDataBaseInitializer implements CommandLineRunner {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) throws Exception {
        ExerciseEntity hammerCurl = new ExerciseEntity("Hammer Curl");
        exerciseRepository.save(hammerCurl);
        ExerciseEntity deadLift = new ExerciseEntity("Dead Lift");
        exerciseRepository.save(deadLift);
        ExerciseEntity LegPress = new ExerciseEntity("Leg Press");
        exerciseRepository.save(LegPress);

        System.out.println("Exercise data initialized.");
    }
}
