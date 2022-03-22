package ssvv.example.repository;

import ssvv.example.domain.Assignment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AssignmentXMLRepo extends AbstractXMLRepository<String, Assignment> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public AssignmentXMLRepo(String filename){
        super(filename);
    }

    /**
     * Extrage informatia despre tema dintr-un elem XML
     * @param element - stringul din care ia datele temei
     * @return tema
     */
    @Override
    public Assignment extractEntity(Element element) {
        String nrAssignment = element.getAttribute("nrAssignment");
        NodeList nodes = element.getChildNodes();
        String description =element.getElementsByTagName("description")
                .item(0)
                .getTextContent();
        String deadline =element.getElementsByTagName("deadline")
                .item(0)
                .getTextContent();
        String received =element.getElementsByTagName("received")
                .item(0)
                .getTextContent();

        return new Assignment(nrAssignment, description, Integer.parseInt(deadline), Integer.parseInt(received));
    }

    /**
     * Creeaza un element XML dintr o entitate Assignment
     * @param document
     * @param entity
     * @return
     */
    @Override
    public Element createElementFromEntity(Document document, Assignment entity) {
        Element e = document.createElement("nrAssignment");
        e.setAttribute("nrAssignment", entity.getID());

        Element description = document.createElement("description");
        description.setTextContent(entity.getDescription());
        e.appendChild(description);

        Element deadline = document.createElement("deadline");
        Integer g=entity.getDeadline();
        deadline.setTextContent(g.toString());
        e.appendChild(deadline);

        Element received = document.createElement("received");
        Integer p=entity.getReceived();
        received.setTextContent(p.toString());
        e.appendChild(received);

        return e;
    }
}
