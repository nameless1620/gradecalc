package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.service.CourseService;
import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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

    //TODO: Add a method to delete an assignment (hint, look at add assignment)
    //TODO: Add a method to update an assignment (hint, look at add assignment)
    //TODO: Add a way to categorize an assignment (hint, you will need to update backend.entity.Assignment.java)




    private void updateCourseList() {
        courseGrid.getDataProvider().refreshAll();
        updateAssignmentList();
    }


    private void updateAssignmentList(){
        Course selectedCourse = courseGrid.asSingleSelect().getValue();
        if(selectedCourse != null){
            assignmentGrid.setItems(selectedCourse.getAssignments());
            category.setItems(selectedCourse.getAssignmentCategories());
        }

    //    Set<Course> courses = courseGrid.getSelectedItems();
/*
        PersonService personService = new PersonService();
        List<Person> personList = personService.fetchAll();

        H3 firstHeader = new H3("Grid with single select");
        Grid<Person> firstGrid = new Grid<>();
        firstGrid.setItems(personList);

        H3 secondHeader = new H3("Grid with multi select");
        Grid<Person> secondGrid = new Grid<>();
        secondGrid.setItems(personList);
        secondGrid.setSelectionMode(SelectionMode.MULTI);

        TextField filterField = new TextField();
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(event -> {
            Optional<Person> foundPerson = personList.stream()
                    .filter(person -> person.getFirstName().toLowerCase()
                            .startsWith(event.getValue().toLowerCase()))
                    .findFirst();

            firstGrid.asSingleSelect().setValue(foundPerson.orElse(null));

            secondGrid.getSelectionModel().deselectAll();
            Set<Person> foundpersons = personList.stream()
                    .filter(person -> person.getFirstName().toLowerCase()
                            .startsWith(event.getValue().toLowerCase()))
                    .collect(Collectors.toSet());
            secondGrid.asMultiSelect().setValue(foundpersons);
        });

        firstGrid.addColumn(Person::getFirstName).setHeader("First Name");
        firstGrid.addColumn(Person::getAge).setHeader("Age");

        secondGrid.addColumn(Person::getFirstName).setHeader("First Name");
        secondGrid.addColumn(Person::getAge).setHeader("Age");

        NativeButton deselectBtn = new NativeButton("Deselect all");
        deselectBtn.addClickListener(
                event -> secondGrid.asMultiSelect().deselectAll());
        NativeButton selectAllBtn = new NativeButton("Select all");
        selectAllBtn.addClickListener(
                event -> ((GridMultiSelectionModel<Person>) secondGrid
                        .getSelectionModel()).selectAll());
        add(filterField, firstHeader, firstGrid, secondHeader, secondGrid,
                selectAllBtn, deselectBtn);

        /*



        if(courses.size() > 0){
            Iterator<Course> iter = courses.iterator();
            Course selectedCourse = iter.next();
            assignmentGrid.setItems(selectedCourse.getAssignments());
        }
         */


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
    private void deleteAssignment(){

        Set<Assignment> edit = assignmentGrid.getSelectedItems();
        for(Iterator<Assignment> assignmentIterator = edit.iterator(); assignmentIterator.hasNext(); ){
            Assignment selected = assignmentIterator.next();
            assignments.remove(selected);
        }
    }

    private void updateAssignment(){

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

    private void configureAssignmentGrid() {
        assignmentGrid.setColumns("name", "category", "questions", "wrongQuestions","grade");
//        Grid.Column<Assignment> nameColumn = assignmentGrid.addColumn(Assignment::getQuestions)
//                .setHeader("Name");
//        Grid.Column<Assignment> questionsColumn = assignmentGrid.addColumn(Assignment::getQuestions)
//                .setHeader("Questions");
//        Grid.Column<Assignment> wrongQuestionsColumn = assignmentGrid.addColumn(Assignment::getQuestions)
//                .setHeader("Wrong Questions");
//        Binder<Assignment> binder = new Binder<>(Assignment.class);
//        assignmentGrid.getEditor().setBinder(binder);
//
//        TextField assignmentNameField = new TextField();
//        TextField assignmentQuestionsField = new TextField();
//        TextField assignmentWrongQuestionsField = new TextField();
//
//        assignmentNameField.getElement()
//                .addEventListener("keydown",
//                        event -> assignmentGrid.getEditor().cancel())
//                .setFilter("event.key === 'Tab' && event.shiftKey");
//        binder.bind(assignmentNameField, "name");
//        nameColumn.setEditorComponent(assignmentNameField);
//
//        assignmentGrid.addItemDoubleClickListener(event -> {
//            assignmentGrid.getEditor().editItem(event.getItem());
//            assignmentNameField.focus();
//        });
//
//        binder.addValueChangeListener(event -> {
//            assignmentGrid.getEditor().refresh();
//        });

      //  assignmentGrid.setItems(assignments);
        updateAssignmentList();
    }

}

