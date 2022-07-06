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
import alura.com.livrariaapp.OBJETOS.Usuario;

public class MenuUsuario extends AppCompatActivity {
    DAO dao = new DAO(this);
    private ListView listUsuarios, listLivros;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        //Listando os livros
        listLivros = findViewById(R.id.list1);

        List<Livro> livros = dao.listarDadosLivros();
        List<String> livrosListView = new ArrayList<>();

        for(Livro livroIterado:livros){
            livrosListView.add(livroIterado.getLivro_nome());
        }
        ArrayAdapter<String> adapterLivro = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,livrosListView);
        listLivros.setAdapter(adapterLivro);

        //Listando os usuários
        listUsuarios = findViewById(R.id.listinhalindinha);

        List<Usuario> usuarios = dao.listarDadosUsuario();
        List<String> usuariosListView = new ArrayList<>();

        for(Usuario usuarioIterado:usuarios){
            usuariosListView.add(usuarioIterado.getUsuario_nome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,usuariosListView);
        listUsuarios.setAdapter(adapter);

    }
    public void voltarLogin (View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}