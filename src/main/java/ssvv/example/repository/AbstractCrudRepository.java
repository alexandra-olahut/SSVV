package ssvv.example.repository;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCrudRepository <ID, E extends HasID<ID>> implements CrudRepository<ID, E> {
    private Map<ID, E> elements;


    /**
     * Class constructor
     */
    AbstractCrudRepository(){
        this.elements = new HashMap<>();
    }

    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return obiectul cu id-ul respectiv sau null daca obiectul nu exista
     */
    @Override
    public E findOne(ID id) {
        return this.elements.get(id);
    }

    /**
     *
     * @return toate obiectele
     */
    @Override
    public Iterable<E> findAll() {
        return this.elements.values();
    }

    /**
     * Salveaza un obiect in memorie
     * @param entity - obiectul pe care il salveaza
     * entity must be not null
     * @return null daca obiectul a fost salvat sau obiectul din memorie daca acesta exista deja
     */
    @Override
    public E save(E entity) {
        /*
        for(ID id: elemente.keySet()){
            if(id == entity.getID()){
                return elemente.get(id);
            }
        }
        */
        E el = this.findOne(entity.getID());
        if (el==null){
            this.elements.put(entity.getID(), entity);
        }
        // must return el (that already exist), not entity that we try to add
        return el;
    }

    /**
     * sterge un obiect din memorie
     * @param id - id-ul obiectului
     * id must be not null
     * @return obiectul sters, null daca nu exista
     */
    @Override
    public E delete(ID id) {
        return this.elements.remove(id);
    }

    /**
     * modifica un obiect
     * @param entity - noul obiect
     * entity must not be null
     * @return obiectul daca obiectul a fost modificat sau null daca acesta nu exista in memorie
     */
    @Override
    public E update(E entity) {
        if(this.elements.get(entity.getID()) == null){
            return null;
        }
        this.elements.replace(entity.getID(), entity);
        return entity;
    }
}
