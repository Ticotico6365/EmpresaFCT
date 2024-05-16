package org.example.empresafct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
    private Connection connection;

    public EmpresaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Empresa> getAllEmpresas() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        String query = "SELECT * FROM empresas";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Empresa empresa = new Empresa();
                empresa.setCodigoEmpresa(resultSet.getString("codigoEmpresa"));
                empresa.setCif(resultSet.getString("cif"));
                empresa.setNombre(resultSet.getString("nombre"));
                empresa.setDireccion(resultSet.getString("direccion"));
                empresa.setCp(resultSet.getString("cp"));
                empresa.setLocalidad(resultSet.getString("localidad"));
                empresa.setJornada(resultSet.getString("jornada"));
                empresa.setModalidad(resultSet.getString("modalidad"));
                empresa.setMail(resultSet.getString("mail"));
                empresa.setDniRepLegal(resultSet.getString("dniRepLegal"));
                empresa.setNombreRepLegal(resultSet.getString("nombreRepLegal"));
                empresa.setApellidosRepLegal(resultSet.getString("apellidosRepLegal"));
                empresa.setDniTutLaboral(resultSet.getString("dniTutLaboral"));
                empresa.setNombreTutLaboral(resultSet.getString("nombreTutLaboral"));
                empresa.setApellidosTutLaboral(resultSet.getString("apellidosTutLaboral"));
                empresa.setTelefonoTutLaboral(resultSet.getString("telefonoTutLaboral"));
                empresas.add(empresa);
            }
        }
        return empresas;
    }
}
