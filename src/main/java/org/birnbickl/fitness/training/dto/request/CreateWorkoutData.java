package org.birnbickl.fitness.training.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateWorkoutData {

    @NotBlank(message ="Workout-Name darf nicht leer sein!")
    private String workoutName;

    public CreateWorkoutData() {
    }

    public CreateWorkoutData(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }
}
