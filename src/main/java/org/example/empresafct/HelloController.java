package org.example.empresafct;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HelloController {
    @FXML
    private TableView<Empresa> empresaTableView;

    private Connection connection;
    private EmpresaDAO empresaDAO;

    public HelloController() {
        // Establecer la conexión a la base de datos
        this.connection = DatabaseConnection.getConnection();
        this.empresaDAO = new EmpresaDAO(connection);
    }

    @FXML
    private void initialize() {
        try {
            // Cargar las empresas desde la base de datos y mostrarlas en la tabla
            loadEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error de conexión a la base de datos
        }
    }

    private void loadEmpresas() throws SQLException {
        List<Empresa> empresas = empresaDAO.getAllEmpresas();
        empresaTableView.getItems().addAll(empresas);
    }
}
