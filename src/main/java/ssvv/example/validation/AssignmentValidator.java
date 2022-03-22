package ssvv.example.validation;

import ssvv.example.domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Assignment entity) throws ValidationException {
        if(entity.getID() == null || entity.getID().equals("")) {
            throw new ValidationException("Numar tema invalid!");
        }
        if(entity.getDescription() == null || entity.getDescription().equals("")){
            throw new ValidationException("Descriere invalida!");
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {
            throw new ValidationException("Deadlineul trebuie sa fie intre 1-14.");
        }
        if(entity.getReceived() < 1 || entity.getReceived() > 14) {
            throw new ValidationException("Saptamana primirii trebuie sa fie intre 1-14.");
        }
    }
}
