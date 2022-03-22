package ssvv.example.view;

import ssvv.example.domain.Assignment;
import ssvv.example.domain.Grade;
import ssvv.example.domain.Student;
import ssvv.example.service.Service;
import ssvv.example.validation.ValidationException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interfata utilizator de tip consola
 */
public class UI {
    private Service service;

    /**
     * Class constructor
     * @param service - ssvv.example.service-ul clasei
     */
    public UI(Service service) {
        this.service = service;
    }

    /**
     * Metoda care ruleaza aplicatia
     */
    public void run() {
        System.out.println("Bine ati venit!");
        while (true) {
            try {
                System.out.println("Meniu comenzi: ");
                System.out.println("0.Exit");
                System.out.println("1.Comenzi student");
                System.out.println("2.Comenzi teme");
                System.out.println("3.Comenzi note");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Introduceti comanda: ");
                int comanda = scanner.nextInt();
                if (comanda == 0) {
                    System.out.println("La revedere!");
                    break;
                } else if (comanda == 1) {
                    studentMenu();
                } else if (comanda == 2) {
                    assignmntsMenu();
                } else if (comanda == 3) {
                    gradesMenu();
                } else {
                    System.out.println("Comanda invalida!");
                }
            } catch (ValidationException exception) {
                System.out.println(exception.getMessage());
            } catch (InputMismatchException exception) {
                System.out.println("Date introduse gresit!");
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Eroare la introducerea datelor!");
            }
        }
    }

    /**
     * Afiseaza meniul de comenzi asupra studentilor
     */
    private void studentMenu() {
        while (true) {
            System.out.println("\n0.Iesire meniu student");
            System.out.println("1.Introducere student");
            System.out.println("2.Stergere student");
            System.out.println("3.Cautare student");
            System.out.println("4.Modificare student");
            System.out.println("5.Afisare lista studenti");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceti comanda: ");
            int comanda = scanner.nextInt();
            if (comanda == 0) {
                break;
            } else if (comanda == 1) {
                addStudent();
            } else if (comanda == 2) {
                deleteStudent();
            } else if (comanda == 3) {
                findStudent();
            } else if (comanda == 4) {
                updateStudent();
            } else if (comanda == 5) {
                listStudents();
            } else {
                System.out.println("Comanda invalida!");
            }
        }
    }

    /**
     * Adauga un student
     * @throws ValidationException daca datele studentul exista deja
     */
    private void addStudent() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id student: ");
        String idStudent = scanner.next();
        if (service.findStudent(idStudent) != null) {
            throw new ValidationException("Studentul exista!");
        }
        System.out.print("Introduceti numele: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Introduceti grupa: ");
        int group = scanner.nextInt();
        System.out.print("Introduceti email: ");
        String email = scanner.next();
        Student student = new Student(idStudent, name, group, email);
        Student student1 = service.addStudent(student);
        if (student1 == null) {
            System.out.println("Student adaugat cu succes!");
        } else {
            System.out.println("Studentul deja exista" + student1);
        }
    }

    /**
     * Sterge un student
     */
    private void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul student: pe care doriti sa il stergeti: ");
        String id = scanner.next();
        Student student = service.deleteStudent(id);
        if (student == null) {
            System.out.println("Studentul nu exista!");
        } else {
            System.out.println("Student sters cu succes!");
        }
    }

    /**
     * Cauta un student
     */
    private void findStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul studentului: ");
        String id = scanner.next();
        Student student = service.findStudent(id);
        if (student == null) {
            System.out.println("Studentul nu exista!");
        } else {
            System.out.println(student);
        }
    }

    /**
     * Modifica datele unui student
     */
    private void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul studentului pe care doriti sa il modificati: ");
        String id = scanner.next();
        System.out.println("Introduceti datele noi");
        System.out.print("Introduceti numele: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Introduceti grupa: ");
        int group = scanner.nextInt();
        System.out.print("Introduceti email: ");
        String email = scanner.next();
        Student student = new Student(id, name, group, email);
        Student student1 = service.updateStudent(student);
        if (student1 == null) {
            System.out.print("Studentul nu exista!");
        } else {
            System.out.println("Student modificat cu succes!" + student1);
        }
    }

    /**
     * Afiseaza lista studentilor
     */
    private void listStudents() {
        Iterable<Student> all = service.getAllStudents();
        all.forEach(System.out::println);
    }

    /**
     * Afiseaza comenzile pentru teme
     */
    private void assignmntsMenu() {
        while (true) {
            System.out.println("\n0.Iesire meniu teme");
            System.out.println("1.Introducere tema");
            System.out.println("2.Prelungire deadline");
            System.out.println("3.Stergere tema");
            System.out.println("4.Cautare tema");
            System.out.println("5.Modificare tema");
            System.out.println("6.Afisare lista teme");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceti comanda: ");
            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) {
                addAssignment();
            }
            else if(command==2) {
                extendDeadline();
            }
            else if (command == 3) {
                deleteAssignment();
            } else if (command == 4) {
                findAssignment();
            } else if (command == 5) {
                updateAssignment();
            } else if (command == 6) {
                listAssignments();
            } else {
                System.out.println("Comanda invalida!");
            }
        }
    }

    /**
     * Adauga o tema
     * @throws ValidationException daca tema exista deja
     */
    private void addAssignment() throws ValidationException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti nr assignment: ");
        String nrAssignment = scanner.next();
        if (service.findAssignment(nrAssignment) != null) {
            throw new ValidationException("Assignment exista deja!");
        }
        System.out.print("Introduceti descrierea: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.print("Introduceti deadline-ul(nr saptamanii): ");
        int deadline = scanner.nextInt();
        System.out.print("Introduceti saptamana primirii: ");
        int received = scanner.nextInt();
        Assignment assignment = new Assignment(nrAssignment, description, deadline, received);
        assignment = service.addAssignment(assignment);
        if (assignment == null) {
            System.out.println("Assignment adaugata cu succes!");
        } else {
            System.out.println("Assignment deja exista" + assignment);
        }
    }


    /**
     * Prelungeste deadline-ul unei teme
     */
    private void extendDeadline(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id tema: ");
        String nrAssignment = scanner.next();
        System.out.print("Introduceti deadline nou: ");
        int deadline = scanner.nextInt();
        service.extendDeadline(nrAssignment, deadline);
        System.out.println("Dealine prelungit!");
    }

    /**
     * Sterge o tema
     */
    private void deleteAssignment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul temei: pe care doriti sa o stergeti: ");
        String id = scanner.next();
        Assignment assignment = service.deleteAssignment(id);
        if (assignment == null) {
            System.out.println("Assignment nu exista!");
        } else {
            System.out.println("Assignment stearsa cu succes!");
        }
    }

    /**
     * Cauta o tema
     */
    private void findAssignment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul temei: ");
        String id = scanner.next();
        Assignment assignment = service.findAssignment(id);
        if (assignment == null) {
            System.out.println("Assignment nu exista!");
        } else {
            System.out.println(assignment);
        }
    }

    /**
     * Modifica o tema
     */
    private void updateAssignment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul temei pe care doriti sa o modificati: ");
        String id = scanner.next();
        System.out.println("Introduceti datele noi");
        System.out.print("Introduceti descrierea: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.print("Introduceti deadline: ");
        int deadline = scanner.nextInt();
        System.out.print("Introduceti saptamana primire: ");
        int received = scanner.nextInt();
        Assignment assignment = new Assignment(id, description, deadline, received);
        Assignment assignment1 = service.updateAssignment(assignment);
        if (assignment1 == null) {
            System.out.println("Assignment nu exista!");
        } else {
            System.out.println("Assignment modificata cu succes!" + assignment1);
        }
    }

    /**
     * Afiseaza toate temele
     */
    private void listAssignments(){
        Iterable<Assignment> all = service.getAllAssignments();
        //for(Assignment tema: all){
        //    System.out.println(tema);
        //}
        all.forEach(System.out::println);
    }

    /**
     * Afiseaza comenzile disponibile pentru note
     */
    private void gradesMenu() {
        while (true) {
            System.out.println("\n0.Iesire meniu note");
            System.out.println("1.Introducere nota");
            System.out.println("2.Stergere nota");
            System.out.println("3.Cautare nota");
            System.out.println("4.Afisare lista note");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceti comanda: ");
            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) {
                addGrade();
            } else if (command == 2) {
                deleteGrade();
            } else if (command == 3) {
                findGrade();
            } else if (command == 4) {
                listGrades();
            } else {
                System.out.println("Comanda invalida!");
            }
        }
    }

    /**
     * Adauga o nota
     * @throws ValidationException daca nota exista deja
     */
    private void addGrade() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id student: ");
        String idStudent = scanner.next();
        System.out.print("Introduceti numarul temei: ");
        String nrAssignment = scanner.next();
        String idGrade = idStudent + "#" + nrAssignment;
        if (service.findGrade(idGrade) != null) {
            throw new ValidationException("Grade exista deja!");
        }
        System.out.print("Introduceti nota: ");
        Double grade = scanner.nextDouble();
        System.out.print("Introduceti data predarii temei(format: an-luna-data): ");
        String date = scanner.next();
        String[] dates = date.split("-");
        LocalDate submissionDate = LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
        System.out.print("Introduceti feedback: ");
        scanner.nextLine();
        String feedback = scanner.nextLine();        //System.out.println(feedback);
        Grade gradeCatalog = new Grade(idGrade, idStudent, nrAssignment, grade, submissionDate);
        double finalGrade = service.addGrade(gradeCatalog, feedback);
        System.out.println("Grade maxima pe care o poate primi studentul este: " + finalGrade);
    }

    /**
     * Sterge o nota
     */
    private void deleteGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul studentului: ");
        String idStudent = scanner.next();
        System.out.print("Introduceti nr-ul temei: ");
        String nrAssignment = scanner.next();
        String idGrade = idStudent + "#" + nrAssignment;
        Grade grade = service.deleteGrade(idGrade);
        if (grade == null) {
            System.out.println("Grade nu exista!");
        } else {
            System.out.println("Grade stearsa cu succes!");
        }
    }

    /**
     * Cauta o nota
     */
    private void findGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul studentului: ");
        String idStudent = scanner.next();
        System.out.print("Introduceti nr-ul temei: ");
        String nrAssignment = scanner.next();
        String idGrade = idStudent + "#" + nrAssignment;
        Grade grade = service.findGrade(idGrade);
        if (grade == null) {
            System.out.println("Grade nu exista!");
        } else {
            System.out.println(grade);
        }
    }

    /**
     * Afiseaza toate notele
     */
    private void listGrades() {
        Iterable<Grade> all = service.getAllGrades();
        all.forEach(System.out::println);
    }
}
