package org.example.empresafct;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM alumnos");

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Alumnos");
            document.appendChild(root);

            while (resultSet.next()) {
                Element alumno = document.createElement("Alumno");

                Element id = document.createElement("ID");
                id.appendChild(document.createTextNode(resultSet.getString("id")));
                alumno.appendChild(id);

                Element name = document.createElement("Name");
                name.appendChild(document.createTextNode(resultSet.getString("name")));
                alumno.appendChild(name);

                // add more elements for each column in the alumnos table

                root.appendChild(alumno);
            }

            // create the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("alumnos.xml"));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void readXMLFileAndSaveToDatabase() {
        try {
            File inputFile = new File("tutoresdoc.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Tutor");

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellidos = eElement.getElementsByTagName("apellidos").item(0).getTextContent();
                    String correo_electronico = eElement.getElementsByTagName("correo_electronico").item(0).getTextContent();
                    String telefono = eElement.getElementsByTagName("telefono").item(0).getTextContent();

                    // Aquí es donde validamos el valor de idEmpresa
                    String idEmpresaStr = eElement.getElementsByTagName("id_empresa").item(0).getTextContent();
                    int idEmpresa;

                    try {
                        idEmpresa = Integer.parseInt(idEmpresaStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: id_empresa debe ser un entero.");
                        return;
                    }



                    String insertQuery = "INSERT INTO tutor (nombre, apellidos, correo_electronico, telefono) VALUES ('" + nombre + "', '" + apellidos + "', '" + correo_electronico + "', '" + telefono + "')";
                    statement.executeUpdate(insertQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void click_bt_crearXMLtutores(ActionEvent actionEvent) {
        readXMLFileAndSaveToDatabase();
    }
}
