package org.example.empresafct;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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



    // Métodos para obtener los datos de las listas desplegables
    private List<String> obtenerAlumnos() {
        return obtenerDatos("alumno","nombre");
    }

    private List<String> obtenerEmpresas() {
        return obtenerDatos("empresa", "nombre_empresa");
    }

    private List<String> obtenerTutores() {
        return obtenerDatos("tutor", "nombre");
    }

    @FXML
    public void initialize() {
        // Llama a los métodos para obtener los datos de las listas desplegables
        // Obtén los alumnos de la base de datos
        List<String> alumnos = obtenerAlumnos();
        System.out.printf("Alumnos: %s\n", alumnos);

        // Limpia los elementos existentes
        smb_eleccionAlumno.getItems().clear();

        // Agrega los alumnos a la lista desplegable
        for (String alumno : alumnos) {
            MenuItem item = new MenuItem(alumno);
            item.setOnAction(event -> smb_eleccionAlumno.setText(alumno));
            smb_eleccionAlumno.getItems().add(item);
        }


        // Obtén las empresas de la base de datos
        List<String> empresas = obtenerEmpresas();

        // Limpia los elementos existentes
        smb_eleccionEmpresa.getItems().clear();

        // Agrega las empresas a la lista desplegable
        for (String empresa : empresas) {
            MenuItem item = new MenuItem(empresa);
            item.setOnAction(event -> smb_eleccionEmpresa.setText(empresa));
            smb_eleccionEmpresa.getItems().add(item);
        }


        // Obtén los tutores de la base de datos
        List<String> tutores = obtenerTutores();

        // Limpia los elementos existentes
        smb_eleccionTutor.getItems().clear();

        // Agrega los tutores a la lista desplegable
        for (String tutor : tutores) {
            MenuItem item = new MenuItem(tutor);
            item.setOnAction(event -> smb_eleccionTutor.setText(tutor));
            smb_eleccionTutor.getItems().add(item);
        }



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

    // Obtén los valores seleccionados de los menús desplegables
    String alumnoSeleccionado = smb_eleccionAlumno == null ? "" : smb_eleccionAlumno.getText();
    String empresaSeleccionada = smb_eleccionEmpresa == null ? "" : smb_eleccionEmpresa.getText();
    String tutorSeleccionado = smb_eleccionTutor == null ? "" : smb_eleccionTutor.getText();

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
        String sql = "SELECT * FROM Asignados WHERE alumno = ?";

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
}