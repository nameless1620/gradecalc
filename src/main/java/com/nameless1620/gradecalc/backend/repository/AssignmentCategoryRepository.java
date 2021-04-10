package com.nameless1620.gradecalc.backend.repository;

import com.nameless1620.gradecalc.backend.entity.AssignmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentCategoryRepository extends JpaRepository<AssignmentCategory, Long> {
}
