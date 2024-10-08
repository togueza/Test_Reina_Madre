package com.reina.madre.repository;

import java.util.List;

import com.reina_madre.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
  List<Tasks> findByCompleted(boolean completed);

  List<Tasks> findByTitleContainingIgnoreCase(String title);

  List<Tasks> findByPriorities(String priorities);
}
