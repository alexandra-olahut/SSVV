package ssvv.example.domain;

import ssvv.example.repository.HasID;

public class Student implements HasID<String> {
    private String idStudent;
    private String name;
    private int group;
    private String email;

    /**
     * Class Constructor
     * @param idStudent - id-ul studentului
     * @param name - numele studentului
     * @param group - grupa studentului
     * @param email - emailul unui student
     */
    public Student(String idStudent, String name, int group, String email) {
        this.idStudent = idStudent;
        this.name = name;
        this.group = group;
        this.email = email;
    }

    /**
     * @return id-ul unui student
     */
    public String getID() {
        return idStudent;
    }

    /**
     * modifica id-ul unui student
     * @param ID - noul id al studentului
     */
    public void setID(String ID) {
        this.idStudent = ID;
    }

    /**
     * @return numele studentului
     */
    public String getName() {
        return name;
    }

    /**
     * modifica numele studentului
     * @param name - noul nume
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return grupa studentului
     */
    public int getGroup() {
        return group;
    }

    /**
     * modifica grupa studentului
     * @param group - noua grupa
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * @return emai-ul studentului
     */
    public String getEmail() {
        return email;
    }

    /**
     * modifica emailul studentului
     * @param email - noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return un obiect de tip Student sub forma de string
     */
    @Override
    public String toString() {
        return idStudent + "," + name + "," + group + "," + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student))
            return false;
        Student other = (Student)obj;
        return idStudent.equals(other.getID())
                && name.equals(other.getName())
                && email.equals(other.getEmail())
                && group == other.getGroup();
    }

}
