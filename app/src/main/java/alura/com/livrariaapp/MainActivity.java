package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Usuario;

public class MainActivity extends AppCompatActivity {

    //Função que insere um usuário.
    public String insereUsuarioMain(Usuario usuario){


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

        //instanciando usuario ADM para ser cadastrado no sistema
        Usuario usuario = new Usuario();

        usuario.setUsuario_nome("Andre diogenes");
        usuario.setUsuario_CPF("1234567890");
        usuario.setUsuario_nasc("31/01/1996");
        usuario.setUsuario_adm(true);
        usuario.setUsuario_senha("1234");

        //Inserindo um adm do sistema
        String resultado = insereUsuarioMain(usuario);

        //Autenticando o ADM
        autenticaUsuarioMain( "1234567890", "1234");
    }
}