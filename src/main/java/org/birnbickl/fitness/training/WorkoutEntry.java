package org.birnbickl.fitness.training;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkoutEntry {

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
    private List<SetEntry> sets = new ArrayList<>();

    protected WorkoutEntry() {
    }

    public WorkoutEntry(ExerciseEntity exercise) {
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

    public List<SetEntry> getSets() {
        if (this.sets == null) {
            this.sets = new ArrayList<>();
        }
        return this.sets;
    }

    public void addSet(SetEntry setEntry) {
        if (this.sets == null) {
            this.sets = new ArrayList<>();
        }
        setEntry.setWorkoutEntry(this);
        this.sets.add(setEntry);
    }
}
