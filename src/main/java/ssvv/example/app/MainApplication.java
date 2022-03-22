package ssvv.example.app;


import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.AssignmentXMLRepo;
import ssvv.example.repository.GradeXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.AssignmentValidator;
import ssvv.example.validation.GradeValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.view.UI;



public class MainApplication {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        AssignmentValidator assignmentValidator = new AssignmentValidator();
        String filenameStudent = "files/Students.xml";
        String filenameAssignment = "files/Assignments.xml";
        String filenameGrade = "files/Grades.xml";

       //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //AssignmentFileRepository temaFileRepository = new AssignmentFileRepository(filenameAssignment);
        //GradeValidator gradeValidator = new GradeValidator(studentFileRepository, temaFileRepository);
        //GradeFileRepository notaFileRepository = new GradeFileRepository(filenameGrade);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        AssignmentXMLRepo assignmentXMLRepository = new AssignmentXMLRepo(filenameAssignment);
        GradeValidator gradeValidator = new GradeValidator(studentXMLRepository, assignmentXMLRepository);
        GradeXMLRepo gradeXMLRepository = new GradeXMLRepo(filenameGrade);
        Service service = new Service(studentXMLRepository, studentValidator, assignmentXMLRepository, assignmentValidator, gradeXMLRepository, gradeValidator);
        UI ui = new UI(service);
        ui.run();
    }

}
