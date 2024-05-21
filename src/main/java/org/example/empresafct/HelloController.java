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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public Tab tap_alumnos;
    public Button bt_crearDATalumnos;
    public Label lab_infoAlumnos;
    public Tab tab_tutores;
    public Button bt_crearXMLtutores;
    public Label lab_infoTutores;
    @FXML
    private Button bt_confirmarAsignacion;

    @FXML
    private Label label_informacionCompleta;

    @FXML
    private SplitMenuButton smb_eleccionAlumno;

    @FXML
    private SplitMenuButton smb_eleccionEmpresa;

    @FXML
    private SplitMenuButton smb_eleccionTutor;

    // Obtén los valores seleccionados de los menús desplegables
    String alumnoSeleccionado = smb_eleccionAlumno == null ? "" : smb_eleccionAlumno.getText();
    String empresaSeleccionada = smb_eleccionEmpresa == null ? "" : smb_eleccionEmpresa.getText();
    String tutorSeleccionado = smb_eleccionTutor == null ? "" : smb_eleccionTutor.getText();


    public void initialize() {
        // Llama a los métodos para obtener los datos de las listas desplegables
        // Obtén los alumnos de la base de datos
        List<String> alumnos = obtenerAlumnos();
        List<String> empresas = obtenerEmpresas();
        List<String> tutores = obtenerTutores();

       // System.out.printf("Alumnos: %s\n", alumnos);
        // Limpia los elementos existentes
        smb_eleccionAlumno.getItems().clear();
        smb_eleccionEmpresa.getItems().clear();
        smb_eleccionTutor.getItems().clear();

        // Agrega los alumnos a la lista desplegable
        for (String alumno : alumnos) {
            MenuItem item = new MenuItem(alumno);
            item.setOnAction(event -> {
                    smb_eleccionAlumno.setText(alumno); alumnoSeleccionado = alumno; });
                smb_eleccionAlumno.getItems().add(item);
        }
        for (String empresa : empresas) {
            MenuItem item = new MenuItem(empresa);
            item.setOnAction(event -> {
                    smb_eleccionEmpresa.setText(empresa);
                    empresaSeleccionada = empresa; });
            smb_eleccionEmpresa.getItems().add(item);
        }
        for (String tutor : tutores) {
            MenuItem item = new MenuItem(tutor);
            item.setOnAction(event -> {
                    smb_eleccionTutor.setText(tutor);
                    tutorSeleccionado = tutor; });
            smb_eleccionTutor.getItems().add(item);
        }

    }

    private List<String> obtenerAlumnos() {
        return obtenerDatos("alumno","nombre");
    }

    private List<String> obtenerEmpresas() {
        return obtenerDatos("empresa", "nombre_empresa");
    }

    private List<String> obtenerTutores() {
        return obtenerDatos("tutor", "nombre");
    }

    private List<String> obtenerDatos(String tabla, String columna) {
        List<String> datos = new ArrayList<>();

        String sql = "SELECT " + columna + " FROM " + tabla;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                datos.add(rs.getString(columna));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return datos;
    }

    @FXML
    public void click_bt_confirmarAsignacion() {


        // Comprueba si el alumno ya está asignado
        if (alumnoEstaAsignado(alumnoSeleccionado)) {
            label_informacionCompleta.setText("El alumno ya está asignado.");
            return;
        }

        //Llamar al método guardarAsignación
        guardarAsignacion(alumnoSeleccionado, empresaSeleccionada, tutorSeleccionado);

        // Comprobar si el alumno ya está asignado
        if (alumnoEstaAsignado(alumnoSeleccionado)){
            label_informacionCompleta.setText("El alumno ya está asignado");
        }

        //Llama al método de la informacion
        muestra_informacionCompleta();


    }

    @FXML
    private void guardarAsignacion(String alumno, String empresa, String tutor) {
        String sql = "INSERT INTO Asignados (id_alumno, id_empresa, id_tutor, fecha_hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno);
            pstmt.setString(2, empresa);
            pstmt.setString(3, tutor);
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void click_smb_eleccionAlumno() {
    }
    @FXML
    public void click_smb_eleccionEmpresa() {
//        // Obtén las empresas de la base de datos
//        List<String> empresas = obtenerEmpresas();
//
//        // Limpia los elementos existentes
//        smb_eleccionEmpresa.getItems().clear();
//
//        // Agrega las empresas a la lista desplegable
//        smb_eleccionEmpresa.getItems().addAll((MenuItem) empresas);
    }
    @FXML
    public void click_smb_eleccionTutor() {
//        // Obtén los tutores de la base de datos
//        List<String> tutores = obtenerTutores();
//
//        // Limpia los elementos existentes
//        smb_eleccionTutor.getItems().clear();
//
//        // Agrega los tutores a la lista desplegable
//        smb_eleccionTutor.getItems().addAll((MenuItem) tutores);
    }

    private boolean alumnoEstaAsignado(String alumnoSeleccionado) {
        // Implementa este método para comprobar si el alumno ya está asignado
        String sql = "SELECT * FROM Asignados WHERE id_alumno = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumnoSeleccionado);
            ResultSet rs = pstmt.executeQuery();

            // Si hay resultados, el alumno ya está asignado
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private String obtenerNombreTutorLaboral(String tutorSeleccionado) {
        // Implementa este método para obtener el nombre del tutor laboral de la base de datos
        String sql = "SELECT nombre FROM tutor WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tutorSeleccionado);
            ResultSet rs = pstmt.executeQuery();

            // Si hay resultados, devuelve el nombre del tutor
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @FXML
    public void muestra_informacionCompleta() {
        // Obtén el nombre del tutor laboral de la base de datos
        String nombreTutorLaboral = obtenerNombreTutorLaboral(tutorSeleccionado);

        // Forma el mensaje a mostrar
        String mensaje = " El alumno " + alumnoSeleccionado + " queda asignado a la empresa" + empresaSeleccionada + "\n" +
                "supervisado por el tutor docente" +  tutorSeleccionado + "y por el tutor laboral" +  nombreTutorLaboral + ".";

        // Muestra el mensaje en la etiqueta
        label_informacionCompleta.setText(mensaje);
        label_informacionCompleta.setFont(Font.font("Arial"));
        label_informacionCompleta.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }






    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
        try {
            List<Alumno> alumnos = getAlumnosFromDatabase();
            writeAlumnosToFile(alumnos, "C:/Users/damda/OneDrive/Documentos/EmpresaFCT/alumnos.dat");
            lab_infoAlumnos.setText("File created: alumnos.dat");
        } catch (Exception e) {
            e.printStackTrace();
            lab_infoAlumnos.setText("Error creating file: " + e.getMessage());
        }
        bt_crearDATalumnos.setDisable(true);
    }

    private List<Alumno> getAlumnosFromDatabase() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");
        while (resultSet.next()) {
            Alumno alumno = new Alumno(
                    resultSet.getString("nombre"),
                    resultSet.getString("apellidos"),
                    resultSet.getString("dni"),
                    resultSet.getDate("fecha_nacimiento").toLocalDate()
            );
            alumnos.add(alumno);
        }
        return alumnos;
    }

    private void writeAlumnosToFile(List<Alumno> alumnos, String filePath) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(alumnos);
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
        lab_infoTutores.setText("Información del fichero ahora registrada en la tabla tutores.");
        bt_crearXMLtutores.setDisable(true);
    }



}