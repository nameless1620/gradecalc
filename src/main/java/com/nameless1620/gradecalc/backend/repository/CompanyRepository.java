package com.nameless1620.gradecalc.backend.repository;

import com.nameless1620.gradecalc.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
