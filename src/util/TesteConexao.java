/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.ConexaoDB;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Bernardo
 */
public class TesteConexao {
    
    public static void main(String[] args) {
        try {
            Connection conn = (Connection) ConexaoDB.getConexao();
            System.out.println("Conexão funcionando perfeitamente!");
            
            ConexaoDB.fecharConexao();
            
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
