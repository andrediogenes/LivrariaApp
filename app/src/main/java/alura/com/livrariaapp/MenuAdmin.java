package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alura.com.livrariaapp.DAO.DAO;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }

    public void editarUsuarios(View view) {
        Intent it = new Intent(this, EditarUsuarios.class);
        startActivity(it);
    }

    public void editarLivros(View view) {
        Intent it = new Intent(this, EditarLivros.class);
        startActivity(it);
    }

    public void voltarMenu(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}