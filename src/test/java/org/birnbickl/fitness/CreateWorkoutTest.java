package org.birnbickl.fitness;

import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class CreateWorkoutTest {


    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    void shouldCreateWorkout(){

        // neues Workout erstellen

        WorkoutEntity createdWorkout = new WorkoutEntity();
        createdWorkout.setWorkoutName("Test Workout");

        // Workout wird gespeichert

        WorkoutEntity savedWorkout = workoutRepository.save(createdWorkout);

        // Überprüfung, ob Workout in Datenbank existiert
        assertNotNull(savedWorkout.getId());
        assertEquals("Test Workout", savedWorkout.getWorkoutName());

    }
}
