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
        String query = "SELECT * FROM empresa";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Empresa empresa = new Empresa();
                empresa.setCodigoEmpresa(resultSet.getString("codigo_empresa"));
                empresa.setCif(resultSet.getString("cif"));
                empresa.setNombre(resultSet.getString("nombre_empresa"));
                empresa.setDireccion(resultSet.getString("direccion"));
                empresa.setCp(resultSet.getString("codigo_postal"));
                empresa.setLocalidad(resultSet.getString("localidad"));
                empresa.setJornada(resultSet.getString("jornada"));
                empresa.setModalidad(resultSet.getString("modalidad"));
                empresa.setMail(resultSet.getString("mail"));
                empresa.setDniRepLegal(resultSet.getString("dni_responsable_legal"));
                empresa.setNombreRepLegal(resultSet.getString("nombre_responsable_legal"));
                empresa.setApellidosRepLegal(resultSet.getString("apellidos_responsable_legal"));
                empresa.setDniTutLaboral(resultSet.getString("dni_responsable_legal"));
                empresa.setNombreTutLaboral(resultSet.getString("nombre_tutor_laboral"));
                empresa.setApellidosTutLaboral(resultSet.getString("apellidos_tutor_laboral"));
                empresa.setTelefonoTutLaboral(resultSet.getString("telefono_tutor_laboral"));
                empresas.add(empresa);
            }
        }
        return empresas;
    }



    public void updateEmpresa(Empresa updatedEmpresa) {
        String query = "UPDATE empresa SET cif = ?, nombre_empresa = ?, direccion = ?, codigo_postal = ?, localidad = ?, jornada = ?, modalidad = ?, mail = ?, dni_responsable_legal = ?, nombre_responsable_legal = ?, apellidos_responsable_legal = ?, dni_tutor_laboral = ?, nombre_tutor_laboral = ?, apellidos_tutor_laboral = ?, telefono_tutor_laboral = ? WHERE codigo_empresa = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedEmpresa.getCif());
            statement.setString(2, updatedEmpresa.getNombre());
            statement.setString(3, updatedEmpresa.getDireccion());
            statement.setString(4, updatedEmpresa.getCp());
            statement.setString(5, updatedEmpresa.getLocalidad());
            statement.setString(6, updatedEmpresa.getJornada());
            statement.setString(7, updatedEmpresa.getModalidad());
            statement.setString(8, updatedEmpresa.getMail());
            statement.setString(9, updatedEmpresa.getDniRepLegal());
            statement.setString(10, updatedEmpresa.getNombreRepLegal());
            statement.setString(11, updatedEmpresa.getApellidosRepLegal());
            statement.setString(12, updatedEmpresa.getDniTutLaboral());
            statement.setString(13, updatedEmpresa.getNombreTutLaboral());
            statement.setString(14, updatedEmpresa.getApellidosTutLaboral());
            statement.setString(15, updatedEmpresa.getTelefonoTutLaboral());
            statement.setString(16, updatedEmpresa.getCodigoEmpresa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmpresa(String codigoEmpresa) {
        String query = "DELETE FROM empresa WHERE codigo_empresa = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigoEmpresa);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
