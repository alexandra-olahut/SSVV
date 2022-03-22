package ssvv.example.validation;


import ssvv.example.domain.Grade;
import ssvv.example.domain.Student;
import ssvv.example.domain.Assignment;
import ssvv.example.repository.*;

public class GradeValidator implements Validator<Grade> {
    private StudentXMLRepo studentRepository;
    private AssignmentXMLRepo assignmentRepository;

    /**
     * Class constructor
     * @param studentRepository - ssvv.example.repository student
     * @param assignmentRepository - ssvv.example.repository tema
     */
    public GradeValidator(StudentXMLRepo studentRepository, AssignmentXMLRepo assignmentRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Valideaza o grade
     * @param grade - grade pe care o valideaza
     * @throws ValidationException daca grade nu e valida
     */
    @Override
    public void validate(Grade grade) throws ValidationException {
        Student student = studentRepository.findOne(grade.getIdStudent());
        if (student == null){
            throw new ValidationException("Studentul nu exista!");
        }
        Assignment assignment = this.assignmentRepository.findOne(grade.getIdAssignment());
        if(assignment == null){
            throw new ValidationException("Assignment nu exista!");
        }
        double gradeValue = grade.getGrade();
        if(gradeValue > 10.00 || gradeValue < 0.00){
            throw new ValidationException("Valoarea notei nu este corecta!");
        }
    }
}
