package org.birnbickl.fitness.training;

import jakarta.persistence.*;

@Entity
public class SetEntry {

    @Id
    @GeneratedValue
    private long id;



    @ManyToOne
    @JoinColumn(name = "workout_entry_id")
    private WorkoutEntry workoutEntry;

    private int setNumber;
    private int repetitions;
    private double weight;


    protected SetEntry() {}

    public SetEntry(WorkoutEntry workoutEntry, int setNumber, int repetitions, double weight) {
        this.workoutEntry = workoutEntry;
        this.setNumber = setNumber;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    public WorkoutEntry getWorkoutEntry() {
        return workoutEntry;
    }

    public void setWorkoutEntry(WorkoutEntry workoutEntry) {
        this.workoutEntry = workoutEntry;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
