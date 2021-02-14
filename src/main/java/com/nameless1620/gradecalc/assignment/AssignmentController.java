//package com.nameless1620.gradecalc.assignment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/v1/assignment")
//public class AssignmentController {
//
//    private final AssignmentService assignmentService;
//
//    @Autowired
//    public AssignmentController(AssignmentService assignmentService) { this.assignmentService = assignmentService; }
//
//    @GetMapping("/assignments")
//    public List<Assignment> getAssignments() { return assignmentService.getAssignments(); }
//
//    @GetMapping("{assignmentId}")
//    public Optional<Assignment> getAssignment(@PathVariable Long assignmentId) {
//        return assignmentService.getAssignment(assignmentId); }
//
//    @PostMapping
//    public void addAssignment(@RequestBody Assignment assignment) {
//        assignmentService.addAssignment(assignment);
//    }
//
//    @DeleteMapping("{assignmentId")
//    public void deleteAssignment(@PathVariable Long assignmentId) {
//        assignmentService.deleteAssignment(assignmentId);
//    }
//
//    @PutMapping("{assignmentId")
//    public void updateAssignment(
//            @PathVariable Long assignmentId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) LocalDate date,
//            @RequestParam(required = false) int score) {
//        assignmentService.updateAssignment(assignmentId, name, type, date, score);
//    }
//
//    @PutMapping("/put/{assignmentId")
//    public ResponseEntity<Object> updateAssignment(
//            @PathVariable Long assignmentId,
//            @RequestBody Assignment assignment) {
//        return assignmentService.updateAssignment(assignmentId, assignment);
//    }
//
//}
