package org.example.empresafct;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
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
import java.sql.SQLException;
import java.util.List;

public class HelloController {
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
            // Cargar las empresas desde la base de datos y mostrarlas en la tabla
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
        // Establecer la conexión a la base de datos
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
    private void updateEmpresa(ActionEvent actionEvent) {
        // Aquí va tu código existente para updateEmpresa
    }

    @FXML
    private void deleteEmpresa(ActionEvent actionEvent) {
        // Obtener el codigoEmpresa de la empresa que quieres eliminar
        String codigoEmpresa = txt_codigo_Empresa.getText();
        // Eliminar la empresa de la base de datos
        empresaDAO.deleteEmpresa(codigoEmpresa);
    }


}