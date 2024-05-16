package org.example.empresafct;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
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

public class HelloController {
    public Tab tap_alumnos;
    public Button bt_crearDATalumnos;
    public Label lab_infoAlumnos;
    public Tab tab_tutores;
    public Button bt_crearXMLtutores;
    public Label lab_infoTutores;

    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
        try {
            File file = new File("C:/Users/damda/OneDrive/Documentos/EmpresaFCT/alumnos.dat");
            if (file.createNewFile()) {
                lab_infoAlumnos.setText("File created: " + file.getName());
            } else {
                lab_infoAlumnos.setText("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readXMLFileAndSaveToDatabase() {
        try {
            File inputFile = new File("C:/Users/damda/OneDrive/Documentos/EmpresaFCT/tutoresdoc.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("tutordoc");

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

//                    System.out.println("\nCurrent Element: " + nNode.getNodeName());
//                    System.out.println();
//
//                    Element eElement = (Element) nNode;
//                    String nombre = eElement.getElementsByTagName("nomap").item(0).getTextContent();
//                    String codtut = eElement.getElementsByTagName("codtut").item(0).getTextContent();
//                    String correo_electronico = eElement.getElementsByTagName("correo").item(0).getTextContent();
//                    String telefono = eElement.getElementsByTagName("telefono").item(0).getTextContent();
//
//                    System.out.println("nomap: " + nombre);
//                    System.out.println("codtut: " + codtut);
//                    System.out.println("Correo Electronico: " + correo_electronico);
//                    System.out.println("Telefono: " + telefono);

                    Element eElement = (Element) nNode;
                    String nombre = eElement.getElementsByTagName("nomap").item(0).getTextContent();
                    String codtut = eElement.getElementsByTagName("codtut").item(0).getTextContent();
                    String correo_electronico = eElement.getElementsByTagName("correo").item(0).getTextContent();
                    String telefono = eElement.getElementsByTagName("telefono").item(0).getTextContent();

                    String insertQuery = "INSERT INTO tutor (nombre, aoellidos, correo_electronico, telefono) VALUES ('" + nombre + "', '" + codtut + "', '" + correo_electronico + "', '" + telefono + "')";
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