package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        telefoneDAO = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);

        Aluno alunoDevolvido = alunos.get(posicao);
        vincula(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto());
        final TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        new BuscaPrimeiroTelefoneDoAlunoTask(telefoneDAO, telefone, aluno.getId(), telefoneEncontrado -> telefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
