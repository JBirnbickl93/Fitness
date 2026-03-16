package org.birnbickl.fitness.training.repository;

import org.birnbickl.fitness.training.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    Optional<ExerciseEntity> findByExerciseName(String exerciseName);
}
