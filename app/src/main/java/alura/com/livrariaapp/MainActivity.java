package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Livro;
import alura.com.livrariaapp.OBJETOS.Usuario;
import alura.com.livrariaapp.OBJETOS.Venda;

public class MainActivity extends AppCompatActivity {

    DAO dao = new DAO(this);

    //Função que insere um usuário.
    public String insereUsuarioMain(Usuario usuario){
        return dao.insereUsuario(usuario);
    }

    //Funcao que autentica o usuario
    public String autenticaUsuarioMain(String CPF, String senha){
        return dao.autenticaUsuario(CPF, senha);
    }

    //Funcao que cadastra o livro
    public String cadastraLivroMain(Livro livro, String CPF){
        Integer id = dao.retornaIDUsuario(CPF);

        return dao.insereLivro(livro, id);
    }

    //Funcao que registra a venda
    public String registraVendaMain(Venda venda, String CPF, String codBarras){
        Integer idUsuario = dao.retornaIDUsuario(CPF);
        Integer idLivro = dao.retornaIDLivro(codBarras);

        return dao.insereVenda(venda, idUsuario, idLivro);
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
        Log.d("Resultado da insercao: ", insereUsuarioMain(usuario));

        //Autenticando o ADM
        Log.d("Resultado autenticacao:", autenticaUsuarioMain( "1234567890", "1234"));

        Livro livro = new Livro();
        livro.setLivro_autor("JK Rowling");
        livro.setLivro_cod_barras("123456");
        livro.setLivro_genero("Fantasia");
        livro.setLivro_nome("Harry Potter");
        livro.setLivro_preco("50,00");

        //cadastrando o livro
        Log.d("Resultado livro: ", cadastraLivroMain(livro, "1234567890"));

        Venda venda = new Venda();
        venda.setVenda_data("22/06/2022");
        venda.setVenda_forma_pagamento("Dinheiro");

        Log.d("Resultado venda: ", registraVendaMain(venda, "1234567890", "123456"));

    }
}