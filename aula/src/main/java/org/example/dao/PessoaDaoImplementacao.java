package aula.src.main.java.org.example.dao;

import aula.src.main.java.org.example.database.DbConnection;
import aula.src.main.java.org.example.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDaoImplementacao implements PessoaDao{

    // metodos que farão o  CRUD
    @Override
    public int addPessoa(Pessoa p) {
        // essa query sera o comando SQL para insercao no banco de dados
        String query = "insert into Pessoa(Name, Email) values(?,?)";
        int rows = 0;
        try(Connection connection = DbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            rows = ps.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    @Override
    public int updatePessoa(Pessoa p) {
        String query = "update Pessoa set Name = ?, Email = ? where Id = ?";
        int rows = 0;
        try(Connection connection = DbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setInt(3, p.getId());
            return ps.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deletePessoa(int id) {
        String query = "delete from Pessoa where Id = ?";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            return ps.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pessoa getPessoa(int id) {
        String query = "select * from Pessoa where Id = ?";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                return new Pessoa(id, nome, email);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<Pessoa> getAllPessoas() {
        var usuario = new ArrayList<Pessoa>();
        String query = "select id as ID, Nome, Email from Pessoa order by ID";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                do {
                    int id = rs.getInt("ID");
                    String nome = rs.getString("Nome");
                    String email = rs.getString("Email");
                    usuario.add(new Pessoa(id, nome, email));
                }while (rs.next());
            }

        }catch (SQLException e) {
            System.out.println("Erro ao se conectar.");
            e.printStackTrace();
        }
        return usuario;
    }
}
