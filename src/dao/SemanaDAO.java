/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Semana;

/**
 *
 * @author Bernardo
 */
public class SemanaDAO {
    
     public boolean inserir(Semana semana) throws SQLException {
        String sql = "INSERT INTO semanas (dia_comeco, dia_fim) VALUES (?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, semana.getDiaComeco());
            stmt.setString(2, semana.getDiaFim());

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    semana.setId(rs.getInt(1));
                }
                rs.close();
                ConexaoDB.commit();
                return true;
            }

            return false;

        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        }
    }

    public boolean atualizar(Semana semana) throws SQLException {
        String sql = "UPDATE semanas SET dia_comeco = ?, dia_fim = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, semana.getDiaComeco());
            stmt.setString(2, semana.getDiaFim());
            stmt.setInt(3, semana.getId());

            int resultado = stmt.executeUpdate();
            ConexaoDB.commit();

            return resultado > 0;

        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM semanas WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int resultado = stmt.executeUpdate();
            ConexaoDB.commit();

            return resultado > 0;

        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        }
    }

    public Semana buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM semanas WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Semana semana = criarSemanaDoResultSet(rs);
                rs.close();
                return semana;
            }

            rs.close();
            return null;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Semana> listarTodos() throws SQLException {
        String sql = "SELECT * FROM semanas ORDER BY dia_comeco DESC";

        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Semana> semanas = new ArrayList<>();

            while (rs.next()) {
                semanas.add(criarSemanaDoResultSet(rs));
            }

            return semanas;

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Semana> buscarPorDiaComeco(String diaComeco) throws SQLException {
        String sql = "SELECT * FROM semanas WHERE dia_comeco = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diaComeco);

            ResultSet rs = stmt.executeQuery();

            List<Semana> semanas = new ArrayList<>();
            while (rs.next()) {
                semanas.add(criarSemanaDoResultSet(rs));
            }

            rs.close();
            return semanas;

        } catch (SQLException e) {
            throw e;
        }
    }

    private Semana criarSemanaDoResultSet(ResultSet rs) throws SQLException {
        Semana semana = new Semana();
        semana.setId(rs.getInt("id"));
        semana.setDiaComeco(rs.getString("dia_comeco"));
        semana.setDiaFim(rs.getString("dia_fim"));
        return semana;
    }
}
