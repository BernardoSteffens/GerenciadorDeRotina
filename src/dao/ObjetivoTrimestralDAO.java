/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Bernardo
 */
public class ObjetivoTrimestralDAO {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean inserir(ObjetivoTrimestral objetivo) throws SQLException {
        String sql = "INSERT INTO objetivos_trimestrais (titulo, descricao, data_inicio, " +
                     "data_fim, progresso, concluido, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, objetivo.getTitulo());
            stmt.setString(2, objetivo.getDescricao());
            stmt.setString(3, dateFormat.format(objetivo.getDataInicio()));
            stmt.setString(4, dateFormat.format(objetivo.getDataFim()));
            stmt.setInt(5, objetivo.getProgresso());
            stmt.setInt(6, objetivo.isConcluido() ? 1 : 0);
            
            // Verifica se existe categoria definida
            if (objetivo.getCategoriaId() > 0) {
                stmt.setInt(7, objetivo.getCategoriaId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            int resultado = stmt.executeUpdate();
            
            // Obtém o ID gerado
            if (resultado > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    objetivo.setId(rs.getInt(1));
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
    
    public boolean atualizar(ObjetivoTrimestral objetivo) throws SQLException {
        String sql = "UPDATE objetivos_trimestrais SET titulo = ?, descricao = ?, " +
                     "data_inicio = ?, data_fim = ?, progresso = ?, concluido = ?, " +
                     "categoria_id = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, objetivo.getTitulo());
            stmt.setString(2, objetivo.getDescricao());
            stmt.setString(3, dateFormat.format(objetivo.getDataInicio()));
            stmt.setString(4, dateFormat.format(objetivo.getDataFim()));
            stmt.setInt(5, objetivo.getProgresso());
            stmt.setInt(6, objetivo.isConcluido() ? 1 : 0);
            
            // Verifica se existe categoria definida
            if (objetivo.getCategoriaId() > 0) {
                stmt.setInt(7, objetivo.getCategoriaId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(8, objetivo.getId());
            
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
        String sql = "DELETE FROM objetivos_trimestrais WHERE id = ?";
        
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
    
    public ObjetivoTrimestral buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM objetivos_trimestrais WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return criarObjetivoTrimestralDoResultSet(rs);
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
    
    public List<ObjetivoTrimestral> buscarAtivos() throws SQLException {
        String sql = "
}
