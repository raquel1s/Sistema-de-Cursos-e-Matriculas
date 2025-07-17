package org.example.main;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.dao.MatriculaDAO;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.util.Gerenciador;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Gerenciador ger = new Gerenciador();
    
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int opcao = 0;

        do{
            System.out.println("\n=== SISTEMA DE CURSOS E MATRÍCULAS ===");
            System.out.println("1. Listar todos os alunos");
            System.out.println("2. Cadastrar novo aluno");
            System.out.println("3. Cadastrar novo curso");
            System.out.println("4. Matricular aluno em curso");
            System.out.println("5. Listar todos os cursos com seus alunos");
            System.out.println("6. Listar alunos não matriculados em nenhum curso");
            System.out.println("7. Excluir aluno");
            System.out.println("8. Excluir curso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção:_");
            opcao = sc.nextInt();
            
            switch (opcao){
                case 1 -> listarAlunos();
                case 2 -> cadastrarAluno();
                case 3 -> cadastrarCurso();
                case 4 -> matricularAluno();
                case 5 -> listarCursosAlunos();
                case 6 -> listarAlunosNaoMatriculados();
                case 7 -> excluirAluno();
                case 8 -> excluirCurso();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
            
        }while(opcao != 0);
    }

    private static void listarAlunos() {
        ger.listarAlunos();
    }

    private static void cadastrarAluno() {
        sc.nextLine();

        System.out.print("\nDigite o nome do aluno: ");
        String nome = sc.nextLine();

        System.out.print("\nDigite o email do aluno: ");
        String email = sc.next();

        AlunoDAO.cadastrarAluno(new Aluno(nome, email));
    }

    private static void cadastrarCurso() {
        sc.nextLine();

        System.out.print("\nDigite o nome do curso: ");
        String nome = sc.nextLine();

        System.out.print("\nDigite a descrição do curso: ");
        String descricao = sc.nextLine();

        CursoDAO.cadastrarCurso(new Curso(nome, descricao));
    }

    private static void matricularAluno() {
        ger.listarAlunosNaoMatriculados();

        System.out.println("Digite qual aluno deseja matricular: ");
        int idAluno = sc.nextInt();

        ger.listarCursos();

        System.out.println("Digite qual curso deseja matricular o aluno: ");
        int idCurso = sc.nextInt();

        MatriculaDAO.matricularAluno(idAluno, idCurso);
    }

    private static void listarCursosAlunos() {
        ger.listarAlunosCursos();
    }

    private static void listarAlunosNaoMatriculados() {
        ger.listarAlunosNaoMatriculados();
    }

    private static void excluirAluno() {
        ger.listarAlunos();

        System.out.println("Digite o ID do aluno que deseja excluir: ");
        int id = sc.nextInt();

        AlunoDAO.deletarAluno(id);
    }

    private static void excluirCurso() {
        ger.listarCursos();

        System.out.println("Digite o ID do curso que deseja excluir: ");
        int id = sc.nextInt();

        CursoDAO.deletarCurso(id);
    }
}