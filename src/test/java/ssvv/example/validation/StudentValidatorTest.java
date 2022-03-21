package ssvv.example.validation;

import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    private static StudentValidator validator = new StudentValidator();

    @Test
    void validStudentShouldBeValidated() {
        Student student = new Student("1", "Bob the Builder", 912, "bob@gmail.com");
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    void invalidStudentShouldThrowException() {
        Student invalidGradeStudent = new Student("1", "Donald Duck", -34, "dd@email.com");
        assertThrows(ValidationException.class, () -> validator.validate(invalidGradeStudent));
    }

}
