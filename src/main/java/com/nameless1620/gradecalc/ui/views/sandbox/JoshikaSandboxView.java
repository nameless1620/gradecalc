package com.nameless1620.gradecalc.ui.views.sandbox;

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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

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
    public JoshikaSandboxView(CourseService courseService)
    {
        addClassName("joshikasandbox-view");

        //initial configuration of the view
        this.courseService = courseService;
        configureCourseGrid();
        configureCategoryGrid();
        configureAssignmentGrid();

        //add components to this list to be displayed, order matters!!!
        add(courseGrid, categoryGrid, assignmentGrid);
    }

    private void configureCourseGrid() {
        courseGrid.addClassName("course-grid");
        courseGrid.removeColumnByKey("assignments");
        courseGrid.setColumns("courseName", "actualGrade", "desiredGrade", "testGradeAverage");
        courseGrid.setItems(courseService.findAll());
        courseGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        courseGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //TODO: Figure out how to add single selection listener
        courseGrid.addSelectionListener(selectionEvent ->
                courseSelectionChange(selectionEvent.getAllSelectedItems().stream().findFirst().orElse(null)));

//        courseGrid.asSingleSelect().addValueChangeListener(event -> {
//            updateCategoryList();
//        });
    }

    private void courseSelectionChange(Course course) {
        if (course != null) {
            lastSelectedCourse = course;
            categoryGrid.setItems(course.getAssignmentCategories());
            assignmentGrid.setItems(course.getAssignments());
        }
        else {
            categoryGrid.setItems();
            lastSelectedAssignmentCategory = null;
            assignmentGrid.setItems();
            lastSelectedAssignment = null;
        }
    }

    private void configureCategoryGrid(){
        categoryGrid.setColumns();
        Grid.Column<AssignmentCategory> assignmentNameColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryName).setHeader("Category");
        Grid.Column<AssignmentCategory> assignmentCategoryColumn = categoryGrid
                .addColumn(AssignmentCategory::getNumberOfAssignments).setHeader("Number of Assignments");
        Grid.Column<AssignmentCategory> assignmentAverageColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryAverage).setHeader("Average");
        Grid.Column<AssignmentCategory> assignmentWeightColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryWeight).setHeader("Weight");
        categoryGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        //TODO: Figure out how to add single selection listener
        categoryGrid.addSelectionListener(selectionEvent ->
                categorySelectionChange(selectionEvent.getAllSelectedItems().stream().findFirst().orElse(null)));

//        categoryGrid.asSingleSelect().addValueChangeListener(event -> {
//            updateAssignmentList();
//        });
    }

    private void categorySelectionChange(AssignmentCategory category) {
        lastSelectedAssignmentCategory = category;
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
        assignmentGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

//        //TODO: Figure out how to add single selection listener
//        assignmentGrid.addSelectionListener(selectionEvent ->
//                assignmentSelectionChange(selectionEvent.getAllSelectedItems().stream().findFirst().orElse(null)));

        Button addAssignmentButton = new Button("Add Assignment", event -> {
            addAssignment();
        });
        Button removeAssignmentButton = new Button("Remove Selected Assignment", event -> {
            removeAssignment();
        });
        Text assignmentInstructions = new Text("HINT: Double click a cell to edit");

        Binder<Assignment> binder = new Binder<>(Assignment.class);
        assignmentGrid.getEditor().setBinder(binder);

        TextField assignmentNameField = new TextField();
        // Close the editor in case of backward between components
        assignmentNameField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        binder.forField(assignmentNameField)
            .bind(Assignment::getName, Assignment::setName);
        assignmentNameColumn.setEditorComponent(assignmentNameField);

        Select<String> assignmentCategoryField = new Select<>();
        assignmentGrid.addSelectionListener(
                event -> {
                    assignmentCategoryField.setItems(
                            currentCourse().getAssignmentCategoryNames());
                  //TODO: switch to SingleSelectionEvent
                    assignmentSelectionChange(event.getAllSelectedItems().stream().findFirst().orElse(null));
                }
        );

        assignmentCategoryField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        assignmentCategoryField.addValueChangeListener(
                event -> {
                    if (event.getValue() != null)
                        currentAssignment().setCategory(
                            currentCourse().getAssignmentCategoryByName(event.getValue()));
                }
        );
        assignmentCategoryColumn.setEditorComponent(assignmentCategoryField);

        TextField assignmentQuestionsField = new TextField();
        // Close the editor in case of backward between components
        assignmentQuestionsField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        binder.forField(assignmentQuestionsField)
                .withConverter(
                        new StringToDoubleConverter("Questions must be a number"))
                .bind(Assignment::getQuestions, Assignment::setQuestions);
        assignmentQuestionsColumn.setEditorComponent(assignmentQuestionsField);

        TextField assignmentErrorsField = new TextField();
        // Close the editor in case of backward between components
//        assignmentErrorsField.getElement()
//                .addEventListener("close"),
//                    event -> assignmentGrid.
        assignmentErrorsField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        binder.forField(assignmentErrorsField)
                .withConverter(
                        new StringToDoubleConverter("Errors must be a number"))
                .bind(Assignment::getWrongQuestions, Assignment::setWrongQuestions);
        assignmentWrongQuestionsColumn.setEditorComponent(assignmentErrorsField);

        assignmentGrid.addItemDoubleClickListener(event -> {
            assignmentGrid.getEditor().editItem(event.getItem());
            assignmentNameField.focus();
        });

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
        if (currentCategory() != null)
            currentCourse().addAssignments(new Assignment("New Assignment", currentCategory(), 0, 0));
        else
            currentCourse().addAssignments(new Assignment("New Assignment", 0, 0));
        assignmentGrid.getDataProvider().refreshAll();
    }

    private void removeAssignment(){
        currentCourse().removeAssignment(assignmentGrid.asSingleSelect().getValue());
        assignmentGrid.getDataProvider().refreshAll();
    }
}

