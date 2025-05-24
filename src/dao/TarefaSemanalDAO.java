package dao;

import model.TarefaSemanal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bernardo
 */
public class TarefaSemanalDAO {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean inserir(TarefaSemanal tarefa) throws SQLException {
        String sql = "INSERT INTO tarefas_semanais (titulo, descricao, prioridade, concluida, categoria_id, objetivo_id, semana_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getPrioridade());
            stmt.setInt(4, tarefa.isConcluida() ? 1 : 0);
            stmt.setInt(7, tarefa.getSemanaId());
            
            if (tarefa.getCategoriaId() > 0) {
                stmt.setInt(5, tarefa.getCategoriaId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (tarefa.getObjetivoId() > 0) {
                stmt.setInt(6, tarefa.getObjetivoId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            
            int resultado = stmt.executeUpdate();
            
            if (resultado > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    tarefa.setId(rs.getInt(1));
                }
                rs.close();
                
                ConexaoDB.commit();
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public boolean atualizar(TarefaSemanal tarefa) throws SQLException {
        String sql = "UPDATE tarefas_semanais SET titulo = ?, descricao = ?, prioridade = ?, concluida = ?, categoria_id = ?, " +
                     "objetivo_id = ?, semana_id = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getPrioridade());
            stmt.setInt(4, tarefa.isConcluida() ? 1 : 0);
            
            if (tarefa.getCategoriaId() > 0) {
                stmt.setInt(5, tarefa.getCategoriaId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (tarefa.getObjetivoId() > 0) {
                stmt.setInt(6, tarefa.getObjetivoId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            stmt.setInt(7, tarefa.getSemanaId());
            stmt.setInt(8, tarefa.getId());
            
            int resultado = stmt.executeUpdate();
            ConexaoDB.commit();
            
            return resultado > 0;
            
        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM tarefas_semanais WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            int resultado = stmt.executeUpdate();
            ConexaoDB.commit();
            
            return resultado > 0;
            
        } catch (SQLException e) {
            ConexaoDB.rollback();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public TarefaSemanal buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefas_semanais WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarTarefaSemanalDoResultSet(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public List<TarefaSemanal> buscarPorIdSemana(int idSemana) throws SQLException {
       
        String sql = "SELECT * FROM tarefas_semanais WHERE semana_id = ?" +
                     "ORDER BY titulo";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, Integer.toString(idSemana));
            
            rs = stmt.executeQuery();
            
            List<TarefaSemanal> tarefas = new ArrayList<>();
            
            while (rs.next()) {
                tarefas.add(criarTarefaSemanalDoResultSet(rs));
            }
            
            return tarefas;
            
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public List<TarefaSemanal> listarTodos() throws SQLException {
        String sql = "SELECT * FROM tarefas_semanais ORDER BY semana_id";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            
            List<TarefaSemanal> tarefas = new ArrayList<>();
            
            while (rs.next()) {
                tarefas.add(criarTarefaSemanalDoResultSet(rs));
            }
            
            return tarefas;
            
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
        
    private TarefaSemanal criarTarefaSemanalDoResultSet(ResultSet rs) throws SQLException {
        TarefaSemanal tarefa = new TarefaSemanal();
        
        tarefa.setId(rs.getInt("id"));
        tarefa.setTitulo(rs.getString("titulo"));
        tarefa.setDescricao(rs.getString("descricao"));
        tarefa.setPrioridade(rs.getInt("prioridade"));
        tarefa.setConcluida(rs.getInt("concluida") == 1);
        tarefa.setSemanaId(rs.getInt("semana_id"));
        
        if (rs.getObject("categoria_id") != null) {
            tarefa.setCategoriaId(rs.getInt("categoria_id"));
        }
        
        if (rs.getObject("objetivo_id") != null) {
            tarefa.setObjetivoId(rs.getInt("objetivo_id"));
        }
        
        return tarefa;
    }
}
