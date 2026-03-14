package org.birnbickl.fitness.training.service;

import org.birnbickl.fitness.errorhandling.WorkoutNotFoundException;
import org.birnbickl.fitness.training.dto.request.CreateSetRequest;
import org.birnbickl.fitness.training.dto.request.CreateWorkoutRequest;
import org.birnbickl.fitness.training.dto.response.WorkoutResponse;
import org.birnbickl.fitness.training.entity.SetEntryEntity;
import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.birnbickl.fitness.training.entity.WorkoutEntryEntity;
import org.birnbickl.fitness.training.repository.WorkoutEntryRepository;
import org.birnbickl.fitness.training.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutEntryRepository workoutEntryRepository;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutEntryRepository workoutEntryRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutEntryRepository = workoutEntryRepository;
    }

    public WorkoutEntity createWorkout(CreateWorkoutRequest request) {
        WorkoutEntity workout = new WorkoutEntity();
        workout.setWorkoutName(request.getWorkoutName());
        workoutRepository.save(workout);
        return workout;
    }

    public SetEntryEntity addSetToWorkoutEntry(Long entryId, CreateSetRequest request) {
        WorkoutEntryEntity workoutEntry = workoutEntryRepository.findById(entryId)
                .orElseThrow(() -> new WorkoutNotFoundException("WorkoutEntry konnte nicht gefunden!"));

        SetEntryEntity setEntry = new SetEntryEntity(
                workoutEntry,
        request.getSetNumber(),
        request.getRepetitions(),
        request.getWeight()
        );
        workoutEntry.addSetEntry(setEntry);
        workoutEntryRepository.save(workoutEntry);

        return setEntry;
    }

    public void addExerciseToWorkout(Long id, String exerciseName) {

    }



    public WorkoutEntity updateWorkout(WorkoutEntity workout) {
        return workoutRepository.save(workout);
    }

    public void deleteWorkoutById(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new WorkoutNotFoundException("Workout mit ID " + id + " nicht gefunden!");
        }
        workoutRepository.deleteById(id);
    }

    public WorkoutEntity getWorkoutById(Long id) {
        return workoutRepository.findById(id).orElseThrow(() ->
                new WorkoutNotFoundException("Workout mit ID " + id + " nicht gefunden!"));
    }

    public List<WorkoutResponse> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workout -> new WorkoutResponse(workout.getId(), workout.getWorkoutName()))
                .toList();
    }

    public WorkoutResponse getSingleWorkoutById(Long id){
        WorkoutEntity workout = workoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout nicht gefunden!"));

        return new WorkoutResponse(workout.getId(), workout.getWorkoutName());
    }

}
