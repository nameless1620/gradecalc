package com.nameless1620.gradecalc.backend.service;

import com.nameless1620.gradecalc.backend.entity.Company;
import com.nameless1620.gradecalc.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(company ->
                stats.put(company.getName(), company.getEmployees().size()));
        return stats;
    }
}