package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.UsuarioAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioAdministrador;
import brandroid.um.capitulo.projeto.dados.RepositorioUsuarios;
import brandroid.um.capitulo.projeto.modelo.Administrador;
import brandroid.um.capitulo.projeto.modelo.Produto;
import brandroid.um.capitulo.projeto.modelo.Usuario;

/**
 * Created by Katrina on 03/12/2015.
 */
public class ListarUsuarioActivity extends Activity {
    private ListView lista;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioAdministrador repositorioAdministrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_user);
        lista = (ListView) findViewById(R.id.lista_users);
        registerForContextMenu(this.lista);
        repositorioUsuarios = new RepositorioUsuarios(this);
        repositorioAdministrador = new RepositorioAdministrador(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarUsuarios();
    }

    private void carregarUsuarios(){
        List<Usuario> usuarioList = repositorioUsuarios.listar();
        UsuarioAdapter adapter = new UsuarioAdapter(this,usuarioList);
        this.lista.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista_user, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Usuario usuario = (Usuario) this.lista.getItemAtPosition(info.position);
        switch(item.getItemId()){
            case R.id.menu_user_dial:
                ligarUsuario(usuario.getTelefone());
                return true;
            case R.id.menu_user_admin:
                promoverUsuario(usuario);
                return true;
            case R.id.menu_user_excluir:
                excluirUsuario(usuario);
                return true;
            default:
                return false;
        }
    }

    private void excluirUsuario(final Usuario usuario){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação");
        builder.setMessage(R.string.excluir_user);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //excluir usuario
                repositorioUsuarios.deletar(usuario);
                carregarUsuarios();
                Toast.makeText(getApplicationContext(),
                        R.string.user_excluido, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void promoverUsuario(Usuario usuario){
        Administrador admin = new Administrador(usuario.getUser(),usuario.getSenha(),
                usuario.getNome(),usuario.getEmail(),usuario.getTelefone(),usuario.getDatanasc());

        repositorioAdministrador.adicionar(admin);
        repositorioUsuarios.deletar(usuario);
        carregarUsuarios();
    }
    public void listarAdministradores(View view){
        listarAdministradoresActivity();
    }
    private void listarAdministradoresActivity(){
        Intent it = new Intent(this, ListarAdministradoresActivity.class);
        startActivity(it);
    }

    private void ligarUsuario(String numero){
        Intent it = new Intent(Intent.ACTION_CALL);
        it.setData(Uri.parse("tel: "+ numero));
        startActivity(it);
    }
}
