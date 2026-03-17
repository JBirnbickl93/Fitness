package org.birnbickl.fitness;


import jakarta.transaction.Transactional;
import org.birnbickl.fitness.training.entity.ExerciseEntity;
import org.birnbickl.fitness.training.entity.SetEntryEntity;
import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.entity.WorkoutEntryEntity;
import org.birnbickl.fitness.training.repository.ExerciseRepository;
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
public class AddSetToExercise {


    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private WorkoutService workoutService;


    @Test
    void shouldAddSetToExercise() {

        // Workout und Übung erstellen und Übung zu Workout hinzufügen
        WorkoutEntity workout = new WorkoutEntity();
        workout.setWorkoutName("Test Workout");
        WorkoutEntity savedWorkout = workoutRepository.save(workout);

        ExerciseEntity exercise = new ExerciseEntity("Bench Press");
        exerciseRepository.save(exercise);
        workoutService.addExerciseToWorkout(savedWorkout.getId(), "Bench Press");

        WorkoutEntity updatedWorkout = workoutRepository.findById(savedWorkout.getId()).orElseThrow();
        assertEquals(1, updatedWorkout.getWorkoutEntries().size());

        WorkoutEntryEntity workoutEntry = updatedWorkout.getWorkoutEntries().get(0);

        // Set zu Übung hinzufügen

        workoutService.addSetToWorkoutEntry(workoutEntry.getId(), new org.birnbickl.fitness.training.dto.request.CreateSetRequest(1, 10, 100.0));


        // Überprüfung
        WorkoutEntity reloadedWorkout = workoutRepository.findById(savedWorkout.getId()).orElseThrow();
        WorkoutEntryEntity reloadedEntry = reloadedWorkout.getWorkoutEntries().get(0);

        assertEquals(1, reloadedEntry.getSets().size());

        SetEntryEntity setEntry = reloadedEntry.getSets().get(0);

        assertEquals(1, setEntry.getSetNumber());
        assertEquals(10, setEntry.getRepetitions());
        assertEquals(100.0, setEntry.getWeight(),0.001);
    }
}
