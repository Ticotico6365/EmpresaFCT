package org.example.empresafct;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlunmosController {
    public Tab tap_alumnos;
    public Button bt_crearDATalumnos;
    public Label lab_infoAlumnos;
    public Tab tab_tutores;
    public Button bt_crearXMLtutores;
    public Label lab_infoTutores;

    public void click_bt_crearXMLtutores(ActionEvent actionEvent) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tutor");

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Tutores");
            document.appendChild(root);

            while (resultSet.next()) {
                Element tutor = document.createElement("Tutor");

                Element id = document.createElement("ID");
                id.appendChild(document.createTextNode(resultSet.getString("id")));
                tutor.appendChild(id);

                Element name = document.createElement("Name");
                name.appendChild(document.createTextNode(resultSet.getString("name")));
                tutor.appendChild(name);

                // add more elements for each column in the tutores table

                root.appendChild(tutor);
            }

            // create the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("tutores.xml"));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
    }
}
