package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Katrina on 17/11/2015.
 */
public class DashboardUser extends Activity {
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_user);
        user = (String) getIntent().getSerializableExtra("User");

    }

    public void comprarProduto(View v){
        comprarProdutoActivity();
    }

    private void comprarProdutoActivity(){
        Intent it = new Intent(this,ComprarProdutoActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }

    public void atualizarCadastro(View view){
        atualizarCadastroActivity();
    }

    private void atualizarCadastroActivity(){
        Intent it = new Intent(this, EditarUsuarioActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }
    public void verPedido(View view){
        verPedidoActivity();
    }
    private void verPedidoActivity(){
        Intent it = new Intent(this, VerPedidoActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }
    public void logout(View view){
        finish();
    }
}
