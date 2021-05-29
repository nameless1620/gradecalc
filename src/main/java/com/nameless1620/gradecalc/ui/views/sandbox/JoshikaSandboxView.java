package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.backend.entity.AssignmentCategory;
import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.service.CategoryService;
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
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.expression.spel.ast.Assign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

    //declare local variables here
    private final CourseService courseService;
//    List<Assignment> assignments = new ArrayList<Assignment>(); DEPRECATED

    Grid<Course> courseGrid = new Grid<>(Course.class);
    Grid<Assignment> assignmentGrid = new Grid<>(Assignment.class);
    Grid<AssignmentCategory> categoryGrid = new Grid<>(AssignmentCategory.class);
    Assignment lastSelectedAssignment = new Assignment();

    ComboBox<String> category = new ComboBox<>("Category");

    //this is the constructor for the class
    public JoshikaSandboxView(CourseService courseService)
    {
        addClassName("joshikasandbox-view");

        //initial configuration of the view
        this.courseService = courseService;
        configureCourseGrid();
        configureAssignmentGrid();
        configureCategoryGrid();

        //add components to this list to be displayed, order matters!!!
        add(courseGrid, categoryGrid, assignmentGrid);
        updateCourseList();
    }

    private void configureCategoryGrid(){
        categoryGrid.setColumns();
        Grid.Column<AssignmentCategory> assignmentNameColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryName).setHeader("Category");
        Grid.Column<AssignmentCategory> assignmentCategoryColumn = categoryGrid
                .addColumn(AssignmentCategory::getNumberOfAssignments).setHeader("Number of Assignments");
        Grid.Column<AssignmentCategory> assignmentAverageColumn = categoryGrid
                .addColumn(AssignmentCategory::getCategoryAverage).setHeader("Average");
        categoryGrid.asSingleSelect().addValueChangeListener(event -> {
            updateAssignmentList();
        });


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

        Button addAssignmentButton = new Button("Add Assignment", event -> {
            addAssignment("New Assignment",
                    categoryGrid.asSingleSelect().getValue(),
                    "0",
                    "0");
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

        Select<AssignmentCategory> assignmentCategoryField = new Select<>();
        //TODO think through null value handling / data strategy
        assignmentGrid.addSelectionListener(
                event -> {
                    assignmentCategoryField.setItems(
//                            courseGrid.asSingleSelect().getValue().getAssignmentCategoryNames());
                            courseGrid.asSingleSelect().getValue().getAssignmentCategories());
                    lastSelectedAssignment = assignmentGrid.asSingleSelect().getValue();
                }
        );
//        if(courseGrid.asSingleSelect().getValue() != null) {
//            assignmentCategoryField.setItems(
//                    courseGrid.asSingleSelect().getValue().getAssignmentCategoryNames()
//            );
//        }
        assignmentCategoryField.getElement()
                .addEventListener("keydown",
                        event -> assignmentGrid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");
        assignmentCategoryField.addValueChangeListener(
                event -> {
                    if (assignmentGrid.asSingleSelect().getValue() != null)
                        assignmentGrid.asSingleSelect().getValue().setCategory(event.getValue());
                    if (lastSelectedAssignment != null)
                        lastSelectedAssignment.setCategory(event.getValue());
                }
        );
//        binder.forField(assignmentCategoryField)
//                .bind(Assignment::getCategoryName, Assignment::setCategory);
        binder.forField(assignmentCategoryField)
                .bind(Assignment::getCategory, Assignment::setCategory);

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
        updateAssignmentList();
    }

    private void updateAssignmentList(){
        Course selectedCourse = courseGrid.asSingleSelect().getValue();
        if(selectedCourse != null){
            assignmentGrid.setItems(selectedCourse.getAssignments());
 //           category.setItems(selectedCourse.getAssignmentCategories());
        }
    }

    private void updateCategoryList(){
        Course selectedCourse = courseGrid.asSingleSelect().getValue();
        if(selectedCourse != null){
            categoryGrid.setItems(selectedCourse.getAssignmentCategories());
        }

//        assignmentCategoryField.setItems(
//                courseGrid.asSingleSelect().getValue().getAssignmentCategoryNames();
    }

    private void addAssignment(String name, AssignmentCategory category, String questions, String wrongQuestions) {
        courseGrid.asSingleSelect().getValue().addAssignments(
                new Assignment(name, category, Double.parseDouble(questions), Double.parseDouble(wrongQuestions))
        );
        assignmentGrid.getDataProvider().refreshAll();
    }

    private void updateAssignment(Assignment assignment){

    }

    private void removeAssignment(){
        courseGrid.asSingleSelect().getValue().removeAssignment(assignmentGrid.asSingleSelect().getValue());
        assignmentGrid.getDataProvider().refreshAll();
    }

    private void configureCourseGrid() {
        courseGrid.addClassName("course-grid");
        courseGrid.removeColumnByKey("assignments");
        courseGrid.setColumns("courseName", "actualGrade", "desiredGrade", "testGradeAverage");
        courseGrid.setItems(courseService.findAll());
        courseGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        courseGrid.asSingleSelect().addValueChangeListener(event -> {
            updateCategoryList();
        });
    }

    private void updateCourseList() {
        //TODO: stop using this for refresh
        courseGrid.getDataProvider().refreshAll();
        updateAssignmentList();
    }
}

