package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardAdmin extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_admin);
    }

    public void cadastrarProduto(View view){
        cadastrarProdutoActivity();
    }
    public void cadastrarProdutoActivity(){
        Intent it = new Intent(this,ListarProdutoActivity.class);
        startActivity(it);
    }
    public void gerenciarUsuarios(View view){
        gerenciarUsuariosActivity();
    }
    private void gerenciarUsuariosActivity(){
        Intent it = new Intent(this, ListarUsuarioActivity.class);
        startActivity(it);
    }
    public void fecharCaixa(View view){

    }
    public void logout(View view){
        finish();
    }
}
