package org.example.util;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.model.Aluno;
import org.example.model.Curso;

import java.util.ArrayList;

public class Gerenciador {

    public void listarAlunos(){
        ArrayList<Aluno> alunos = AlunoDAO.listarAlunos();

        if(alunos.isEmpty()){
            System.out.println("Nenhum aluno cadastrado.");
        }else{
            for(Aluno aluno : alunos){
                System.out.println(aluno);
            }
        }
    }

    public void listarCursos(){
        ArrayList<Curso> cursos = CursoDAO.listarCursos();

        if(cursos.isEmpty()){
            System.out.println("Nenhum curso cadastrado.");
        }else{
            for(Curso curso : cursos){
                System.out.println(curso);
            }
        }
    }

    public void listarAlunosCursos(){
        ArrayList<Curso> cursos = CursoDAO.listarCursos();

        if(cursos.isEmpty()){
            System.out.println("Nenhum curso cadastrado.");
        }else{
            for(Curso curso : cursos){
                System.out.println("\n" + curso.getNome()+ ": ");
                ArrayList<Aluno> alunos = AlunoDAO.alunoPorCurso(curso);
                for(Aluno aluno : alunos){
                    System.out.println(aluno);
                }
            }
        }
    }

    public void listarAlunosNaoMatriculados(){
        ArrayList<Aluno> alunos = AlunoDAO.listarAlunosNaoMatriculados();

        if(alunos.isEmpty()){
            System.out.println("Os alunos já estão matriculados.");
        }else{
            for(Aluno aluno : alunos){
                System.out.println(aluno);
            }
        }
    }
}
