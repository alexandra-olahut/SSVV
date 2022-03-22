package ssvv.example.repository;

import ssvv.example.domain.Student;

public class StudentFileRepository extends AbstractFileRepository<String, Student> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public StudentFileRepository(String filename) {
        super(filename);
    }

    /**
     * Extrage informatia despre student dintr-un string
     * @param line - stringul din care ia datele studentului
     * @return studentul
     */
    @Override
    public Student extractEntity(String line) {
        String[] words = line.split(",");
        return new Student(words[0], words[1], Integer.parseInt(words[2]), words[3]);
    }
}
