package com.nameless1620.gradecalc.backend.service;

import com.nameless1620.gradecalc.backend.entity.AssignmentCategory;
import com.nameless1620.gradecalc.backend.repository.AssignmentCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    private AssignmentCategoryRepository localCategoryRepository;

    public CategoryService(AssignmentCategoryRepository parameterCategoryRepository) {
        this.localCategoryRepository = parameterCategoryRepository;
    }

    public List<AssignmentCategory> findAll() {return localCategoryRepository.findAll(); }

    @PostConstruct
    public void populateCategoryData() {
        if(localCategoryRepository.count() == 0) {
            localCategoryRepository.saveAll(
                    Stream.of("Test", "Quiz", "Home/Classwork", "Project", "Labs")
                        .map(name -> {


                            AssignmentCategory assignmentCategory = new AssignmentCategory(name,20, 5, 89);
                          return assignmentCategory;
                        })
                    .collect(Collectors.toList()));
        }
    }
}
