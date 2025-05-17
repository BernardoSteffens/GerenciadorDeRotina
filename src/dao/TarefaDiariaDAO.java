package dao;

import java.text.SimpleDateFormat;
import model.TarefaDiaria;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bernardo
 */
public class TarefaDiariaDAO {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean inserir(TarefaDiaria tarefa) throws SQLException {
        String sql = "INSERT INTO tarefas_diarias (titulo, descricao, data, hora_inicio, " +
                     "hora_fim, prioridade, concluida, categoria_id, tarefa_semanal_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getData());
            stmt.setString(4, tarefa.getHoraInicio());
            stmt.setString(5, tarefa.getHoraFim());
            stmt.setInt(6, tarefa.getPrioridade());
            stmt.setInt(7, tarefa.isConcluida() ? 1 : 0);
            
            if (tarefa.getCategoriaId() > 0) {
                stmt.setInt(8, tarefa.getCategoriaId());
            } else {
                stmt.setNull(8, java.sql.Types.INTEGER);
            }
            
            if (tarefa.getTarefaSemanalId() > 0) {
                stmt.setInt(9, tarefa.getTarefaSemanalId());
            } else {
                stmt.setNull(9, java.sql.Types.INTEGER);
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
    
    public boolean atualizar(TarefaDiaria tarefa) throws SQLException {
        String sql = "UPDATE tarefas_diarias SET titulo = ?, descricao = ?, data = ?, " +
                     "hora_inicio = ?, hora_fim = ?, prioridade = ?, concluida = ?, " +
                     "categoria_id = ?, tarefa_semanal_id = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getData());
            stmt.setString(4, tarefa.getHoraInicio());
            stmt.setString(5, tarefa.getHoraFim());
            stmt.setInt(6, tarefa.getPrioridade());
            stmt.setInt(7, tarefa.isConcluida() ? 1 : 0);
            
            if (tarefa.getCategoriaId() > 0) {
                stmt.setInt(8, tarefa.getCategoriaId());
            } else {
                stmt.setNull(8, java.sql.Types.INTEGER);
            }
            
            if (tarefa.getTarefaSemanalId() > 0) {
                stmt.setInt(9, tarefa.getTarefaSemanalId());
            } else {
                stmt.setNull(9, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(10, tarefa.getId());
            
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
        String sql = "DELETE FROM tarefas_diarias WHERE id = ?";
        
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
    
    public TarefaDiaria buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefas_diarias WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarTarefaDiariaDoResultSet(rs);
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
    
    public List<TarefaDiaria> buscarPorData(String data) throws SQLException {
        String sql = "SELECT * FROM tarefas_diarias WHERE data = ? ORDER BY prioridade";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, data);
            
            rs = stmt.executeQuery();
            
            List<TarefaDiaria> tarefas = new ArrayList<>();
            
            while (rs.next()) {
                tarefas.add(criarTarefaDiariaDoResultSet(rs));
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
    
    public List<TarefaDiaria> listarTodos() throws SQLException {
        String sql = "SELECT * FROM tarefas_diarias ORDER BY data DESC, hora_inicio";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            
            List<TarefaDiaria> tarefas = new ArrayList<>();
            
            while (rs.next()) {
                tarefas.add(criarTarefaDiariaDoResultSet(rs));
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
    
    public List<TarefaDiaria> buscarPorFiltros(String titulo, String data, int prioridade, int concluida) throws SQLException {
         
        StringBuilder sql = new StringBuilder("SELECT * FROM tarefas_diarias WHERE 1=1");
        List<String> parametros = new ArrayList<>();

        if (titulo != null && !titulo.trim().isEmpty()) {
            sql.append(" AND titulo LIKE ?");
            parametros.add("%" + titulo + "%");
        }

        if (data != null && !data.trim().isEmpty()) {
            sql.append(" AND data = ?");
            parametros.add(data);
        }

        if (prioridade != 0) {
            sql.append(" AND prioridade = ?");
            parametros.add(Integer.toString(prioridade));
        }

        if (concluida != -1) {
            sql.append(" AND concluida = ?");
            parametros.add(Integer.toString(concluida));
        }

        sql.append(" ORDER BY hora_inicio");

        Connection conn = ConexaoDB.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        for (int i = 0; i < parametros.size(); i++) {
            stmt.setString(i + 1, parametros.get(i));
        }
        
        ResultSet rs = stmt.executeQuery();
        List<TarefaDiaria> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(criarTarefaDiariaDoResultSet(rs));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    
    private TarefaDiaria criarTarefaDiariaDoResultSet(ResultSet rs) throws SQLException {
        TarefaDiaria tarefa = new TarefaDiaria();
        
        tarefa.setId(rs.getInt("id"));
        tarefa.setTitulo(rs.getString("titulo"));
        tarefa.setDescricao(rs.getString("descricao"));
        tarefa.setData(rs.getString("data"));
        
        tarefa.setHoraInicio(rs.getString("hora_inicio"));
        tarefa.setHoraFim(rs.getString("hora_fim"));
        tarefa.setPrioridade(rs.getInt("prioridade"));
        tarefa.setConcluida(rs.getInt("concluida") == 1);
        
        if (rs.getObject("categoria_id") != null) {
            tarefa.setCategoriaId(rs.getInt("categoria_id"));
        }
        
        if (rs.getObject("tarefa_semanal_id") != null) {
            tarefa.setTarefaSemanalId(rs.getInt("tarefa_semanal_id"));
        }
        
        return tarefa;
    }
}
