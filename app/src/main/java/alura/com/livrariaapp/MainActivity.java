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

    //Funcao que deleta o usuario
    public String deletaUsuarioMain(String CPF){
        return dao.deletaUsuario(CPF);
    }

    //Funcao que cadastra o livro
    public String cadastraLivroMain(Livro livro, String CPF){
        Integer id = dao.retornaIDUsuario(CPF);

        return dao.insereLivro(livro, id);
    }

    //Funcao que deleta um livro
    public String deletaLivroMain(String codBarras){
        return dao.deletaLivro(codBarras);
    }

    //Funcao que registra a venda
    public String registraVendaMain(Venda venda, String CPF, String codBarras){
        Integer idUsuario = dao.retornaIDUsuario(CPF);
        Integer idLivro = dao.retornaIDLivro(codBarras);

        return dao.insereVenda(venda, idUsuario, idLivro);
    }

    public String deletaVendaMain(String codBarras){
        Integer idLivro = dao.retornaIDLivro(codBarras);
        return dao.deletaVenda(idLivro);
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
        usuario.setUsuario_adm(1);
        usuario.setUsuario_senha("1234");

        //Inserindo um adm do sistema
        Log.d("Resultado usuario: ", insereUsuarioMain(usuario));
        //Autenticando o ADM
        Log.d("Resultado autenticacao:", autenticaUsuarioMain( "1234567890", "1234"));
        //Inserindo o mesmo usuário no sistema para dar erro
        Log.d("Resultado usuario: ", insereUsuarioMain(usuario));
        //Deletando o usuario no sistema
        Log.d("Delete Usuario:", deletaUsuarioMain("1234567890"));
        //Inserindo o mesmo usuário no sistema para ver se o delete foi feito correto
        Log.d("Resultado usuario: ", insereUsuarioMain(usuario));

        Livro livro = new Livro();
        livro.setLivro_autor("JK Rowling");
        livro.setLivro_cod_barras("123456");
        livro.setLivro_genero("Fantasia");
        livro.setLivro_nome("Harry Potter");
        livro.setLivro_preco("50,00");

        //cadastrando o livro
        Log.d("Resultado livro: ", cadastraLivroMain(livro, "1234567890"));
        //Tentando cadastrar o livro novamente para exibir uma mensagem de erro
        Log.d("Resultado livro: ", cadastraLivroMain(livro, "1234567890"));
        //Deletando o livro
        Log.d("Resultado delete:", deletaLivroMain("123456"));
        //cadastrando o livro novamente para verificar se o delete foi correto
        Log.d("Resultado livro: ", cadastraLivroMain(livro, "1234567890"));

        Venda venda = new Venda();
        venda.setVenda_data("22/06/2022");
        venda.setVenda_forma_pagamento("Dinheiro");

        //Registrando uma venda
        Log.d("Resultado venda: ", registraVendaMain(venda, "1234567890", "123456"));
        //Tentando registrar uma venda para exibir o erro
        Log.d("Resultado venda: ", registraVendaMain(venda, "1234567890", "123456"));
        //deletando venda
        Log.d("Resultado delete:", deletaVendaMain("123456"));
        //registrando venda novamente para verificar se o delete foi correto
        Log.d("Resultado venda: ", registraVendaMain(venda, "1234567890", "123456"));


        //-------------------------------------------------------
        //Agora partindo para as funções de exibição do banco de dados
        Usuario usuario1 = new Usuario();
        usuario1.setUsuario_nome("Gabriel");
        usuario1.setUsuario_CPF("12345678");
        usuario1.setUsuario_nasc("31/01/1996");
        usuario1.setUsuario_adm(0);
        usuario1.setUsuario_senha("1234");
        Usuario usuario2 = new Usuario();
        usuario2.setUsuario_nome("Pedro");
        usuario2.setUsuario_CPF("1111");
        usuario2.setUsuario_nasc("31/01/1991");
        usuario2.setUsuario_adm(0);
        usuario2.setUsuario_senha("1234");

        //Livros
        Livro livro1 = new Livro();
        livro1.setLivro_autor("CS Lewis");
        livro1.setLivro_cod_barras("1111");
        livro1.setLivro_genero("Fantasia");
        livro1.setLivro_nome("Nárnia");
        livro1.setLivro_preco("100,00");
        Livro livro2 = new Livro();
        livro2.setLivro_autor("George Orwell");
        livro2.setLivro_cod_barras("1112");
        livro2.setLivro_genero("Fantasia");
        livro2.setLivro_nome("A revolução dos bichos");
        livro2.setLivro_preco("75,00");

        //Venda
        Venda venda1 = new Venda();
        venda1.setVenda_data("22/05/2022");
        venda1.setVenda_forma_pagamento("Cartão");

        //Registrando uma venda
        Log.d("Resultado venda: ", registraVendaMain(venda, "1234567890", "1111"));

        //-------------------------------------------------------------------------
        //Exibindo as informações

    }
}