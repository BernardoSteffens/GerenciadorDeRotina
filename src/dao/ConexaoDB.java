package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bernardo
 */
public class ConexaoDB {
    private static final String URL = "jdbc:sqlite:gerenciador_rotina.db";
    private static Connection conexao;

    public static Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try {
                Class.forName("org.sqlite.JDBC");
                
                conexao = DriverManager.getConnection(URL);
                
                conexao.setAutoCommit(false);
                
                System.out.println("Conexão com SQLite estabelecida com sucesso!");
                
                criarTabelas();
                
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC do SQLite não encontrado: " + e.getMessage());
                throw new SQLException("Driver SQLite não encontrado", e);
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao SQLite: " + e.getMessage());
                throw e;
            }
        }
        return conexao;
    }
    
    private static void criarTabelas() throws SQLException {
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS categorias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "cor TEXT NOT NULL" +
                    ")");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS objetivos_trimestrais (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "descricao TEXT, " +
                    "data_inicio TEXT NOT NULL, " +
                    "data_fim TEXT NOT NULL, " +
                    "progresso INTEGER DEFAULT 0, " +
                    "concluido INTEGER DEFAULT 0, " +
                    "categoria_id INTEGER, " +
                    "FOREIGN KEY (categoria_id) REFERENCES categorias (id)" +
                    ")");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS tarefas_semanais (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "descricao TEXT, " +
                    "prioridade INTEGER DEFAULT 0, " +
                    "concluida INTEGER DEFAULT 0, " +
                    "categoria_id INTEGER, " +
                    "objetivo_id INTEGER, " +
                    "semana_id INTEGER," +
                    "FOREIGN KEY (categoria_id) REFERENCES categorias (id), " +
                    "FOREIGN KEY (objetivo_id) REFERENCES objetivos_trimestrais (id)" +
                    ")");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS semanas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "dia_comeco TEXT NOT NULL, " +
                    "dia_fim TEXT NOT NULL " +
                    ")"
            );
            
            stmt.execute("CREATE TABLE IF NOT EXISTS tarefas_diarias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "descricao TEXT, " +
                    "data TEXT NOT NULL, " +
                    "hora_inicio TEXT, " +
                    "hora_fim TEXT, " +
                    "prioridade INTEGER DEFAULT 0, " +
                    "concluida INTEGER DEFAULT 0, " +
                    "categoria_id INTEGER, " +
                    "tarefa_semanal_id INTEGER, " +
                    "FOREIGN KEY (categoria_id) REFERENCES categorias (id), " +
                    "FOREIGN KEY (tarefa_semanal_id) REFERENCES tarefas_semanais (id)" +
                    ")");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS eventos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "descricao TEXT, " +
                    "data TEXT NOT NULL, " +
                    "hora TEXT, " +
                    "local TEXT, " +
                    "categoria_id INTEGER, " +
                    "FOREIGN KEY (categoria_id) REFERENCES categorias (id)" +
                    ")");
            
            conexao.commit();
            System.out.println("Tabelas criadas com sucesso!");
            
        } catch (SQLException e) {
            if (conexao != null) {
                conexao.rollback();
            }
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
            throw e;
        }
    }
   
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão com SQLite fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com SQLite: " + e.getMessage());
        }
    }
    
    public static void commit() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.commit();
        }
    }
    
    public static void rollback() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar rollback: " + e.getMessage());
        }
    }
}

