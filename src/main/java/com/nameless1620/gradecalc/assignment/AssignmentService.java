package com.nameless1620.gradecalc.assignment;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) { this.assignmentRepository = assignmentRepository; }

    public List<Assignment> getAssignments() { return assignmentRepository.findAll(); }

    public Optional<Assignment> getAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (!assignment.isPresent())
            throw new IllegalStateException("no assignment found with id " + assignmentId);
        return assignment;
    }

    public void addAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long assignmentId) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if (!optionalAssignment.isPresent())
            throw new IllegalStateException("cannot delete nonexistent assignment " + assignmentId);
        assignmentRepository.deleteById(assignmentId);
    }

    @Transactional
    public void updateAssignment(Long assignmentId,
                                 String name,
                                 String type,
                                 LocalDate date,
                                 int score) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new IllegalStateException(
                "cannot update nonexistent assignment " + assignmentId));
        if(name != null && name.length() > 0)
            assignment.setName(name);
        if(type != null && type.length() > 0)
            assignment.setType(type);
        if(date != null)
            assignment.setDate(date);
        assignment.setScore(score);
    }

    @Transactional
    public ResponseEntity<Object> updateAssignment(
            Long assignmentId,
            Assignment assignment) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);
        if (!assignmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        assignment.setId(assignmentId);
        assignmentRepository.save(assignment);
        return ResponseEntity.noContent().build();
    }


}
