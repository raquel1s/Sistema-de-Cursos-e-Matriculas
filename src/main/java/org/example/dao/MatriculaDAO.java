package org.example.dao;

import org.example.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDAO {

    public static void matricularAluno(int idAluno, int idCurso) {
        String sql = "INSERT INTO matricula (aluno_id, curso_id) VALUES (?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);
            stmt.executeUpdate();

            System.out.println("\nAluno matriculado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
