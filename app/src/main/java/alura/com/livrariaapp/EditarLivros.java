package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Livro;

public class EditarLivros extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private ListView listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livros);

        //Listando os livros
        listaLivros = findViewById(R.id.listaLivros);

        List<Livro> livros = dao.listarDadosLivros();
        List<String> livrosListView = new ArrayList<>();

        for(Livro livroIterado:livros){
            livrosListView.add(livroIterado.getLivro_nome());
        }
        ArrayAdapter<String> adapterLivro = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,livrosListView);
        listaLivros.setAdapter(adapterLivro);
    }
    public void voltarMenuAdmin (View view){
        Intent it = new Intent(this, MenuAdmin.class);
        startActivity(it);
    }
    public void inserirLivro (View view){
        Intent it = new Intent(this,CadastrarLivro.class);
        startActivity(it);
    }
}