package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alura.com.livrariaapp.DAO.DAO;
import alura.com.livrariaapp.OBJETOS.Usuario;
import alura.com.livrariaapp.R;

public class Cadastro extends AppCompatActivity {
    DAO dao = new DAO(this);
    private EditText edtNome;
    private EditText edtCPF;
    private EditText edtNascData;
    private EditText edtSenha;
    private Button btnSalvar;
    private Usuario usuario;
    private Usuario altUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtCPF = findViewById(R.id.edtCPF);
        edtNascData = findViewById(R.id.edtNascData);
        edtSenha = findViewById(R.id.edtSenha);
        btnSalvar = findViewById(R.id.btnSalvar);

        Intent it = getIntent();
        altUsuario = (Usuario) it.getSerializableExtra("chave_usuario");
        usuario = new Usuario();

        if(altUsuario != null){
            btnSalvar.setText("ALTERAR");
            edtNome.setText(altUsuario.getUsuario_nome());
            edtNascData.setText(altUsuario.getUsuario_nasc());
            edtSenha.setText(altUsuario.getUsuario_senha());
        }else{
            btnSalvar.setText("SALVAR");
        }
    }

    public void cadastrar (View view){
        String nome = edtNome.getText().toString();
        String cpf = edtCPF.getText().toString();
        String data = edtNascData.getText().toString();
        String senha = edtSenha.getText().toString();

        usuario.setUsuario_nome(nome);
        usuario.setUsuario_CPF(cpf);
        usuario.setUsuario_nasc(data);
        usuario.setUsuario_senha(senha);
        if(btnSalvar.getText().toString().equals("SALVAR")) {
            dao.insereUsuario(usuario);
            Toast toast = Toast.makeText(Cadastro.this,
                    "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            dao.atualizarContato(usuario);
            dao.close();
        }
        limpar();
        finish();
    }
    public void limpar(){
        edtNome.setText("");
        edtCPF.setText("");
        edtNascData.setText("");
        edtSenha.setText("");
    }
    public void cancelar(View view) {
        finish();
    }
}