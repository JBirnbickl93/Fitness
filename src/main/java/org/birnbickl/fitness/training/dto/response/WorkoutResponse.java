package org.birnbickl.fitness.training.dto.response;

import jakarta.validation.constraints.NotBlank;
public class WorkoutResponse {

    private Long id;
    @NotBlank(message = "Workout-Name darf nicht leer sein!")
    private String workoutName;

    public WorkoutResponse(Long id, String workoutName) {
        this.id = id;
        this.workoutName = workoutName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }




}
