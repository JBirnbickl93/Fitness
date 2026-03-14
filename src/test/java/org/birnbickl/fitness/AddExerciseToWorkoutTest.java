package org.birnbickl.fitness;

import jakarta.transaction.Transactional;
import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.repository.WorkoutEntryRepository;
import org.birnbickl.fitness.training.repository.WorkoutRepository;
import org.birnbickl.fitness.training.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AddExerciseToWorkoutTest {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private WorkoutService workoutService;

    @Test
    public void shouldAddExerciseToWorkout() {

        // Workout erstellen, um gleich eine Übung hinzuzufügen
        WorkoutEntity workout = new WorkoutEntity();
        workout.setWorkoutName("Test Workout");
        WorkoutEntity savedWorkout = workoutRepository.save(workout);

        // Übung hinzufügen
        workoutService.addExerciseToWorkout(1L, "Bench Press");

        // Überprüfung
        WorkoutEntity updatedWorkout = workoutRepository.findById(savedWorkout.getId())
                .orElseThrow();

        assertEquals(1, updatedWorkout.getWorkoutEntries().size());
        assertEquals("Bench Press", updatedWorkout.getWorkoutEntries().get(0).getExerciseName());
    }
}
