package org.birnbickl.fitness.training;

import jakarta.persistence.*;


@Entity
public class ExerciseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String exerciseName;

    protected ExerciseEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

}
