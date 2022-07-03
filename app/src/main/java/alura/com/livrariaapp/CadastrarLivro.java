package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Livro;

public class CadastrarLivro extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private EditText edtNomeLivro;
    private EditText edtGenero;
    private EditText edtAutor;
    private EditText edtPreco;
    private EditText edtCodBarras;
    private EditText edtIdUsarioCadastro;
    private Button btnCadastrarLivro;
    private Livro livro;
    private Livro altLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);

        edtNomeLivro = findViewById(R.id.edtNomeLivro);
        edtGenero = findViewById(R.id.edtGenero);
        edtAutor = findViewById(R.id.edtAutor);
        edtPreco = findViewById(R.id.edtPreco);
        edtCodBarras = findViewById(R.id.edtCodBarras);
        edtIdUsarioCadastro = findViewById(R.id.edtIdUsuarioCadastro);

        if(altLivro != null){
            btnCadastrarLivro.setText("ALTERAR");
            edtNomeLivro.setText(altLivro.getLivro_nome());
            edtGenero.setText(altLivro.getLivro_genero());
            edtAutor.setText(altLivro.getLivro_autor());
            edtPreco.setText(altLivro.getLivro_preco());
            edtCodBarras.setText(altLivro.getLivro_cod_barras());
            edtIdUsarioCadastro.setText(altLivro.getUsuario_cadastro_id());
        }else{
            btnCadastrarLivro.setText("SALVAR");
        }
    }
    public void cadastrarLivro (View view){
    }
    public void limpar(){
        edtNomeLivro.setText("");
        edtGenero.setText("");
        edtAutor.setText("");
        edtPreco.setText("");
        edtCodBarras.setText("");
    }
    public void cancelarCadastro(View view) {
        finish();
    }

    public void voltarEdtLivros (View view){
        Intent it = new Intent(this, EditarLivros.class);
        startActivity(it);
    }
}