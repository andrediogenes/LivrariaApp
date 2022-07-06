package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Usuario;

public class EditarUsuarios extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private ListView listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuarios);

        //Listando os usuários
        listaUsuarios = findViewById(R.id.listaUsuarios);

        List<Usuario> usuarios = dao.listarDadosUsuario();
        List<String> usuariosListView = new ArrayList<>();

        for(Usuario usuarioIterado:usuarios){
            usuariosListView.add(usuarioIterado.getUsuario_nome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,usuariosListView);
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