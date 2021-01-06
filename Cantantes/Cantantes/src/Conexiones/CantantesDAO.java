/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import static Conexiones.Conexion.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.Cantantes;

/**
 *
 * @author sermar
 */
public class CantantesDAO {

    private static final String SQL_select = "SELECT nombreReal, nombreArt, premios, discos, fecha_nac, fecha_falle, idArt FROM artistas";
    private static final String SQL_INSERT = "INSERT INTO artistas (nombreReal,nombreArt,premios,discos,fecha_nac,fecha_falle) VALUES (?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM artistas WHERE idArt = ?";
    private static final String SQL_UPDATE = "UPDATE artistas SET nombreReal = ?, nombreArt = ?, premios = ?, discos = ?, fecha_nac = ?, fecha_falle = ? WHERE idArt = ?";

    public List<Cantantes> seleccionar() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cantantes cantante = null;
        List<Cantantes> cantantes = new ArrayList<>();

        try {

            conn = getConnection();
            stm = conn.prepareStatement(SQL_select);
            rs = stm.executeQuery();
            while (rs.next()) {
                String nombreReal = rs.getString("nombreReal");
                String nombreArt = rs.getString("nombreArt");
                int premios = rs.getInt("premios");
                int discos = rs.getInt("discos");
                String fecha_nac = rs.getString("fecha_nac");
                String fecha_falle = rs.getString("fecha_falle");
                int idArt = rs.getInt("idArt");
                cantante = new Cantantes(nombreReal, nombreArt, premios, discos, fecha_nac, fecha_falle, idArt);
                cantantes.add(cantante);

            }
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stm);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return cantantes;
    }

    public int insertar(Cantantes cantante) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cantante.getNombreReal());
            stmt.setString(2, cantante.getNombreArt());
            stmt.setInt(3, cantante.getPremios());
            stmt.setInt(4, cantante.getDiscos());
            stmt.setString(5, cantante.getFecha_nac());
            stmt.setString(6, cantante.getFecha_falle());
            registros = stmt.executeUpdate();

        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Cantantes cantante) throws SQLException {
        int regis = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cantante.getIdArt());
            regis = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return regis;
    }

    public int actualizar(Cantantes cantante) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cantante.getNombreReal());
            stmt.setString(2, cantante.getNombreArt());
            stmt.setInt(3, cantante.getPremios());
            stmt.setInt(4, cantante.getDiscos());
            stmt.setString(5, cantante.getFecha_nac());
            stmt.setString(6, cantante.getFecha_falle());
            stmt.setInt(7, cantante.getIdArt());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
