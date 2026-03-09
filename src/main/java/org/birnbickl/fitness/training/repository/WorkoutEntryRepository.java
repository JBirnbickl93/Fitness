package org.birnbickl.fitness.training.repository;

import org.birnbickl.fitness.training.entity.WorkoutEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutEntryRepository extends JpaRepository <WorkoutEntryEntity, Long> {
}
