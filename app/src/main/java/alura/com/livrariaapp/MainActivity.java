package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Livro;
import alura.com.livrariaapp.OBJETOS.Usuario;
import alura.com.livrariaapp.OBJETOS.Venda;

public class MainActivity extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private EditText edtLoginCPF;
    private EditText edtSenha;

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

    public ArrayList<Usuario> listaUsuarios(){
        return dao.listarDadosUsuario();
    }

    public ArrayList<Livro> listaLivros(){
        return dao.listarDadosLivros();
    }

    public ArrayList<Venda> listaVendas(){
        return dao.listarDadosVendas();
    }

    public void elementosListaUsuario(ArrayList<Usuario> lista){
        //Testando e mostrando os elementos da lista de Usuarios
        int i;
        int n = lista.size();
        for (i=0; i<n; i++) {
            System.out.printf("Dado Numero: %d- Nome: %s\n", i, lista.get(i).getUsuario_nome());
        }
    }

    public void elementosListaLivro(ArrayList<Livro> lista){
        //Testando e mostrando os elementos da lista de livros
        int i;
        int n = lista.size();
        for (i=0; i<n; i++) {
            System.out.printf("Dado Numero: %d- Nome: %s\n", i, lista.get(i).getLivro_nome());
        }
    }

    public void elementosListaVenda(ArrayList<Venda> lista){
        //Testando e mostrando os elementos da lista de vendas
        int i;
        int n = lista.size();
        for (i=0; i<n; i++) {
            System.out.printf("Dado Numero: %d- Nome: %s\n", i, lista.get(i).getVenda_forma_pagamento());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Testes feitos:
        //instanciando usuario ADM para ser cadastrado no sistema
        Usuario usuario = new Usuario();
        usuario.setUsuario_nome("Andre diogenes");
        usuario.setUsuario_CPF("1");
        usuario.setUsuario_nasc("31/01/1996");
        usuario.setUsuario_adm(1);
        usuario.setUsuario_senha("1");

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
        Log.d("Resultado usuario: ", insereUsuarioMain(usuario1));
        Log.d("Resultado usuario: ", insereUsuarioMain(usuario2));

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
        Log.d("Resultado livro: ", cadastraLivroMain(livro1, "1234567890"));
        Log.d("Resultado livro: ", cadastraLivroMain(livro2, "1234567890"));

        //Venda
        Venda venda1 = new Venda();
        venda1.setVenda_data("22/05/2022");
        venda1.setVenda_forma_pagamento("Cartão");

        Venda venda2 = new Venda();
        venda2.setVenda_data("22/01/2022");
        venda2.setVenda_forma_pagamento("Débito");

        //Registrando uma venda
        Log.d("Resultado venda: ", registraVendaMain(venda2, "1234567890", "1111"));
        Log.d("Resultado venda: ", registraVendaMain(venda2, "1234567890", "1112"));

        //-------------------------------------------------------------------------
        //Exibindo as informações
        elementosListaUsuario(listaUsuarios());

        elementosListaLivro(listaLivros());

        elementosListaVenda(listaVendas());

        //-------------------------------------------------------------------------
        //Fim da parte de testes do Andre
        //-------------------------------------------------------------------------
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtLoginCPF=findViewById(R.id.edtLoginCPF);
        edtSenha=findViewById(R.id.edtSenha);
    }
    public void conectar(View view) {
        String usr=edtLoginCPF.getText().toString();
        String senha = edtSenha.getText().toString();
        String autenticacao=dao.autenticaUsuario(usr, senha);
        if(autenticacao.equals("login efetuado com sucesso")){
            Intent intent= new Intent(this, MenuUsuario.class);
            intent.putExtra("chave_usuario",usr);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(MainActivity.this,
                    "Usuário ou senha inválido",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void cadastrar(View view) {
        Intent it = new Intent(this, Cadastro.class);
        startActivity(it);
    }
    public void LoginAdm (View view){
        Intent it = new Intent(this, LoginAdmin.class);
        startActivity(it);
    }


}