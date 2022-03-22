package ssvv.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.ValidationException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private static String filenameStudent = "fisiere/test/Studenti.xml";
    private static String filenameTema = "fisiere/test/Teme.xml";
    private static String filenameNota = "fisiere/test/Note.xml";

    private StudentValidator studentValidator = new StudentValidator();
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

    private TemaValidator temaValidator = new TemaValidator();
    private TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);

    private NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    private NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    private Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @BeforeEach
    void setUp() throws FileNotFoundException {
        PrintWriter writer1 = new PrintWriter(filenameStudent);
        writer1.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        writer1.close();
        PrintWriter writer2 = new PrintWriter(filenameTema);
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
        Tema tema = new Tema("1", "a", 14, 1);
        Tema ret = service.addTema(tema);
        assertNull(ret);
    }

    @Test
    void addExistingAssignmentShouldReturnAssignment() {
        Tema tema = new Tema("1", "a", 14, 1);
        service.addTema(tema);

        assertDoesNotThrow(() -> {service.addTema(tema);});
        Tema ret = service.addTema(tema);
        assertEquals(ret.getID(), tema.getID());
    }

}
