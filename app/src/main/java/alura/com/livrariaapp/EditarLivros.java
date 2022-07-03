package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import alura.com.livrariaapp.DAO.DAO;

public class EditarLivros extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private ListView listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livros);

        listaLivros = findViewById(R.id.listaLivros);
        String [] lista = new String[]{};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,lista);
        listaLivros.setAdapter(adapter);
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