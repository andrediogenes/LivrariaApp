package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Usuario;

public class MainActivity extends AppCompatActivity {

    public String insereUsuarioMain(String nome , String CPF, String datanasc, boolean ehadm, String senha){
        Usuario usuario = new Usuario();

        usuario.setUsuario_nome(nome);
        usuario.setUsuario_CPF(CPF);
        usuario.setUsuario_nasc(datanasc);
        usuario.setUsuario_adm(ehadm);
        usuario.setUsuario_senha(senha);

        DAO dao = new DAO(this);

        String resultado = dao.insereUsuario(usuario);
        Log.d("Resultado da insercao: ", resultado);
        return resultado;
    }

    public void autenticaUsuarioMain(String CPF, String senha){

        DAO dao = new DAO(this);

        String resultado = dao.autenticaUsuario(CPF, senha);
        Log.d("Resultado: ", resultado);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inserindo um adm do sistema
        String resultado = insereUsuarioMain("Andre diogenes", "1234567890", "31/01/1996", true, "1234");

        autenticaUsuarioMain( "1234567890", "1234");

    }
}