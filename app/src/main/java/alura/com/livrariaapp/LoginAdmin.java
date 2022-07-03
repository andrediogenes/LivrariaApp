package alura.com.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import alura.com.livrariaapp.DAO.DAO;

public class LoginAdmin extends AppCompatActivity {
    private DAO dao = new DAO(this);
    private EditText edtLoginCPF;
    private EditText edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        edtLoginCPF=findViewById(R.id.edtLoginCPF);
        edtSenha=findViewById(R.id.edtSenha);
    }
    public void conectar(View view) {
        String usr=edtLoginCPF.getText().toString();
        String senha = edtSenha.getText().toString();
        String password=dao.autenticaUsuario(usr, senha);
        if(senha.equals(password)){
            Intent intent= new Intent(this, MenuAdmin.class);
            intent.putExtra("chave_usuario",usr);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(LoginAdmin.this,
                    "Usuário ou senha inválido",Toast.LENGTH_LONG);
            toast.show();
        }
        limpar();
        finish();
    }
    public void limpar(){
        edtLoginCPF.setText("");
        edtSenha.setText("");
    }
    public void cancelar(View view) {
        finish();
    }
}