package ssvv.example.repository;

import ssvv.example.domain.Grade;

import java.time.LocalDate;

public class GradeFileRepository extends AbstractFileRepository<String, Grade> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public GradeFileRepository(String filename) {
        super( filename);
    }

    /**
     * Extrage informatia despre nota dintr-un string
     * @param line - stringul din care ia datele notei
     * @return nota
     */
    @Override
    public Grade extractEntity(String line) {
        String[] words = line.split(",");
        String[] data = words[3].split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        return new Grade(words[0]+"#"+words[1], words[0], words[1], Double.parseDouble(words[2]), date);
    }
}
