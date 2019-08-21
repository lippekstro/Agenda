package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorId);
        alunos.add(aluno);
        atualizaId();
    }

    private void atualizaId() {
        contadorId++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);

        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPorId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos); //devolve uma copia da nossa lista, pra nao perder o encapsulamento
    }

    public void remove(Aluno aluno) {
        Aluno alunoEscolhido = buscaAlunoPorId(aluno);
        if(alunoEscolhido != null) {
            alunos.remove(alunoEscolhido);
        }
    }
}
