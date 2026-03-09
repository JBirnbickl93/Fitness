package org.birnbickl.fitness.training.controller;

import jakarta.validation.Valid;
import org.birnbickl.fitness.training.dto.request.CreateSetRequest;
import org.birnbickl.fitness.training.dto.request.CreateWorkoutRequest;
import org.birnbickl.fitness.training.entity.SetEntryEntity;
import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping("/create")
    public ResponseEntity<WorkoutEntity> createWorkout(@Valid @RequestBody CreateWorkoutRequest request) {
        WorkoutEntity newWorkout = workoutService.createWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkout);
    }

    @PostMapping("/entries/{entryId}/addSet")
    public ResponseEntity<SetEntryEntity> addSetToWorkoutEntry(@PathVariable Long id, @RequestBody CreateSetRequest request) {
        SetEntryEntity createdSet = workoutService.addSetToWorkoutEntry(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet);
    }

    @GetMapping("/list")
    public ResponseEntity<List<WorkoutEntity>> getAllWorkouts() {
        List<WorkoutEntity> workouts = Collections.singletonList(workoutService.getAllWorkouts());
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutEntity> getWorkoutById(@PathVariable Long id) {
        WorkoutEntity workout = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(workout);
    }
}
