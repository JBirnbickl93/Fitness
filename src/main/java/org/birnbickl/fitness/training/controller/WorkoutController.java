package org.birnbickl.fitness.training.controller;

import jakarta.validation.Valid;
import org.birnbickl.fitness.training.dto.request.CreateSetEntryData;
import org.birnbickl.fitness.training.dto.request.CreateWorkoutData;
import org.birnbickl.fitness.training.dto.request.SetEntryData;
import org.birnbickl.fitness.training.dto.response.WorkoutData;
import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping("/create")
    public ResponseEntity<WorkoutData> createWorkout(@Valid @RequestBody CreateWorkoutData request) {
        WorkoutData newWorkout = workoutService.createWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkout);
    }

    @PostMapping("/entries/{entryId}/addSet")
    public ResponseEntity<SetEntryData> addSetToWorkoutEntry(@PathVariable Long entryId, @Valid @RequestBody CreateSetEntryData request) {
        SetEntryData createdSet = workoutService.addSetToWorkoutEntry (entryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet);
    }

    @GetMapping("/list")
    public ResponseEntity<List<WorkoutData>> getAllWorkouts() {
        List<WorkoutData> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutData> getSingleWorkoutById(@PathVariable Long id) {
        WorkoutData workout = workoutService.getSingleWorkoutById(id);
        return ResponseEntity.ok(workout);
    }
}
