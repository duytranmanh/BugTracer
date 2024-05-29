package com.example.BugTracer.repo;

import com.example.BugTracer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByUsername(String username);
  User getReferenceByUsername(String username);
}
