package com.nameless1620.gradecalc.ui.views.gradecalculator;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.backend.entity.AssignmentCategory;
import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.service.CourseService;
import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("GradeCalc | Grade Calculator")
@Route(value = "", layout = MainLayout.class)
public class GradeCalculatorView extends VerticalLayout {

    //declare local variables here
    private final CourseService courseService;
//    List<Assignment> assignments = new ArrayList<Assignment>(); DEPRECATED

    Grid<Course> courseGrid = new Grid<>(Course.class);
    Course lastSelectedCourse = new Course();
    Grid<Assignment> assignmentGrid = new Grid<>(Assignment.class);
    Assignment lastSelectedAssignment = new Assignment();
    Grid<AssignmentCategory> categoryGrid = new Grid<>(AssignmentCategory.class);
    AssignmentCategory lastSelectedAssignmentCategory = new AssignmentCategory();

    ComboBox<String> category = new ComboBox<>("Category");

    //this is the constructor for the class
    public GradeCalculatorView(CourseService courseService)
    {
        addClassName("gradecalculator-view");

        //initial configuration of the view
        this.courseService = courseService;
        configureCourseGrid();
        configureCategoryGrid();
        configureAssignmentGrid();

        //add components to this list to be displayed, order matters!!!
        add(courseGrid, categoryGrid, assignmentGrid);
    }

    private void configureCourseGrid() {
        //COLUMN CONFIG
        courseGrid.setColumns();
        Grid.Column<Course> courseNameColumn = courseGrid
                .addColumn(Course:: getCourseName).setHeader("Course Name");
        Grid.Column<Course> actualGradeColumn = courseGrid
                .addColumn(Course:: getActualGrade).setHeader("Actual Grade");
        Grid.Column<Course> desiredGradeColumn = courseGrid
                .addColumn(Course::getDesiredGrade).setHeader("Desired Grade");
        Grid.Column<Course> assignedWeightageColumn = courseGrid
                .addColumn(Course :: getAssignedWeight).setHeader("Assigned Weightage");
        courseGrid.addClassName("course-grid");

        //DATA PROVIDER, BINDER, EDITOR
        DataProvider<Course, Void> dataProvider =
                DataProvider.fromCallbacks(
                        // First callback fetches items based on a query
                        query -> {
                            // The index of the first item to load
                            int offset = query.getOffset();
                            // The number of items to load
                            int limit = query.getLimit();
                            List<Course> courses = courseService
                                    .fetchCourses(offset, limit);
                            return courses.stream();
                        },
                        // Second callback fetches the total number of items currently in the Grid.
                        // The grid can then use it to properly adjust the scrollbars.
                        query -> courseService.getCourseCount());
        courseGrid.setDataProvider(dataProvider);
        courseGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        Binder<Course> binder = new Binder<>(Course.class);
        courseGrid.getEditor().setBinder(binder);

        //EDITING NAME
        TextField courseNameField = new TextField();
        courseNameField.getElement()
                .addEventListener("keydown",
                        event -> courseGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        binder.forField(courseNameField)
                .bind(Course::getCourseName, Course::setCourseName);
        courseNameColumn.setEditorComponent(courseNameField);

        //EDITING DESIRED GRADE
        TextField courseDesiredGradeField = new TextField();
        courseDesiredGradeField.getElement()
                .addEventListener("keydown",
                        event -> courseGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        binder.forField(courseDesiredGradeField)
                .withConverter(
                        new StringToDoubleConverter("Desired Grade must be a number"))
                .bind(Course::getDesiredGrade, Course::setDesiredGrade);
        desiredGradeColumn.setEditorComponent(courseDesiredGradeField);

        //EVENTS
        //TODO: Figure out how to add single selection listener
        courseGrid.addSelectionListener(selectionEvent ->
                courseSelectionChange(selectionEvent.getAllSelectedItems().stream().findFirst().orElse(null)));
        courseGrid.addItemDoubleClickListener(event -> {
            courseGrid.getEditor().editItem(event.getItem());
            courseNameField.focus();
        });

        //FOOTER ROW
        Button addCourseButton = new Button("Add Course", event -> {
            addCourse();
        });

        Button removeCourseButton = new Button("Remove Course", event ->{
            removeCourse();
        });
        //TODO add logic to show cumulative GPA
//        TextField cumulativeActualGPATextField = new TextField();
//        TextField cumulativeDesiredGPATextField = new TextField();
        FooterRow footerRow = courseGrid.appendFooterRow();
        footerRow.getCell(courseNameColumn).setComponent(addCourseButton);
        footerRow.getCell(actualGradeColumn).setComponent(removeCourseButton);
//        footerRow.getCell(desiredGradeColumn).setComponent(cumulativeActualGPATextField);
//        footerRow.getCell(assignedWeightageColumn).setComponent(cumulativeDesiredGPATextField);
        add(addCourseButton,removeCourseButton);
    }

    private void courseSelectionChange(Course course) {
        //CHANGE SELECTED COURSE
        if (course != null) {
            lastSelectedCourse = course;
            categoryGrid.setItems(course.getAssignmentCategories());
            assignmentGrid.setItems(course.getAssignments());
        }
        //CLEAR SUB-GRIDS
        else {
            categoryGrid.setItems();
            lastSelectedAssignmentCategory = null;
            assignmentGrid.setItems();
            lastSelectedAssignment = null;
        }
    }

    private void configureCategoryGrid(){
        //COLUMN CONFIG
        categoryGrid.setColumns();
        Grid.Column<AssignmentCategory> assignmentCategoryNameColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryName).setHeader("Category");
        Grid.Column<AssignmentCategory> numberOfAssignmentsColumn = categoryGrid
                .addColumn(AssignmentCategory::getNumberOfAssignments).setHeader("Number of Assignments");
        Grid.Column<AssignmentCategory> assignmentAverageColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryAverage).setHeader("Average");
        Grid.Column<AssignmentCategory> assignmentCategoryWeightColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryWeight).setHeader("Weight");

        //BINDER AND EDITOR
        categoryGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        Binder<AssignmentCategory> binder = new Binder<>(AssignmentCategory.class);
        categoryGrid.getEditor().setBinder(binder);

        //CATEGORY NAME EDITOR
        TextField assignmentCategoryNameField = new TextField();
        assignmentCategoryNameField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        if(!event.getOldValue().equals(event.getValue())) {
                            currentCategory().setCategoryName(event.getValue());
                            persistCourseChanges(currentCourse());
                            fullGridRefresh();}}});
        // Close the editor in case of backward between components
        assignmentCategoryNameField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        //TODO setter is redundant with value changed listener
        binder.forField(assignmentCategoryNameField)
                .bind(AssignmentCategory::getCategoryName, AssignmentCategory::setCategoryName);
        assignmentCategoryNameColumn.setEditorComponent(assignmentCategoryNameField);

        //CATEGORY WEIGHT EDITOR
        TextField assignmentCategoryWeightField = new TextField();
        assignmentCategoryWeightField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        if(!event.getOldValue().equals(event.getValue())) {
                            currentCategory().setCategoryWeight(Double.parseDouble(event.getValue()));
                            persistCourseChanges(currentCourse());
                            fullGridRefresh();}}});
        // Close the editor in case of backward between components
        assignmentCategoryWeightField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        //TODO setter is redundant with value changed listener
        binder.forField(assignmentCategoryWeightField)
                .withConverter(new StringToDoubleConverter("Weight must be a number"))
                .bind(AssignmentCategory::getCategoryWeight, AssignmentCategory::setCategoryWeight);
        assignmentCategoryWeightColumn.setEditorComponent(assignmentCategoryWeightField);

        //GRID EVENTS
        //TODO: Figure out how to add single selection listener
        categoryGrid.addSelectionListener(selectionEvent ->
                categorySelectionChange(selectionEvent.getAllSelectedItems().stream().findFirst().orElse(null)));
        categoryGrid.addItemDoubleClickListener(event -> {
            categoryGrid.getEditor().editItem(event.getItem());
            assignmentCategoryNameField.focus();
        });

        //FOOTER ROW
        Button addCategoryButton = new Button("Add Category", event -> {
            addCategory();
        });
        Button removeCategoryButton = new Button("Remove Selected Category", event -> {
            removeCategory();
        });
        Text categoryInstructions = new Text("HINT: Double click a cell to edit");
        FooterRow footerRow = categoryGrid.appendFooterRow();
        footerRow.getCell(assignmentCategoryNameColumn).setComponent(addCategoryButton);
        footerRow.getCell(numberOfAssignmentsColumn).setComponent(removeCategoryButton);
        footerRow.getCell(assignmentAverageColumn).setComponent(categoryInstructions);
        add(addCategoryButton, removeCategoryButton, categoryInstructions);
    }

    private void categorySelectionChange(AssignmentCategory category) {
        if (category != null) {
            lastSelectedAssignmentCategory = category;
        }
        assignmentGrid.setItems(currentCourse()
                .getAssignments()
                .stream()
                .filter(assignment -> assignment.getCategory() == category));
    }

    private void assignmentSelectionChange(Assignment assignment) {
        //TODO: figure out better handling of the deselection when editing fields in grid
        if (assignment != null) {
            lastSelectedAssignment = assignment;
        }
    }

    private void configureAssignmentGrid() {
        //COLUMN CONFIG
        assignmentGrid.setColumns();
        Grid.Column<Assignment> assignmentNameColumn = assignmentGrid
                .addColumn(Assignment::getName).setHeader("Name");
        Grid.Column<Assignment> assignmentCategoryColumn = assignmentGrid
                .addColumn(Assignment::getCategoryName).setHeader("Category");
        Grid.Column<Assignment> assignmentQuestionsColumn = assignmentGrid
                .addColumn(Assignment::getQuestions)
                .setHeader("Questions");
        Grid.Column<Assignment> assignmentWrongQuestionsColumn = assignmentGrid
                .addColumn(Assignment::getWrongQuestions).setHeader("Errors");
        Grid.Column<Assignment> assignmentGradeColumn = assignmentGrid
                .addColumn(Assignment::getGrade).setHeader("Grade");

        //BINDER AND EDITOR
        Binder<Assignment> binder = new Binder<>(Assignment.class);
        assignmentGrid.getEditor().setBinder(binder);
        assignmentGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //ADD AND REMOVE BUTTONS
        Button addAssignmentButton = new Button("Add Assignment", event -> {
            addAssignment();
        });
        Button removeAssignmentButton = new Button("Remove Selected Assignment", event -> {
            removeAssignment();
        });
        Text assignmentInstructions = new Text("HINT: Double click a cell to edit");

        //ASSIGNMENT NAME EDITOR
        TextField assignmentNameField = new TextField();
        assignmentNameField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        if(!event.getOldValue().equals(event.getValue())) {
                            currentAssignment().setName(event.getValue());
                            persistCourseChanges(currentCourse());
                            fullGridRefresh();}}});
        // Close the editor in case of backward between components
        assignmentNameField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        //TODO setter is redundant with value changed listener
        binder.forField(assignmentNameField)
            .bind(Assignment::getName, Assignment::setName);
        assignmentNameColumn.setEditorComponent(assignmentNameField);

        //ASSIGNMENT CATEGORY EDITOR
        Select<String> assignmentCategoryField = new Select<>();
        assignmentGrid.addSelectionListener(
                event -> {
                    assignmentCategoryField.setItems(
                            currentCourse().getAssignmentCategoryNames());
                  //TODO: switch to SingleSelectionEvent
                    assignmentSelectionChange(event.getAllSelectedItems().stream().findFirst().orElse(null));
                });
        assignmentCategoryField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        assignmentCategoryField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        currentAssignment().setCategory(
                                currentCourse().getAssignmentCategoryByName(event.getValue()));
                        persistCourseChanges(currentCourse());
                        fullGridRefresh();
                    }});
        assignmentCategoryColumn.setEditorComponent(assignmentCategoryField);

        //ASSIGNMENT QUESTIONS EDITOR
        TextField assignmentQuestionsField = new TextField();
        assignmentQuestionsField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        if(!event.getOldValue().equals(event.getValue())) {
                            currentAssignment().setQuestions(Double.parseDouble(event.getValue()));
                            persistCourseChanges(currentCourse());
                            fullGridRefresh();
                        }}});
        // Close the editor in case of backward between components
        assignmentQuestionsField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        //TODO setter is redundant with value changed event
        binder.forField(assignmentQuestionsField)
                .withConverter(
                        new StringToDoubleConverter("Questions must be a number"))
                .bind(Assignment::getQuestions, Assignment::setQuestions);
        assignmentQuestionsColumn.setEditorComponent(assignmentQuestionsField);

        //ASSIGNMENT ERRORS EDITOR
        TextField assignmentErrorsField = new TextField();
        assignmentErrorsField.addValueChangeListener(
                event -> {
                    if(event.isFromClient() && event.getValue() != null) {
                        if(!event.getOldValue().equals(event.getValue())) {
                            currentAssignment().setWrongQuestions(Double.parseDouble(event.getValue()));
                            persistCourseChanges(currentCourse());
                            fullGridRefresh();
                        }}});
        // Close the editor in case of backward between components
        assignmentErrorsField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        //TODO setter is redundant with value changed listener
        binder.forField(assignmentErrorsField)
                .withConverter(
                        new StringToDoubleConverter("Errors must be a number"))
                .bind(Assignment::getWrongQuestions, Assignment::setWrongQuestions);
        assignmentWrongQuestionsColumn.setEditorComponent(assignmentErrorsField);

        //EVENTS
        assignmentGrid.addItemDoubleClickListener(event -> {
            assignmentGrid.getEditor().editItem(event.getItem());
            assignmentNameField.focus();
        });

        //FOOTER ROW
        FooterRow footerRow = assignmentGrid.appendFooterRow();
        footerRow.getCell(assignmentNameColumn).setComponent(addAssignmentButton);
        footerRow.getCell(assignmentCategoryColumn).setComponent(removeAssignmentButton);
        footerRow.getCell(assignmentQuestionsColumn).setComponent(assignmentInstructions);
        add(addAssignmentButton, removeAssignmentButton, assignmentInstructions);
    }

    //Currently selected course in the Course grid
    public Course currentCourse() {
        if(lastSelectedCourse != null)
            return lastSelectedCourse;
        else throw new IllegalStateException("No course selected");
    }

    //Currently selected assignment category in the category grid
    public AssignmentCategory currentCategory() {
        if(lastSelectedAssignmentCategory != null)
            return lastSelectedAssignmentCategory;
        else throw new IllegalStateException("No category selected");
    }

    //Currently selected assignment in the assignment grid
    public Assignment currentAssignment() {
        if (lastSelectedAssignment != null) {
            return lastSelectedAssignment;
        }
        else throw new IllegalStateException("No assignment selected");
    }

    private void addAssignment() {
        if (lastSelectedAssignmentCategory != null)
            currentCourse().addAssignments(new Assignment("New Assignment", currentCategory(), 0, 0));
        else
            currentCourse().addAssignments(new Assignment("New Assignment", 0, 0));
        persistCourseChanges(currentCourse());
        fullGridRefresh();
    }

    private void removeAssignment(){
        if (assignmentGrid.asSingleSelect().getValue() != null) {
            currentCourse().removeAssignment(assignmentGrid.asSingleSelect().getValue());
            persistCourseChanges(currentCourse());
        }
        fullGridRefresh();
    }

    private void addCourse() {
        courseService.addCourse(new Course("New Course"));
        //todo correctly implement data provider for course grid
        fullGridRefresh();
    }

    private void removeCourse(){
        if(courseGrid.asSingleSelect().getValue() == null)
            return;
        courseService.removeCourse(courseGrid.asSingleSelect().getValue());
        //todo correctly implement data provider for course grid
        fullGridRefresh();
    }

    private void addCategory() {
        if (lastSelectedCourse != null) {
            currentCourse().addCategories(new AssignmentCategory("New Category", 0,0,  0));
            persistCourseChanges(currentCourse());
        }
        fullGridRefresh();
    }

    private void removeCategory(){
        if (lastSelectedCourse != null) {
            currentCourse().removeCategories(categoryGrid.asSingleSelect().getValue());
            persistCourseChanges(currentCourse());
        }
        fullGridRefresh();
    }

    private void persistCourseChanges(Course course) {
        course.calculateGrades();
        courseService.saveCourse(course);
    }

    private void fullGridRefresh() {
        courseGrid.getDataProvider().refreshAll();
        categoryGrid.getDataProvider().refreshAll();
        assignmentGrid.getDataProvider().refreshAll();
    }
}

