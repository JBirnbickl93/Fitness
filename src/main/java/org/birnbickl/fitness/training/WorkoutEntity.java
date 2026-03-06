package org.birnbickl.fitness.training;

import jakarta.persistence.*;
import org.birnbickl.fitness.user.UserEntity;

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
    private List<WorkoutEntry> workoutEntries = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public List<WorkoutEntry> getWorkoutEntries() {
        if (this.workoutEntries == null) {
            this.workoutEntries = new ArrayList<>();
        }
        return this.workoutEntries;
    }

    public void addWorkoutEntry(WorkoutEntry workoutEntry) {
        if (this.workoutEntries == null) {
            this.workoutEntries = new ArrayList<>();
        }
        workoutEntry.setWorkout(this);
        this.workoutEntries.add(workoutEntry);
    }

    public void removeWorkoutEntry(WorkoutEntry workoutEntry) {
        if (this.workoutEntries != null) {
            this.workoutEntries.remove(workoutEntry);
        }
        workoutEntry.setWorkout(null);
    }
}
