package com.example.BugTracer.repo;

import com.example.BugTracer.model.Project;
import com.example.BugTracer.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByProject(Project project);
}
