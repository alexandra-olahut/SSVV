package ssvv.example.validation;

import org.junit.jupiter.api.Test;
import ssvv.example.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    private static StudentValidator validator = new StudentValidator();

    @Test
    void validStudentShouldBeValidated() {
        Student student = new Student("1", "name", 935, "email");
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    void invalidGroupShouldThrowException() {
        Student invalidGroupStudent = new Student("1", "name", -1, "email");
        assertThrows(ValidationException.class, () -> validator.validate(invalidGroupStudent));
    }

}
