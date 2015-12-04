package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.AdministradorAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioAdministrador;
import brandroid.um.capitulo.projeto.dados.RepositorioUsuarios;
import brandroid.um.capitulo.projeto.modelo.Administrador;
import brandroid.um.capitulo.projeto.modelo.Usuario;

/**
 * Created by Katrina on 04/12/2015.
 */
public class ListarAdministradoresActivity extends Activity {
    private ListView lista;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioAdministrador repositorioAdministrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_admin);
        lista = (ListView) findViewById(R.id.lista_admins);
        registerForContextMenu(this.lista);
        repositorioUsuarios = new RepositorioUsuarios(this);
        repositorioAdministrador = new RepositorioAdministrador(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAdministradores();
    }

    private void carregarAdministradores(){
        List<Administrador> administradorList = repositorioAdministrador.listar();
        AdministradorAdapter adapter = new AdministradorAdapter(this,administradorList);
        this.lista.setAdapter(adapter);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista_admin, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Administrador administrador = (Administrador) this.lista.getItemAtPosition(info.position);
        switch(item.getItemId()){
            case R.id.menu_admin_user:
                rebaixarAdministrador(administrador);
                return true;
            case R.id.menu_admin_excluir:
                excluirAdministrador(administrador);
                return true;
            default:
                return false;
        }
    }

    private void rebaixarAdministrador(Administrador administrador) {
        Usuario usuario = new Usuario(administrador.getUser(),administrador.getSenha(),
                administrador.getNome(),administrador.getEmail(),administrador.getTelefone(),
                administrador.getDatanasc());
        repositorioUsuarios.inserir(usuario);
        repositorioAdministrador.deletar(administrador);
        carregarAdministradores();
    }

    private void excluirAdministrador(final Administrador administrador){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação");
        builder.setMessage(R.string.admin_excluir);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //excluir usuario
                repositorioAdministrador.deletar(administrador);
                carregarAdministradores();
                Toast.makeText(getApplicationContext(),
                        R.string.admin_excluido, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void listarUsers(View view){
        finish();
    }
}
