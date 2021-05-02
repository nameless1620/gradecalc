package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.service.CourseService;
import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

    //declare local variables here
    private final CourseService courseService;
    List<Assignment> assignments = new ArrayList<Assignment>();

    Grid<Course> courseGrid = new Grid<>(Course.class);
    Grid<Assignment> assignmentGrid = new Grid<>(Assignment.class);

    ComboBox<String> category = new ComboBox<>("Category");

    //this is the constructor for the class
    public JoshikaSandboxView(CourseService courseService)
    {
        addClassName("joshikasandbox-view");

        //initial configuration of the view
        this.courseService = courseService;
        configureCourseGrid();
        configureAssignmentGrid();

        //entry fields for assignments
        TextField assignment = new TextField("Assignment","Ex: Test 1");
        TextField questions = new TextField("Number of Questions", "Ex: 35");
        TextField wrongQuestions = new TextField("Number of Wrong Questions","Ex: 4");
        Button addAssignment = new Button("Add Assignment", event -> {
            addAssignment(
                    assignment.getValue(),
                    category.getValue(),
                    questions.getValue(),
                    wrongQuestions.getValue());
            assignmentGrid.getDataProvider().refreshAll();
        });
        Button deleteAssignment = new Button("Remove Assignment", buttonClickEvent -> {
            deleteAssignment();
            assignmentGrid.getDataProvider().refreshAll();
        });
        Button updateAssignment = new Button("Edit Assignment", buttonClickEvent -> {
            updateAssignment();
        });

        //add components to this list to be displayed, order matters!!!
        add(courseGrid, assignmentGrid, assignment, category, questions, wrongQuestions, addAssignment, deleteAssignment, updateAssignment);
        updateCourseList();
    }

    private void configureAssignmentGrid() {
        assignmentGrid.setColumns();
        Grid.Column<Assignment> assignmentNameColumn = assignmentGrid
                .addColumn(Assignment::getName).setHeader("Name");
        Grid.Column<Assignment> assignmentCategoryColumn = assignmentGrid
                .addColumn(Assignment::getCategory).setHeader("Category");
        Grid.Column<Assignment> assignmentQuestionsColumn = assignmentGrid
                .addColumn(Assignment::getQuestions).setHeader("Questions");
        Grid.Column<Assignment> assignmentWrongQuestionsColumn = assignmentGrid
                .addColumn(Assignment::getWrongQuestions).setHeader("Errors");
        Grid.Column<Assignment> assignmentGradeColumn = assignmentGrid
                .addColumn(Assignment::getGrade).setHeader("Grade");
        Button addAssignmentButton = new Button("Add Assignment", event -> {
            addAssignment("New Assignment", "Math", "0", "0");
        });
        FooterRow footerRow = assignmentGrid.appendFooterRow();
        footerRow.getCell(assignmentNameColumn).setComponent(addAssignmentButton);
        add(addAssignmentButton);
        updateAssignmentList();
    }

    private void updateAssignmentList(){
        Course selectedCourse = courseGrid.asSingleSelect().getValue();
        if(selectedCourse != null){
            assignmentGrid.setItems(selectedCourse.getAssignments());
            category.setItems(selectedCourse.getAssignmentCategories());
        }
    }

    private void addAssignment(String name, String category, String questions, String wrongQuestions) {
        Assignment assignment = new Assignment(name, category, Double.parseDouble(questions), Double.parseDouble(wrongQuestions));
        //   assignments.add(assignment);
        Set<Course> courses = courseGrid.getSelectedItems();
        Iterator<Course> iter = courses.iterator();
        Course selectedCourse = iter.next();
        selectedCourse.addAssignments(assignment);
        updateCourseList();
    }

    private void updateAssignment(){

    }

    private void deleteAssignment(){

        Set<Assignment> edit = assignmentGrid.getSelectedItems();
        for(Iterator<Assignment> assignmentIterator = edit.iterator(); assignmentIterator.hasNext(); ){
            Assignment selected = assignmentIterator.next();
            assignments.remove(selected);
        }
    }

    private void configureCourseGrid() {
        courseGrid.addClassName("course-grid");
        courseGrid.removeColumnByKey("assignments");
        courseGrid.setColumns("courseName", "actualGrade", "desiredGrade", "testGradeAverage");
        courseGrid.setItems(courseService.findAll());
        courseGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        courseGrid.asSingleSelect().addValueChangeListener(event -> {
            updateAssignmentList();
        });
    }

    private void updateCourseList() {
        courseGrid.getDataProvider().refreshAll();
        updateAssignmentList();
    }

}

