package ssvv.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;
import ssvv.example.domain.Assignment;
import ssvv.example.repository.GradeXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.AssignmentXMLRepo;
import ssvv.example.validation.AssignmentValidator;
import ssvv.example.validation.GradeValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.ValidationException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private static String filenameStudent = "files/test/Students.xml";
    private static String filenameAssignment = "files/test/Assignments.xml";
    private static String filenameGrade = "files/test/Grades.xml";

    private StudentValidator studentValidator = new StudentValidator();
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

    private AssignmentValidator assignmentValidator = new AssignmentValidator();
    private AssignmentXMLRepo assignmentXMLRepository = new AssignmentXMLRepo(filenameAssignment);

    private GradeValidator gradeValidator = new GradeValidator(studentXMLRepository, assignmentXMLRepository);
    private GradeXMLRepo gradeXMLRepository = new GradeXMLRepo(filenameGrade);

    private Service service = new Service(studentXMLRepository, studentValidator, assignmentXMLRepository, assignmentValidator, gradeXMLRepository, gradeValidator);

    @BeforeEach
    void setUp() throws FileNotFoundException {
        PrintWriter writer1 = new PrintWriter(filenameStudent);
        writer1.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        writer1.close();
        PrintWriter writer2 = new PrintWriter(filenameAssignment);
        writer2.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        writer2.close();
    }


    /**
     *      ADD STUDENT
     */

    @Test
    void addValidStudentShouldReturnNull() {
        Student student = new Student("1", "name", 1, "email");
        Student ret = service.addStudent(student);
        assertNull(ret);
    }

    @Test
    void addNullIdStudentShouldThrowException() {
        Student student = new Student(null, "name", 1, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }
    @Test
    void addEmptyIdStudentShouldThrowException() {
        Student student = new Student("", "name", 1, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    @Test
    void addNullNameStudentShouldThrowException() {
        Student student = new Student("1", null, 1, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }
    @Test
    void addEmptyNameStudentShouldThrowException() {
        Student student = new Student("1", "", 1, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    @Test
    void addNullEmailStudentShouldThrowException() {
        Student student = new Student("1", "name", 1, null);
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }
    @Test
    void addEmptyEmailStudentShouldThrowException() {
        Student student = new Student("1", "name", 1, "");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    @Test
    void addNegativeGroupStudentShouldThrowException() {
        Student student = new Student("1", "name", -1, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }
    @Test
    void addGroup0StudentShouldThrowException() {
        Student student = new Student("1", "name", 0, "email");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
    }

    @Test
    void addExistingStudentShouldReturnStudent() {
        Student student = new Student("11", "a", 1, "a");
        service.addStudent(student);

        assertDoesNotThrow(() -> {service.addStudent(student);});
        Student ret = service.addStudent(student);
        assertEquals(ret, student);
    }


    /**
     *              ADD ASSIGNMENT
     */

    @Test
    void addValidAssignmentShouldReturnNull() {
        Assignment assignment = new Assignment("1", "a", 14, 1);
        Assignment ret = service.addAssignment(assignment);
        assertNull(ret);
    }

    @Test
    void addExistingAssignmentShouldReturnAssignment() {
        Assignment assignment = new Assignment("1", "a", 14, 1);
        service.addAssignment(assignment);

        assertDoesNotThrow(() -> {service.addAssignment(assignment);});
        Assignment ret = service.addAssignment(assignment);
        assertEquals(ret.getID(), assignment.getID());
    }

}
