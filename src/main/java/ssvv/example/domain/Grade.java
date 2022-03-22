package ssvv.example.domain;

import ssvv.example.repository.HasID;

import java.time.LocalDate;

public class Grade implements HasID<String> {
    private String id;
    private String idStudent;
    private String idAssignment;
    private double grade;
    private LocalDate date;

    /**
     * Class Constructor
     * @param id - id-ul notei
     * @param idStudent - id-ul studentului
     * @param idAssignment - id-ul temei
     * @param grade - valoarea notei
     * @param date - data in care a fost predata tema
     */
    public Grade(String id, String idStudent, String idAssignment, double grade, LocalDate date){
        this.id = id;
        this.idStudent = idStudent;
        this.idAssignment = idAssignment;
        this.grade = grade;
        this.date = date;
    }

    /**
     * @return id-ul studentului
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     * @return id-ul temei
     */
    public String getIdAssignment() {
        return idAssignment;
    }

    /**
     * @return valoarea notei
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Modifica valoarea unei note
     * @param grade - noua valoarea a notei
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * @return data in care a fost predata nota
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return idStudent + "," + idAssignment + "," + grade + "," + date;
    }

    /**
     * @return id-ul notei
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * modifica id-ul notei
     * @param id - noul id
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    /**
     * modifica id-ul studentului
     * @param idStudent - noul id
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    /**
     * modifica id-ul temei
     * @param idAssignment - noul id
     */
    public void setIdAssignment(String idAssignment) {
        this.idAssignment = idAssignment;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Grade))
            return false;
        Grade other = (Grade)obj;
        return id.equals(other.getID())
                && idStudent.equals(other.getIdStudent())
                && idAssignment.equals(other.getIdAssignment())
                && grade== other.getGrade();
    }
}
