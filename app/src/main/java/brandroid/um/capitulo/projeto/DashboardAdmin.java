package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DashboardAdmin extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_admin);
    }

    public void cadastrarProduto(View view){

    }

    public void gerenciarUsuarios(View view){

    }

    public void fecharCaixa(View view){

    }
    public void logout(View view){
        finish();
    }
}
