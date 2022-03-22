package ssvv.example.service;

import ssvv.example.curent.Current;
import ssvv.example.domain.Assignment;
import ssvv.example.domain.Grade;
import ssvv.example.domain.Student;

import ssvv.example.repository.*;
import ssvv.example.validation.AssignmentValidator;
import ssvv.example.validation.GradeValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.ValidationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clasa Service
 */
public class Service {
    //private StudentFileRepository studentFileRepository;
    private StudentXMLRepo studentRepository;
    private StudentValidator studentValidator;
    //private AssignmentFileRepository temaFileRepository;
    private AssignmentXMLRepo assignmentRepository;
    private AssignmentValidator assignmentValidator;
    //private GradeFileRepository notaFileRepository;
    private GradeXMLRepo gradeRepository;
    private GradeValidator gradeValidator;

    /**
     * Class Constructor
     * @param studentRepository - ssvv.example.repository student
     * @param studentValidator - validator student
     * @param assignmentRepository - ssvv.example.repository tema
     * @param assignmentValidator - validator tema
     * @param GradeRepository - ssvv.example.repository nota
     * @param gradeValidator - validator nota
     */
    //public Service(StudentFileRepository studentFileRepository, StudentValidator studentValidator, AssignmentFileRepository temaFileRepository, AssignmentValidator assignmentValidator, GradeFileRepository notaFileRepository, GradeValidator gradeValidator) {
    public Service(StudentXMLRepo studentRepository, StudentValidator studentValidator, AssignmentXMLRepo assignmentRepository, AssignmentValidator assignmentValidator, GradeXMLRepo GradeRepository, GradeValidator gradeValidator) {

        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
        this.assignmentRepository = assignmentRepository;
        this.assignmentValidator = assignmentValidator;
        this.gradeRepository = GradeRepository;
        this.gradeValidator = gradeValidator;
    }

    /**
     * adauga un Student in memorie
     * @param student - studentul pe care il adauga
     * @return null daca studentul a fost adaugat cu succes sau studentul din memorie daca acesta exista deja
     */
    public Student addStudent(Student student) {
        studentValidator.validate(student);
        return studentRepository.save(student);
    }

    /**
     * Sterge un student
     * @param id - id-ul studentului
     * @return studentul daca acesta a fost sters sau null daca studentul nu exista
     */
    public Student deleteStudent(String id){
        if(id == null || id.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return studentRepository.delete(id);
    }

    /**
     * Cauta un student dupa id
     * @param id - id-ul studentului
     * @return studentul daca acesta exista sau null altfel
     */
    public Student findStudent(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return studentRepository.findOne(id);
    }

    /**
     * Modifica un student
     * @param student - noul student
     * @return noul student daca s-a facut modificarea sau null daca acesta nu exista
     */
    public Student updateStudent(Student student){
        studentValidator.validate(student);
        return studentRepository.update(student);
    }

    /**
     * @return toti studentii din memorie
     */
    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     * Adauga o assignment noua
     * @param assignment  - assignment pe care o adauga
     * @return null daca s-a facut adaugarea sau assignment daca aceasta exista deja
     */
    public Assignment addAssignment(Assignment assignment){
        assignmentValidator.validate(assignment);
        return assignmentRepository.save(assignment);
    }

    /**
     * Sterge o tema
     * @param nrTema - nr-ul temei
     * @return tema daca aceasta a fost stearsa sau null daca tema nu exista
     */
    public Assignment deleteAssignment(String nrTema){
        if(nrTema == null || nrTema.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return assignmentRepository.delete(nrTema);
    }

    /**
     * Cauta o tema
     * @param id - id-ul temei
     * @return tema sau null daca aceasta nu exista
     */
    public Assignment findAssignment(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }return assignmentRepository.findOne(id);
    }

    /**
     * Modifica o assignment
     * @param assignment - noua assignment
     * @return assignment daca s-a facut modificarea sau null daca acesta nu exisra
     */
    public Assignment updateAssignment(Assignment assignment){
        assignmentValidator.validate(assignment);
        return assignmentRepository.update(assignment);
    }

    /**
     * @return toate temele din memorie
     */
    public Iterable<Assignment> getAllAssignments(){
        return assignmentRepository.findAll();
    }

    /**
     * Adauga o grade
     * @param grade - grade
     * @param feedback - feedback-ul notei
     * @return null daca grade a fost adaugata sau grade daca aceasta exista deja
     */
    public double addGrade(Grade grade, String feedback){
        gradeValidator.validate(grade);
        Student student = studentRepository.findOne(grade.getIdStudent());
        Assignment assignment = assignmentRepository.findOne(grade.getIdAssignment());

        int submittedWeek = calculateSubmissionWeek(grade.getDate());
        if(submittedWeek > assignment.getDeadline()){
            if (submittedWeek- assignment.getDeadline() == 1){
                grade.setGrade(grade.getGrade()-2.5);
            }
            else{
                throw new ValidationException("Studentul nu mai poate preda aceasta assignment!");
            }
        }
        gradeRepository.save(grade);

        String filename = "files/students/" + student.getName() + ".txt";
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))){
            bufferedWriter.write("\nAssignment: " + assignment.getID());
            bufferedWriter.write("\nGrade: " + grade.getGrade());
            bufferedWriter.write("\nSubmitted in week: " + submittedWeek);
            bufferedWriter.write("\nDeadline: " + assignment.getDeadline());
            bufferedWriter.write("\nFeedback: " +feedback);
            bufferedWriter.newLine();
        } catch (IOException exception){
            throw new ValidationException(exception.getMessage());
        }
        return grade.getGrade();
    }

    /**
     * Sterge o nota
     * @param id - id-ul notei
     * @return nota daca aceasta a fost stearsa sau null daca nota nu exista
     */
    public Grade deleteGrade(String id){
        if(id == null || id.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return gradeRepository.delete(id);
    }

    /**
     * Cauta o nota
     * @param id - id-ul notei
     * @return nota sau null daca aceasta nu exista
     */
    public Grade findGrade(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return gradeRepository.findOne(id);
    }

    /**
     * @return toate notele
     */
    public Iterable<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

    /**
     * Prelungeste deadline-ul unei teme
     * @param nrTema - nr-ul temei
     * @param deadline - noul deadline
     */
    public void extendDeadline(String nrTema, int deadline){
        int currentWeek= Current.getCurrentWeek();
        Assignment assignment = assignmentRepository.findOne(nrTema);
        if(assignment == null){
            throw new ValidationException("Assignment inexistenta!");
        }
        if(assignment.getDeadline() >= currentWeek) {
            assignment.setDeadline(deadline);
            assignmentRepository.writeToFile();
        }
        else{
            throw new ValidationException("Nu se mai poate prelungi deadline-ul!");
        }
    }

    /**
     * Calculeaza saptamana de predare
     * @param submitted - data predarii unei teme
     * @return saptamana in care a fost predata tema
     */
    private int calculateSubmissionWeek(LocalDate submitted) {
        LocalDate startDate = Current.getStartDate();
        long days = DAYS.between(startDate, submitted);
        double submittedWeek = Math.ceil((double)days/7);
        return (int)submittedWeek;
    }
}
