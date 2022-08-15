/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Contato;

/**
 *
 * @author anton
 */
public class JDBCContato {
    
    Connection conexao;
    
    public JDBCContato(Connection conexao){
        this.conexao = conexao;
    }
    
    public void inserirContato(Contato c){
        String sql = "insert into contato(nome,telefone) values(?, ?)";
        PreparedStatement ps;
        
        try{
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void atualizaContato(String tel, int id){
        String sql = "update contato set telefone = ? where id = ?";
        PreparedStatement ps;
        
        try{
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, tel);
            ps.setInt(2, id);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Contato> listarContatos(){
        ArrayList<Contato> Contatos = new ArrayList<Contato>();
        String sql = "select * from contato";
        
        try{
            Statement declaracao = conexao.createStatement();
            ResultSet resposta = declaracao.executeQuery(sql);
            
            while(resposta.next()){
           
                int id = resposta.getInt("id");
                String nome = resposta.getString("nome");
                String telefone = resposta.getString("telefone");
                
                Contato c = new Contato(id, nome, telefone);
                Contatos.add(c);
                        
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return Contatos;
    }
    
    public void apagarContato(int id){
        String sql = "delete from contato where id = ?";
        
        PreparedStatement ps;
        
        try{
            ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void apagarTudo(){
        String sql = "delete from contato";
        
        PreparedStatement ps;
        
        try{
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
