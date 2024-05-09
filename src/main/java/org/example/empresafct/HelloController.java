package org.example.empresafct;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField txt_codigo_Empresa;
    @FXML
    private TextField txt_CIF;
    // Tus otros campos de texto aquí

    private EmpresaDAO empresaDAO;

    public HelloController() {
        // Aquí necesitas inicializar empresaDAO.
        // Necesitarás una conexión a la base de datos para esto.
        // Por ejemplo:
        // this.empresaDAO = new EmpresaDAO(myDatabaseConnection);
    }

    @FXML
    protected void onInsertarButtonClick() {
        // Crea una nueva empresa con los datos de los campos de texto
        // Valida los datos de la empresa
        // Inserta la empresa en la base de datos
        // Actualiza la tabla
    }

    @FXML
    protected void onModificarButtonClick() {
        // Obtiene la empresa seleccionada en la tabla
        // Modifica los datos de la empresa con los datos de los campos de texto
        // Valida los datos de la empresa
        // Modifica la empresa en la base de datos
        // Actualiza la tabla
    }

    @FXML
    protected void onEliminarButtonClick() {
        // Obtiene la empresa seleccionada en la tabla
        // Elimina la empresa de la base de datos
        // Actualiza la tabla
    }
}