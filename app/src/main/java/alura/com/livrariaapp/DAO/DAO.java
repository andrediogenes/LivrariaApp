package alura.com.livrariaapp.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import alura.com.livrariaapp.OBJETOS.Livro;
import alura.com.livrariaapp.OBJETOS.Usuario;
import alura.com.livrariaapp.OBJETOS.Venda;

public class DAO extends SQLiteOpenHelper {
    public DAO(Context context) {
        super(context, "USUARIO", null, 12);
    }

    //Criacao das tabelas no banco
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_usuario = "CREATE TABLE USUARIO (USUARIO_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USUARIO_NOME TEXT," +
                "USUARIO_CPF TEXT UNIQUE," +
                "USUARIO_DATANASC DATE," +
                "USUARIO_EHADM INTEGER," +
                "USUARIO_SENHA TEXT NOT NULL);";
        String sql_livro = "CREATE TABLE LIVRO (LIVRO_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "LIVRO_IDUSUARIOCADASTRO INTEGER," +
                "LIVRO_CODBARRAS TEXT UNIQUE," +
                "LIVRO_NOME TEXT," +
                "LIVRO_GENERO TEXT," +
                "LIVRO_AUTOR TEXT," +
                "LIVRO_PRECO TEXT);";
        String sql_venda = "CREATE TABLE VENDA (VENDA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "VENDA_IDUSUARIOVENDA TEXT," +
                "VENDA_IDLIVROVENDA INTEGER UNIQUE," +
                "VENDA_DATACOMPRA DATE," +
                "VENDA_FORMAPAGAMENTO TEXT);";

        db.execSQL(sql_usuario);
        db.execSQL(sql_livro);
        db.execSQL(sql_venda);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        String sql_usuario = "DROP TABLE IF EXISTS USUARIO;";
        String sql_livro = "DROP TABLE IF EXISTS LIVRO;";
        String sql_venda = "DROP TABLE IF EXISTS VENDA;";

        db.execSQL(sql_usuario);
        db.execSQL(sql_livro);
        db.execSQL(sql_venda);

        onCreate(db);
    }

    //Insere um usuário no banco
    //Retorna uma mensagem positiva caso sucesso
    //Retorna uma mensagem negativa caso falha
    public String insereUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        //Dados a serem gravados no banco
        try {
            ContentValues dados_usuario = new ContentValues();
            dados_usuario.put("USUARIO_NOME", usuario.getUsuario_nome());
            dados_usuario.put("USUARIO_CPF", usuario.getUsuario_CPF());
            dados_usuario.put("USUARIO_DATANASC", usuario.getUsuario_nasc());
            dados_usuario.put("USUARIO_EHADM", usuario.isUsuario_adm());
            dados_usuario.put("USUARIO_SENHA", usuario.getUsuario_senha());

            db.insertOrThrow("USUARIO", null,dados_usuario);
            db.close();
        } catch (SQLiteConstraintException erro) {
            return "Usuário já cadastrado com esse CPF";
        }
        return "Sucesso ao cadastrar usuário";
    }

    //Autentica um usuário no banco pelo CPF e senha
    //Mensagem positiva caso positivo e negativa caso negativo
    @SuppressLint("Range")
    public String autenticaUsuario(String CPF, String senha){
        SQLiteDatabase db = getWritableDatabase();
        String sqli_busca_usuario = "SELECT * FROM USUARIO WHERE USUARIO_CPF = " +
                "'" +
                CPF +
                "'";
        Cursor c = db.rawQuery(sqli_busca_usuario, null);

        while (c.moveToNext()){
            if (CPF.equals(c.getString(c.getColumnIndex("USUARIO_CPF")))){
                if(senha.equals(c.getString(c.getColumnIndex("USUARIO_SENHA")))){
                    db.close();
                    c.close();
                    return "login efetuado com sucesso";
                }
            }
        }
        db.close();
        c.close();
        return "login falhou, usuário ou senha inválidos";
    }

    @SuppressLint("Range")
    public String autenticaAdm(String CPF, String senha){
        SQLiteDatabase db = getWritableDatabase();
        String sqli_busca_usuario = "SELECT * FROM USUARIO WHERE USUARIO_CPF = " +
                "'" +
                CPF +
                "'";
        Cursor c = db.rawQuery(sqli_busca_usuario, null);

        while (c.moveToNext()){
            if (CPF.equals(c.getString(c.getColumnIndex("USUARIO_CPF")))){
                if(senha.equals(c.getString(c.getColumnIndex("USUARIO_SENHA")))){
                    if (c.getInt(c.getColumnIndex("USUARIO_EHADM")) == 1) {
                        db.close();
                        c.close();
                        return "login efetuado com sucesso";
                    }
                    else
                        return "Usuário não é ADM";
                }
            }
        }
        db.close();
        c.close();
        return "login falhou, usuário ou senha inválidos";
    }

    //
    public String deletaUsuario(String CPF){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sqli_deleta_usuario = "DELETE FROM USUARIO WHERE USUARIO_CPF = " +
                    "'" +
                    CPF +
                    "'";

            db.execSQL(sqli_deleta_usuario);
            db.close();
        } catch (SQLiteConstraintException erro){
            return "Erro de delete";
        }
        return "Usuario deletado com sucesso";
    }

    //Pesquisa um usuario pelo CPF e o retorna pelo ID
    public Integer retornaIDUsuario(String CPF){
        SQLiteDatabase db = getWritableDatabase();
        String sqli_busca_usuario = "SELECT * FROM USUARIO WHERE USUARIO_CPF = " +
                "'" +
                CPF +
                "'";
        Cursor c = db.rawQuery(sqli_busca_usuario, null);
        Integer id = c.getColumnIndex("USUARIO_ID");
        db.close();
        c.close();
        return id;
    }

    public String insereLivro(Livro livro, Integer idUsuarioCadastro){
        SQLiteDatabase db = getWritableDatabase();

        //Dados a serem gravados no banco
        try {
            ContentValues dados_livro = new ContentValues();
            dados_livro.put("LIVRO_IDUSUARIOCADASTRO", idUsuarioCadastro);
            dados_livro.put("LIVRO_CODBARRAS", livro.getLivro_cod_barras());
            dados_livro.put("LIVRO_NOME", livro.getLivro_nome());
            dados_livro.put("LIVRO_GENERO", livro.getLivro_genero());
            dados_livro.put("LIVRO_AUTOR", livro.getLivro_autor());
            dados_livro.put("LIVRO_PRECO", livro.getLivro_preco());

            db.insertOrThrow("LIVRO", null,dados_livro);
            db.close();
        } catch (SQLiteConstraintException erro) {
            return "Erro de insercao, livro já cadastrado";
        }
        return "Livro cadastrado com sucesso";
    }

    //Dado um id do livro, o deleta
    public String deletaLivro(String codBarras){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sqli_deleta_livro = "DELETE FROM LIVRO WHERE LIVRO_CODBARRAS = " +
                    "'" +
                    codBarras +
                    "'";

            db.execSQL(sqli_deleta_livro);
            db.close();
        } catch (SQLiteConstraintException erro){
            return "Erro de delete";
        }
        return "Livro deletado com sucesso";
    }

    //Pesquisa um livro pelo codBarras e o retorna pelo ID
    public Integer retornaIDLivro(String codBarras){
        SQLiteDatabase db = getWritableDatabase();
        String sqli_busca_livro = "SELECT * FROM LIVRO WHERE LIVRO_CODBARRAS = " +
                "'" +
                codBarras +
                "'";
        Cursor c = db.rawQuery(sqli_busca_livro, null);
        Integer id = c.getColumnIndex("LIVRO_ID");
        db.close();
        c.close();
        return id;
    }

    //Funcao de cadastro das vendas no banco
    public String insereVenda(Venda venda, Integer idUsuarioVenda, Integer idLivroVenda){
        SQLiteDatabase db = getWritableDatabase();

        //Dados a serem gravados no banco
        try {
            ContentValues dados_venda = new ContentValues();
            dados_venda.put("VENDA_IDUSUARIOVENDA", idUsuarioVenda);
            dados_venda.put("VENDA_IDLIVROVENDA", idLivroVenda);
            dados_venda.put("VENDA_DATACOMPRA", venda.getVenda_data());
            dados_venda.put("VENDA_FORMAPAGAMENTO", venda.getVenda_forma_pagamento());

            db.insertOrThrow("VENDA", null,dados_venda);
            db.close();
        } catch (SQLiteConstraintException erro) {
            return "ERRO! Esse livro ja está vendido";
        }
        return "Venda cadastrada com sucesso";
    }

    //Funcao de deletar um registro de venda no banco
    public String deletaVenda(Integer idLivroVenda){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sqli_deleta_venda = "DELETE FROM VENDA WHERE VENDA_IDLIVROVENDA = " +
                    "'" +
                    idLivroVenda +
                    "'";

            db.execSQL(sqli_deleta_venda);
            db.close();
        } catch (SQLiteConstraintException erro){
            return "Erro de delete";
        }
        return "Venda deletado com sucesso";
    }

    //Funcao de retornar o ID da venda
    public Integer retornaIDVenda(String idLivroVenda){
        SQLiteDatabase db = getWritableDatabase();
        String sqli_busca_livro = "SELECT * FROM LIVRO WHERE LIVRO_CODBARRAS = " +
                "'" +
                idLivroVenda +
                "'";
        Cursor c = db.rawQuery(sqli_busca_livro, null);
        Integer id = c.getColumnIndex("VENDA_ID");
        db.close();
        c.close();
        return id;
    }

    public ArrayList<Usuario> listarDadosUsuario() {
        ArrayList<Usuario> linhas = new ArrayList<>();
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM USUARIO", null);

            while(c.moveToNext()){
                Usuario usuario = new Usuario();
                usuario.setUsuario_adm(c.getInt(c.getColumnIndexOrThrow("USUARIO_EHADM")));
                usuario.setUsuario_id(c.getInt(c.getColumnIndexOrThrow("USUARIO_ID")));
                usuario.setUsuario_nome(c.getString(c.getColumnIndexOrThrow("USUARIO_NOME")));
                usuario.setUsuario_CPF(c.getString(c.getColumnIndexOrThrow("USUARIO_CPF")));
                usuario.setUsuario_senha(c.getString(c.getColumnIndexOrThrow("USUARIO_SENHA")));
                usuario.setUsuario_nasc(c.getString(c.getColumnIndexOrThrow("USUARIO_DATANASC")));

                linhas.add(usuario);
            }
            db.close();
            c.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return linhas;
    }

    public ArrayList<Livro> listarDadosLivros() {
        ArrayList<Livro> linhas = new ArrayList<>();
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM LIVRO", null);

            while(c.moveToNext()){
                Livro livro = new Livro();
                livro.setLivro_id(c.getInt(c.getColumnIndexOrThrow("LIVRO_ID")));
                livro.setLivro_cod_barras(c.getString(c.getColumnIndexOrThrow("LIVRO_CODBARRAS")));
                livro.setLivro_nome(c.getString(c.getColumnIndexOrThrow("LIVRO_NOME")));
                livro.setLivro_genero(c.getString(c.getColumnIndexOrThrow("LIVRO_GENERO")));
                livro.setLivro_preco(c.getString(c.getColumnIndexOrThrow("LIVRO_PRECO")));
                livro.setLivro_autor(c.getString(c.getColumnIndexOrThrow("LIVRO_AUTOR")));
                livro.setUsuario_cadastro_id(c.getInt(c.getColumnIndexOrThrow("LIVRO_IDUSUARIOCADASTRO")));

                linhas.add(livro);
            }
            db.close();
            c.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return linhas;
    }

    public ArrayList<Venda> listarDadosVendas() {
        ArrayList<Venda> linhas = new ArrayList<>();
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM VENDA", null);

            while(c.moveToNext()){
                Venda venda = new Venda();
                venda.setVenda_id(c.getInt(c.getColumnIndexOrThrow("VENDA_ID")));
                venda.setVenda_id_usuario(c.getInt(c.getColumnIndexOrThrow("VENDA_IDUSUARIOVENDA")));
                venda.setVenda_id_livro(c.getInt(c.getColumnIndexOrThrow("VENDA_IDLIVROVENDA")));
                venda.setVenda_data(c.getString(c.getColumnIndexOrThrow("VENDA_DATACOMPRA")));
                venda.setVenda_forma_pagamento(c.getString(c.getColumnIndexOrThrow("VENDA_FORMAPAGAMENTO")));

                linhas.add(venda);
            }
            db.close();
            c.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return linhas;
    }

    //Funcao que atualiza um usuario na tabela
    public long atualizarContato(Usuario usuario){
        long retornoBD;
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("USUARIO_NOME",usuario.getUsuario_nome());
        values.put("USUARIO_CPF",usuario.getUsuario_CPF());
        values.put("USUARIO_DATANASC", usuario.getUsuario_nasc());
        values.put("USUARIO_SENHA",usuario.getUsuario_senha());
        String[] args = {String.valueOf(usuario.getUsuario_id())};
        retornoBD=db.update("USUARIO",values,"id=?",args);
        db.close();
        return retornoBD;
    }

    //Funcao que atualiza um livro na tabela
    public long atualizarLivro(Livro livro){
        long retornoBD;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LIVRO_NOME",livro.getLivro_nome());
        values.put("LIVRO_GENERO",livro.getLivro_genero());
        values.put("LIVRO_AUTOR",livro.getLivro_autor());
        values.put("LIVRO_PRECO",livro.getLivro_preco());
        values.put("LIVRO_CODBARRAS",livro.getLivro_cod_barras());
        String[] args = {String.valueOf(livro.getLivro_id())};
        retornoBD=db.update("LIVRO",values,"id=?",args);
        db.close();
        return retornoBD;
    }

    //Funcao que atualiza uma venda na tabela
    public long atualizarLivro(Venda venda){
        long retornoBD;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VENDA_DATACOMPRA",venda.getVenda_data());
        values.put("VENDA_FORMAPAGAMENTO", venda.getVenda_forma_pagamento());
        String[] args = {String.valueOf(venda.getVenda_id())};
        retornoBD=db.update("LIVRO",values,"id=?",args);
        db.close();
        return retornoBD;
    }
}
