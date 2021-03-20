package com.nameless1620.gradecalc.backend.repository;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
