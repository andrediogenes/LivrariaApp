package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import alura.com.livrariaapp.DAO.DAO;

public class EditarUsuarios extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private ListView listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuarios);

        listaUsuarios = findViewById(R.id.listaUsuarios);
        String [] lista = new String[]{"USUARIO_NOME", "USUARIO_CPF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        listaUsuarios.setAdapter(adapter);
    }

    public void cadastrar(View view) {
        Intent it = new Intent(this,Cadastro.class);
        startActivity(it);
    }

    public void voltar (View view){
        Intent it = new Intent(this,MenuAdmin.class);
        startActivity(it);
    }
}