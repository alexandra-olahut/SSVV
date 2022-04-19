package ssvv.example.domain;


import ssvv.example.repository.HasID;


public class Assignment implements HasID<String> {
    private String nrAssignment;
    private String description;
    private int deadline;
    private int received;

    /**
     *
     * @param nrAssignment - numarul temei
     * @param description - descrierea unei teme
     * @param deadline - deadlineul unei teme
     * @param received - saptamana de primirea unei teme
     * Class Constructor
     */
    public Assignment(String nrAssignment, String description, int deadline, int received) {
        this.nrAssignment = nrAssignment;
        this.description = description;
        this.deadline = deadline;
        this.received = received;
    }

    /**
     * @return descrierea unei teme
     */
    public String getDescription() {
        return description;
    }

    /**
     * modifica descrierea unei teme
     * @param description - noua descriere
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return deadlineul unei teme
     */
    public int getDeadline() {
        return deadline;
    }

    /**
     * modifica deadlineul unei note
     */
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    /**
     * @return saptamana primirii unei teme
     */
    public int getReceived() {
        return received;
    }

    /**
     * modifica saptamana primirii unei teme
     * @param received - noua saptamana de primire
     */
    public void setReceived(int received) {
        this.received = received;
    }

    /**
     * @return numarul unei teme
     */
    @Override
    public String getID() {
        return this.nrAssignment;
    }

    /**
     * schimba numarul unei teme
     * @param nrAssignment - noul numar al temei
     */
    @Override
    public void setID(String nrAssignment) {
        this.nrAssignment = nrAssignment;
    }

    /**
     * @return un obiect de tip Assignment sub forma unui string
     */
    @Override
    public String toString() {
        return nrAssignment + "," + description + "," + deadline + "," + received;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Assignment))
            return false;
        Assignment other = (Assignment)obj;
        return nrAssignment.equals(other.getID())
                && description.equals(other.getDescription())
                && deadline == other.getDeadline()
                && received == other.getReceived();
    }

}
