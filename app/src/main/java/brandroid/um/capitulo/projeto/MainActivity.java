package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import brandroid.um.capitulo.projeto.dados.RepositorioAdministrador;
import brandroid.um.capitulo.projeto.dados.RepositorioUsuarios;
import brandroid.um.capitulo.projeto.modelo.Administrador;
import brandroid.um.capitulo.projeto.modelo.Pessoa;
import brandroid.um.capitulo.projeto.modelo.Usuario;

public class MainActivity extends Activity {
    private EditText textUser,textSenha;
    private Pessoa pessoa;
    private List<Usuario> usuariosCadastrados;
    private List<Administrador> administradoresCadastrados;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioAdministrador repositorioAdministrador;
    private boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textUser = (EditText) findViewById(R.id.edit_user);
        textSenha = (EditText) findViewById(R.id.edit_senha);
        repositorioUsuarios = new RepositorioUsuarios(this);
        repositorioAdministrador = new RepositorioAdministrador(this);

        usuariosCadastrados = new ArrayList<>();
        administradoresCadastrados = new ArrayList<>();
    }
    //Carregar dados do Banco de dados
    @Override
    protected void onResume() {
        super.onResume();
        carregarUsuarios();
        carregarAdministradores();
        login = false;
    }
    public void carregarUsuarios(){
        usuariosCadastrados = repositorioUsuarios.listar();

    }
    public void carregarAdministradores(){
        administradoresCadastrados = repositorioAdministrador.listar();
    }
    //Métodos para tela Dashboard
    public void login(View view){
        activityLogar();
    }

    public void activityLogar(){
        String usuario = textUser.getText().toString();
        String senha = textSenha.getText().toString();
        pessoa = new Pessoa(usuario, senha);


        //Identificação de tipo de Usuário
        for(Usuario u : usuariosCadastrados){

            if(pessoa.getUser().equalsIgnoreCase(u.getUser()) &&
                    pessoa.getSenha().equals(u.getSenha())){
                Intent it = new Intent(this,DashboardUser.class);
                startActivity(it);
                Toast toast = Toast.makeText(this,R.string.login_sucesso,Toast.LENGTH_SHORT);
                toast.show();
                login = true;
            }
        }
        for(Administrador a : administradoresCadastrados){
            if(pessoa.getUser().equalsIgnoreCase(a.getUser()) &&
                    pessoa.getSenha().equals(a.getSenha())){
                Intent it = new Intent(this, DashboardAdmin.class);
                startActivity(it);
                Toast toast = Toast.makeText(this,R.string.admin_login_sucesso,Toast.LENGTH_SHORT);
                toast.show();
                login = true;
            }
        }
        if(login == false){
            Toast toast = Toast.makeText(this,R.string.login_erro,Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Métodos para tela de Cadastro de Usuário
    public void criarUsuario(View view){
        activityCadastro();
    }

    public void activityCadastro(){
        Intent it = new Intent(this,CadastroUsuarioActivity.class);
        startActivity(it);
    }
}
