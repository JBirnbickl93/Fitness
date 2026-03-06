package org.birnbickl.fitness.training.repository;

import org.birnbickl.fitness.training.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Long> {
}
