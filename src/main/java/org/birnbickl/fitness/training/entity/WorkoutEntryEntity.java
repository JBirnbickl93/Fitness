package org.birnbickl.fitness.training.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkoutEntryEntity {

    @Id
    @GeneratedValue
    private long id;



    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    @OneToMany(mappedBy = "workoutEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetEntryEntity> sets = new ArrayList<>();

    protected WorkoutEntryEntity() {
    }

    public WorkoutEntryEntity(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    public long getId() {
        return id;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    public List<SetEntryEntity> getSets() {
        if (this.sets == null) {
            this.sets = new ArrayList<>();
        }
        return this.sets;
    }

    public void addSet(SetEntryEntity setEntryEntity) {
        if (this.sets == null) {
            this.sets = new ArrayList<>();
        }
        setEntryEntity.setWorkoutEntry(this);
        this.sets.add(setEntryEntity);
    }

    public void addSetEntry(SetEntryEntity set) {
        sets.add(set);
        set.setWorkoutEntry(this);
    }


}
