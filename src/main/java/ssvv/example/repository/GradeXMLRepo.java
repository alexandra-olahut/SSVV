package ssvv.example.repository;

import ssvv.example.domain.Grade;

import java.time.LocalDate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GradeXMLRepo extends AbstractXMLRepository<String, Grade> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public GradeXMLRepo(String filename) {
        super( filename);
    }

    @Override
    public Element createElementFromEntity(Document document, Grade entity) {
        Element e = document.createElement("grade");
        e.setAttribute("id", entity.getID());

        Element idStudent = document.createElement("idStudent");
        idStudent.setTextContent(entity.getIdStudent());
        e.appendChild(idStudent);

        Element idAssignment = document.createElement("idAssignment");
        idAssignment.setTextContent(entity.getIdAssignment());
        e.appendChild(idAssignment);

        Element givenGrade = document.createElement("givenGrade");
        Double i = entity.getGrade();
        givenGrade.setTextContent(i.toString());
        e.appendChild(givenGrade);

        Element currentDate = document.createElement("currentDate");
        LocalDate d = entity.getDate();
        currentDate.setTextContent(d.toString());
        e.appendChild(currentDate);

        return e;
    }


    /**
     * Extrage informatia despre nota dintr-un XML
     * @param element - elem XML din care ia datele notei
     * @return nota
     */
    @Override
    public Grade extractEntity(Element element) {
        String id = element.getAttribute("id");
        NodeList nodes = element.getChildNodes();

        String studentId =element.getElementsByTagName("idStudent")
                .item(0)
                .getTextContent();

        String idAssignment =element.getElementsByTagName("idAssignment")
                .item(0)
                .getTextContent();

        String givenGrade =element.getElementsByTagName("givenGrade")
                .item(0)
                .getTextContent();


        String currentDate =element.getElementsByTagName("currentDate")
                .item(0)
                .getTextContent();

        String[] d = currentDate.split("-");
        LocalDate dat = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));

        return new Grade(id,studentId,idAssignment,Double.parseDouble(givenGrade),dat);
        }
}
