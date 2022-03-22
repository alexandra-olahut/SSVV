package ssvv.example.repository;

import ssvv.example.domain.Assignment;

public class AssignmentFileRepository extends AbstractFileRepository<String, Assignment> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public AssignmentFileRepository(String filename){
        super(filename);
    }

    /**
     * Extrage informatia despre tema dintr-un string
     * @param line - stringul din care ia datele temei
     * @return tema
     */
    @Override
    public Assignment extractEntity(String line) {
        String[] words = line.split(",");
        return new Assignment(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
    }
}
