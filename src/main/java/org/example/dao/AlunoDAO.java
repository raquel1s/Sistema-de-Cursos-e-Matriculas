package org.example.dao;

import org.example.conexao.Conexao;
import org.example.model.Aluno;
import org.example.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {

    public static ArrayList<Aluno> listarAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT id, nome, email FROM aluno";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                alunos.add(new Aluno(id, nome, email));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return alunos;
    }

    public static void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno(nome, email) VALUES(?,?)";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.executeUpdate();

            System.out.println("\nAluno cadastrado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deletarAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("\nAluno removido com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Aluno> listarAlunosNaoMatriculados() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT a.id AS id, " +
                "a.nome AS nome, " +
                "a.email AS email " +
                "FROM aluno a " +
                "LEFT JOIN matricula m " +
                "ON m.aluno_id = a.id " +
                "LEFT JOIN curso c " +
                "ON c.id = m.curso_id " +
                "WHERE c.nome IS NULL";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                alunos.add(new Aluno(id, nome, email));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return alunos;
    }


    public static ArrayList<Aluno> alunoPorCurso(Curso curso) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT a.id as id, " +
                "a.nome as nome, " +
                "a.email as email " +
                "FROM aluno a " +
                "LEFT JOIN matricula m " +
                "ON m.aluno_id = a.id " +
                "LEFT JOIN curso c " +
                "ON c.id = m.curso_id " +
                "WHERE c.id = " + curso.getId();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                alunos.add(new Aluno(id, nome, email));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return alunos;
    }
}
