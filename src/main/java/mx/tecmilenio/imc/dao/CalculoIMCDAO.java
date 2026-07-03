package mx.tecmilenio.imc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.tecmilenio.imc.model.CalculoIMC;
import mx.tecmilenio.imc.util.DBConnection;

public class CalculoIMCDAO {
    public void guardar(CalculoIMC calculo) throws SQLException {
        String sql = "INSERT INTO calculos_imc (usuario_id, peso, imc, categoria) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, calculo.getUsuarioId());
            ps.setDouble(2, calculo.getPeso());
            ps.setDouble(3, calculo.getImc());
            ps.setString(4, calculo.getCategoria());
            ps.executeUpdate();
        }
    }

    public List<CalculoIMC> listarPorUsuario(int usuarioId) throws SQLException {
        List<CalculoIMC> lista = new ArrayList<>();
        String sql = "SELECT id, usuario_id, peso, imc, categoria, DATE_FORMAT(fecha, '%Y-%m-%d %H:%i:%s') AS fecha FROM calculos_imc WHERE usuario_id = ? ORDER BY fecha DESC";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new CalculoIMC(
                            rs.getInt("id"),
                            rs.getInt("usuario_id"),
                            rs.getDouble("peso"),
                            rs.getDouble("imc"),
                            rs.getString("categoria"),
                            rs.getString("fecha")
                    ));
                }
            }
        }
        return lista;
    }
}
