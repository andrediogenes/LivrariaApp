package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import alura.com.livrariaapp.DAO.DAO;

public class MenuUsuario extends AppCompatActivity {
    DAO dao = new DAO(this);
    private ListView list;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        list = findViewById(R.id.list1);
        String [] lista = new String[]{"LIVRO_NOME"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        list.setAdapter(adapter);
    }
    public void voltarLogin (View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}