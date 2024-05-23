package org.example.empresafct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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

public class HelloController {
    public Tab tap_alumnos;
    public Button bt_crearDATalumnos;
    public Label lab_infoAlumnos;
    public Tab tab_tutores;
    public Button bt_crearXMLtutores;
    public Label lab_infoTutores;
    public TextField txt_dni_Rep_Legal;
    public TextField txt_dni_Tut_Laboral;
    public TextField txt_nombre_Rl;
    public TextField txt_nombre_Tl;
    public TextField txt_apellidos_Tl;
    public TextField txt_tlfTL;
    public TextField txt_apellidos_Rl;
    public Button bt_insertar;
    public Button bt_modificar;
    public Button bt_eliminar;
    public TextField txt_codigo_Empresa;
    public TextField txt_CIF;
    public TextField txt_nombre;
    public TextField txt_direccion;
    public TextField txt_CP;
    public TextField txt_localidad;
    public TextField txt_mail;
    public TableView<Empresa> tab_reflejo_Tabla_De_Datos;
    public TableColumn<Empresa, String> col_cif;
    public TableColumn<Empresa, String> col_nombre;
    public TableColumn<Empresa, String> col_direccion;
    public TableColumn<Empresa, String> col_cp;
    public TableColumn<Empresa, String> col_localidad;
    public TableColumn<Empresa, String> col_jornada;
    public TableColumn<Empresa, String> col_modalidad;
    public TableColumn<Empresa, String> col_correo;
    public ChoiceBox smb_jornada;
    public ChoiceBox smb_modalidad;
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

        //Métodos de la parte de la pestaña de Asignación
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

        label_informacionCompleta.setFont(Font.font("Arial", FontWeight.BOLD, 12));


        // Agrega los alumnos, empresa y tutor a la lista desplegable

        for (String alumno : alumnos) {
            MenuItem item = new MenuItem(alumno);
            item.setOnAction(event -> {
                smb_eleccionAlumno.setText(alumno);
                alumnoSeleccionado = alumno;
            });
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

        //Métodos para la pestaña de Empresas
        // Agrega los elementos a los menús desplegables
        smb_jornada.getItems().addAll("Mañana", "Tarde", "Completa");
        smb_modalidad.getItems().addAll("Presencial", "Online", "Mixta");

        // Inicializa la tabla de reflejo de datos
        col_cif.setCellValueFactory(new PropertyValueFactory<>("cif"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_cp.setCellValueFactory(new PropertyValueFactory<>("codigo_postal"));
        col_localidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        col_jornada.setCellValueFactory(new PropertyValueFactory<>("jornada"));
        col_modalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        col_correo.setCellValueFactory(new PropertyValueFactory<>("mail"));

        ObservableList<Empresa> data = FXCollections.observableArrayList(getEmpresas());
        tab_reflejo_Tabla_De_Datos.setItems(data);

        // Agrega un listener al elemento seleccionado de la tabla
        tab_reflejo_Tabla_De_Datos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Rellena los campos de texto con los valores del elemento seleccionado
                txt_codigo_Empresa.setText(newSelection.getCodigo_emrpresa());
                txt_CIF.setText(newSelection.getCif());
                txt_nombre.setText(newSelection.getNombre_empresa());
                txt_direccion.setText(newSelection.getDireccion());
                txt_CP.setText(newSelection.getCodigo_postal());
                txt_localidad.setText(newSelection.getLocalidad());
                smb_jornada.setValue(newSelection.getJornada());
                smb_modalidad.setValue(newSelection.getModalidad());
                txt_mail.setText(newSelection.getMail());
                txt_dni_Rep_Legal.setText(newSelection.getDni_responsable_legal());
                txt_dni_Tut_Laboral.setText(newSelection.getDni_tutor_legal());
                txt_nombre_Rl.setText(newSelection.getNombre_responsable_legal());
                txt_nombre_Tl.setText(newSelection.getNombre_tutor_laboral());
                txt_apellidos_Rl.setText(newSelection.getApellidos_responsable_legal());
                txt_apellidos_Tl.setText(newSelection.getApellidos_tutor_laboral());
                txt_tlfTL.setText(newSelection.getTelefono_tutor_laboral());
            }
        });

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
    public void click_bt_confirmarAsignacion() throws Exception {


        // Comprueba si el alumno ya está asignado
        if (alumnoEstaAsignado(alumnoSeleccionado)) {
            label_informacionCompleta.setText("El alumno ya está asignado.");
            return;
        }
        Alumno alumno = new Alumno();

        for (Alumno a : getAlumnosFromDatabase()) {
            if (a.getNombre().equals(alumnoSeleccionado)) {
                alumno = a;
                break;
            }
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
        String sql = "INSERT INTO Asignados (id_alumno, id_empresa, id_tutor, fecha, hora) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, buscarId("alumno", "nombre", alumno));
            pstmt.setInt(2, buscarId("empresa", "nombre_empresa", empresa));
            pstmt.setInt(3, buscarId("tutor", "nombre", tutor));
            pstmt.setDate(4, Date.valueOf(LocalDate.now()));
            pstmt.setTime(5, Time.valueOf(LocalDateTime.now().toLocalTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer buscarId(String tabla, String columna, String valor) {
        String sql = "SELECT id FROM " + tabla + " WHERE " + columna + " = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
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
        String sql = "SELECT nombre_tutor_laboral, apellidos_tutor_laboral FROM empresa WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, buscarId("tutor", "nombre", tutorSeleccionado));
            ResultSet rs = pstmt.executeQuery();

            // Si hay resultados, devuelve el nombre del tutor
            if (rs.next()) {
                String nombre = rs.getString("nombre_tutor_laboral");
                String apellidos = rs.getString("apellidos_tutor_laboral");
                return nombre + " " + apellidos;
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
                "supervisado por el tutor docente" + "\n" +tutorSeleccionado + "y por el tutor laboral" + nombreTutorLaboral + ".";

        // Muestra el mensaje en la etiqueta

        label_informacionCompleta.setText(mensaje);
        label_informacionCompleta.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label_informacionCompleta.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        label_informacionCompleta.setTextAlignment(TextAlignment.CENTER);
        label_informacionCompleta.setAlignment(Pos.CENTER);
    }


    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
        try {
            List<Alumno> alumnos = getAlumnosFromDatabase();
            writeAlumnosToFile(alumnos, "alumnos.dat");
            lab_infoAlumnos.setText("File created: alumnos.dat");
        } catch (Exception e) {
            e.printStackTrace();
            lab_infoAlumnos.setText("Error creating file: " + e.getMessage());
        }
        bt_crearDATalumnos.setDisable(true);
    }

    private static List<Alumno> getAlumnosFromDatabase() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");
        while (resultSet.next()) {
            Alumno alumno = new Alumno(
                    resultSet.getInt("id"),
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
            File inputFile = new File("tutoresdoc.xml");
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

    public static List<Empresa> getEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empresa");
            while (resultSet.next()) {
                Empresa empresa = new Empresa(
                        resultSet.getInt("id"),
                        resultSet.getString("codigo_empresa"),
                        resultSet.getString("cif"),
                        resultSet.getString("nombre_empresa"),
                        resultSet.getString("direccion"),
                        resultSet.getString("codigo_postal"),
                        resultSet.getString("localidad"),
                        resultSet.getString("jornada"),
                        resultSet.getString("modalidad"),
                        resultSet.getString("mail"),
                        resultSet.getString("dni_responsable_legal"),
                        resultSet.getString("dni_tutor_legal"),
                        resultSet.getString("nombre_responsable_legal"),
                        resultSet.getString("nombre_tutor_laboral"),
                        resultSet.getString("apellidos_responsable_legal"),
                        resultSet.getString("apellidos_tutor_laboral"),
                        resultSet.getString("telefono_tutor_laboral")
                );
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    public void insertEmpresa(Empresa empresa) {
        String sql = "INSERT INTO empresa (codigo_empresa, cif, nombre_empresa, direccion, codigo_postal, localidad, jornada, modalidad, mail, dni_responsable_legal, dni_tutor_legal, nombre_responsable_legal, nombre_tutor_laboral, apellidos_responsable_legal, apellidos_tutor_laboral, telefono_tutor_laboral) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empresa.getCodigo_emrpresa());
            pstmt.setString(2, empresa.getCif());
            pstmt.setString(3, empresa.getNombre_empresa());
            pstmt.setString(4, empresa.getDireccion());
            pstmt.setString(5, empresa.getCodigo_postal());
            pstmt.setString(6, empresa.getLocalidad());
            pstmt.setString(7, empresa.getJornada());
            pstmt.setString(8, empresa.getModalidad());
            pstmt.setString(9, empresa.getMail());
            pstmt.setString(10, empresa.getDni_responsable_legal());
            pstmt.setString(11, empresa.getDni_tutor_legal());
            pstmt.setString(12, empresa.getNombre_responsable_legal());
            pstmt.setString(13, empresa.getNombre_tutor_laboral());
            pstmt.setString(14, empresa.getApellidos_responsable_legal());
            pstmt.setString(15, empresa.getApellidos_tutor_laboral());
            pstmt.setString(16, empresa.getTelefono_tutor_laboral());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateEmpresa(Empresa empresa) {
        String query = "UPDATE empresa SET codigo_empresa = '" + empresa.getCodigo_emrpresa() + "', cif = '" + empresa.getCif() + "', nombre_empresa = '" + empresa.getNombre_empresa() + "', direccion = '" + empresa.getDireccion() + "', codigo_postal = '" + empresa.getCodigo_postal() + "', localidad = '" + empresa.getLocalidad() + "', jornada = '" + empresa.getJornada() + "', modalidad = '" + empresa.getModalidad() + "', mail = '" + empresa.getMail() + "', dni_responsable_legal = '" + empresa.getDni_responsable_legal() + "', dni_tutor_legal = '" + empresa.getDni_tutor_legal() + "', nombre_responsable_legal = '" + empresa.getNombre_responsable_legal() + "', nombre_tutor_laboral = '" + empresa.getNombre_tutor_laboral() + "', apellidos_responsable_legal = '" + empresa.getApellidos_responsable_legal() + "', apellidos_tutor_laboral = '" + empresa.getApellidos_tutor_laboral() + "', telefono_tutor_laboral = '" + empresa.getTelefono_tutor_laboral() + "' WHERE id = " + empresa.getId();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmpresa(Empresa empresa) {
        String query = "DELETE FROM empresa WHERE id = " + empresa.getId();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer buscarIdEmpresa(Empresa empresa) {
        String sql = "SELECT id FROM empresa WHERE codigo_empresa = ? AND cif = ? AND nombre_empresa = ? AND direccion = ? AND codigo_postal = ? AND localidad = ? AND jornada = ? AND modalidad = ? AND mail = ? AND dni_responsable_legal = ? AND dni_tutor_legal = ? AND nombre_responsable_legal = ? AND nombre_tutor_laboral = ? AND apellidos_responsable_legal = ? AND apellidos_tutor_laboral = ? AND telefono_tutor_laboral = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empresa.getCodigo_emrpresa());
            pstmt.setString(2, empresa.getCif());
            pstmt.setString(3, empresa.getNombre_empresa());
            pstmt.setString(4, empresa.getDireccion());
            pstmt.setString(5, empresa.getCodigo_postal());
            pstmt.setString(6, empresa.getLocalidad());
            pstmt.setString(7, empresa.getJornada());
            pstmt.setString(8, empresa.getModalidad());
            pstmt.setString(9, empresa.getMail());
            pstmt.setString(10, empresa.getDni_responsable_legal());
            pstmt.setString(11, empresa.getDni_tutor_legal());
            pstmt.setString(12, empresa.getNombre_responsable_legal());
            pstmt.setString(13, empresa.getNombre_tutor_laboral());
            pstmt.setString(14, empresa.getApellidos_responsable_legal());
            pstmt.setString(15, empresa.getApellidos_tutor_laboral());
            pstmt.setString(16, empresa.getTelefono_tutor_laboral());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void click_bt_insertar(ActionEvent actionEvent) {
        Empresa empresa = new Empresa();
        empresa.setCodigo_emrpresa(txt_codigo_Empresa.getText());
        empresa.setCif(txt_CIF.getText());
        empresa.setNombre_empresa(txt_nombre.getText());
        empresa.setDireccion(txt_direccion.getText());
        empresa.setCodigo_postal(txt_CP.getText());
        empresa.setLocalidad(txt_localidad.getText());
        empresa.setJornada(smb_jornada.getValue().toString());
        empresa.setModalidad(smb_modalidad.getValue().toString());
        empresa.setMail(txt_mail.getText());
        empresa.setDni_responsable_legal(txt_dni_Rep_Legal.getText());
        empresa.setDni_tutor_legal(txt_dni_Tut_Laboral.getText());
        empresa.setNombre_responsable_legal(txt_nombre_Rl.getText());
        empresa.setNombre_tutor_laboral(txt_nombre_Tl.getText());
        empresa.setApellidos_responsable_legal(txt_apellidos_Rl.getText());
        empresa.setApellidos_tutor_laboral(txt_apellidos_Tl.getText());
        empresa.setTelefono_tutor_laboral(txt_tlfTL.getText());
        insertEmpresa(empresa);

        ObservableList<Empresa> data = FXCollections.observableArrayList(getEmpresas());
        tab_reflejo_Tabla_De_Datos.setItems(data);
        tab_reflejo_Tabla_De_Datos.refresh();
    }

    public void click_bt_modificar(ActionEvent actionEvent) {
        Empresa empresa = tab_reflejo_Tabla_De_Datos.getSelectionModel().getSelectedItem();
        empresa.setCodigo_emrpresa(txt_codigo_Empresa.getText());
        empresa.setCif(txt_CIF.getText());
        empresa.setNombre_empresa(txt_nombre.getText());
        empresa.setDireccion(txt_direccion.getText());
        empresa.setCodigo_postal(txt_CP.getText());
        empresa.setLocalidad(txt_localidad.getText());
        empresa.setJornada(smb_jornada.getValue().toString());
        empresa.setModalidad(smb_modalidad.getValue().toString());
        empresa.setMail(txt_mail.getText());
        empresa.setDni_responsable_legal(txt_dni_Rep_Legal.getText());
        empresa.setDni_tutor_legal(txt_dni_Tut_Laboral.getText());
        empresa.setNombre_responsable_legal(txt_nombre_Rl.getText());
        empresa.setNombre_tutor_laboral(txt_nombre_Tl.getText());
        empresa.setApellidos_responsable_legal(txt_apellidos_Rl.getText());
        empresa.setApellidos_tutor_laboral(txt_apellidos_Tl.getText());
        empresa.setTelefono_tutor_laboral(txt_tlfTL.getText());
        updateEmpresa(empresa);
        tab_reflejo_Tabla_De_Datos.refresh();
    }

    public void click_bt_eliminar(ActionEvent actionEvent) {
        // Obtiene la empresa seleccionada en la tabla
        Empresa empresa = tab_reflejo_Tabla_De_Datos.getSelectionModel().getSelectedItem();

        // Si no hay ninguna empresa seleccionada, termina el método
        if (empresa == null) {
            return;
        }

        // Elimina la empresa de la base de datos
        deleteEmpresa(empresa);

        // Actualiza la tabla
        ObservableList<Empresa> data = FXCollections.observableArrayList(getEmpresas());
        tab_reflejo_Tabla_De_Datos.setItems(data);
        tab_reflejo_Tabla_De_Datos.refresh();
    }

    public void click_smb_eleccionEmpresa(ActionEvent actionEvent) {
    }

    public void click_smb_eleccionAlumno(ActionEvent actionEvent) {
    }

    public void click_smb_eleccionTutor(ActionEvent actionEvent) {
    }
}