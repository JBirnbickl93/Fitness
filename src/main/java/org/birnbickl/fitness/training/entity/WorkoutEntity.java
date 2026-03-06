package org.birnbickl.fitness.training.entity;

import jakarta.persistence.*;
import org.birnbickl.fitness.user.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkoutEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String workoutName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutEntryEntity> workoutEntries = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public List<WorkoutEntryEntity> getWorkoutEntries() {
        if (this.workoutEntries == null) {
            this.workoutEntries = new ArrayList<>();
        }
        return this.workoutEntries;
    }

    public void addWorkoutEntry(WorkoutEntryEntity workoutEntryEntity) {
        if (this.workoutEntries == null) {
            this.workoutEntries = new ArrayList<>();
        }
        workoutEntryEntity.setWorkout(this);
        this.workoutEntries.add(workoutEntryEntity);
    }

    public void removeWorkoutEntry(WorkoutEntryEntity workoutEntryEntity) {
        if (this.workoutEntries != null) {
            this.workoutEntries.remove(workoutEntryEntity);
        }
        workoutEntryEntity.setWorkout(null);
    }
}
