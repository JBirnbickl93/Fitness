package org.birnbickl.fitness.training.entity;

import jakarta.persistence.*;

@Entity
public class SetEntryEntity {

    @Id
    @GeneratedValue
    private long id;



    @ManyToOne
    @JoinColumn(name = "workout_entry_id")
    private WorkoutEntryEntity workoutEntryEntity;

    private int setNumber;
    private int repetitions;
    private double weight;


    protected SetEntryEntity() {}

    public SetEntryEntity(WorkoutEntryEntity workoutEntryEntity, int setNumber, int repetitions, double weight) {
        this.workoutEntryEntity = workoutEntryEntity;
        this.setNumber = setNumber;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    public WorkoutEntryEntity getWorkoutEntry() {
        return workoutEntryEntity;
    }

    public void setWorkoutEntry(WorkoutEntryEntity workoutEntryEntity) {
        this.workoutEntryEntity = workoutEntryEntity;
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
