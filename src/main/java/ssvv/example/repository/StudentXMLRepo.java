package ssvv.example.repository;

import ssvv.example.domain.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StudentXMLRepo extends AbstractXMLRepository<String, Student> {
    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public StudentXMLRepo(String filename) {
        super(filename);
    }

    /**
     * Extrage informatia despre student dintr-un element XML
     * @param element - XML-ul din care ia datele studentului
     * @return studentul
     */

    @Override
    public Student extractEntity(Element element) {
        String studentId = element.getAttribute("idStudent");
        NodeList nodes = element.getChildNodes();
        String name =element.getElementsByTagName("name")
                .item(0)
                .getTextContent();
        String group =element.getElementsByTagName("group")
                .item(0)
                .getTextContent();
        String email =element.getElementsByTagName("email")
                .item(0)
                .getTextContent();

        return new Student(studentId, name, Integer.parseInt(group), email);
    }

    @Override
    public Element createElementFromEntity(Document document, Student entity) {
        Element e = document.createElement("student");
        e.setAttribute("idStudent", entity.getID());

        Element name = document.createElement("name");
        name.setTextContent(entity.getName());
        e.appendChild(name);

        Element group = document.createElement("group");
        Integer g = entity.getGroup();
        group.setTextContent(g.toString());
        e.appendChild(group);

        Element email = document.createElement("email");
        email.setTextContent(entity.getEmail());
        e.appendChild(email);

        return e;
    }

}
