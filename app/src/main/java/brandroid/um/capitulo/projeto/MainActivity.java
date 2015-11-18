package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Katrina on 17/11/2015.
 */
public class MainActivity extends Activity {
    private EditText textUser,textSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textUser = (EditText) findViewById(R.id.edit_user);
        textSenha = (EditText) findViewById(R.id.edit_senha);
    }

    public void login(View view){
        activityLogar();
    }

    public void criarUsuario(View view){
        activityCadastro();
    }

    public void activityCadastro(){
        Intent it = new Intent(this,CadastroUsuarioActivity.class);
        startActivity(it);
    }

    public void activityLogar(){
        String usuario = textUser.getText().toString();
        String senha = textSenha.getText().toString();


        Intent it = new Intent(this, DashboardAdmin.class);
    }
}
