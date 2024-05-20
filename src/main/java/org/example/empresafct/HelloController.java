package org.example.empresafct;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HelloController {
    public TextField txt_CIF;
    public TextField txt_nombre;
    public TextField txt_direccion;
    public TextField txt_CP;
    public TextField txt_localidad;
    public SplitMenuButton smb_jornada;
    public SplitMenuButton smb_modalidad;
    public TextField txt_mail;
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
    @FXML
    private TableView<Empresa> empresaTableView;
    @FXML
    private TextField txt_codigo_Empresa;

    private Connection connection;
    private EmpresaDAO empresaDAO;
    public Tab tap_alumnos;
    public Button bt_crearDATalumnos;
    public Label lab_infoAlumnos;
    public Tab tab_tutores;
    public Button bt_crearXMLtutores;
    public Label lab_infoTutores;

    @FXML
    private void initialize() {
        try {
            loadEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadEmpresas() throws SQLException {
        List<Empresa> empresas = empresaDAO.getAllEmpresas();
        empresaTableView.getItems().addAll(empresas);
    }

    public HelloController() {
        this.connection = DatabaseConnection.getConnection();
        this.empresaDAO = new EmpresaDAO(connection);
    }

    public void click_bt_crearDATalumnos(ActionEvent actionEvent) {
        // Aquí va tu código existente para click_bt_crearDATalumnos
    }

    public static void readXMLFileAndSaveToDatabase() {
        // Aquí va tu código existente para readXMLFileAndSaveToDatabase
    }

    public void click_bt_crearXMLtutores(ActionEvent actionEvent) {
        readXMLFileAndSaveToDatabase();
    }

    @FXML
    private void insertEmpresa(ActionEvent actionEvent) {
        Empresa newEmpresa = new Empresa();
        // Aquí debes obtener los valores de los campos de texto y establecerlos en el objeto newEmpresa
        newEmpresa.setCodigoEmpresa(txt_codigo_Empresa.getText());
        newEmpresa.setCif(txt_CIF.getText());
        // Por ejemplo: newEmpresa.setCif(txt_CIF.getText());
        // Repite este paso para todos los campos

        try {
            empresaDAO.insertEmpresa(newEmpresa);
            loadEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    private void updateEmpresa(ActionEvent actionEvent) {
        // Aquí va tu código existente para updateEmpresa
    }

    @FXML
    private void deleteEmpresa(ActionEvent actionEvent) throws SQLException {
        String codigoEmpresa = txt_codigo_Empresa.getText();
        empresaDAO.deleteEmpresa(codigoEmpresa);
    }
}