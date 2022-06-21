package alura.com.livrariaapp.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import alura.com.livrariaapp.OBJETOS.Livro;
import alura.com.livrariaapp.OBJETOS.Usuario;

public class DAO extends SQLiteOpenHelper {
    public DAO(Context context) {
        super(context, "USUARIO", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_usuario = "CREATE TABLE USUARIO (USUARIO_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USUARIO_NOME TEXT," +
                "USUARIO_CPF TEXT UNIQUE," +
                "USUARIO_DATANASC DATE," +
                "USUARIO_EHADM BOOLEAN," +
                "USUARIO_SENHA TEXT NOT NULL);";
        String sql_livro = "CREATE TABLE LIVRO (LIVRO_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "LIVRO_IDUSUARIOCADASTRO INTEGER," +
                "LIVRO_NOME TEXT," +
                "LIVRO_GENERO TEXT," +
                "LIVRO_AUTOR TEXT," +
                "LIVRO_PRECO FLOAT);";
        String sql_venda = "CREATE TABLE VENDA (VENDA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "VENDA_IDUSUARIOVENDA TEXT," +
                "VENDA_IDLIVROVENDA INTEGER," +
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

    public void insereLivro(Livro livro){

    }

}
