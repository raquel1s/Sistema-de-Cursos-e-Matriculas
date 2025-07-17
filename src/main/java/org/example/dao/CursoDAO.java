package org.example.dao;

import org.example.conexao.Conexao;
import org.example.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAO {

    public static void cadastrarCurso(Curso curso){
        String sql = "INSERT INTO curso (nome, descricao) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.executeUpdate();

            System.out.println("\nCurso cadastrado com sucesso!");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Curso> listarCursos() {
        ArrayList<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM curso";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                cursos.add(new Curso(id, nome, descricao));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return cursos;
    }

    public static void deletarCurso(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("\nCurso removido com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String cursosNenhumAluno(int id) {
        String sql = "select c.nome as Curso " +
                "from curso c " +
                "left join matricula m " +
                "on m.curso_id = c.id " +
                "left join aluno a " +
                "on a.id = m.aluno_id " +
                "where a.id is null;";
    }
}
