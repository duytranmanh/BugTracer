package com.example.BugTracer.repo;

import com.example.BugTracer.model.UserProject;
import com.example.BugTracer.model.UserProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId> {
}
