package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import brandroid.um.capitulo.projeto.dados.RepositorioUsuarios;
import brandroid.um.capitulo.projeto.modelo.Usuario;

/**
 * Created by Katrina on 04/12/2015.
 */
public class EditarUsuarioActivity extends Activity {
    private String userLogin;
    private EditText editUser, editSenha,editNome,editEmail,editTel;
    private int ano, mes, dia;
    private Button dataCadastro;
    private Usuario usuario;
    private RepositorioUsuarios repositorioUsuarios;
    private List<Usuario> usuarioList;
    private TextView textCadastro;
    private Button buttonCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_cadastro_layout);
        userLogin = (String) getIntent().getSerializableExtra("user");
        //Campos de Cadastro
        editUser = (EditText) findViewById(R.id.edit_cadastro_user);
        editSenha = (EditText) findViewById(R.id.edit_cadastro_senha);
        editNome = (EditText) findViewById(R.id.edit_cadastro_nome);
        editEmail = (EditText) findViewById(R.id.edit_cadastro_email);
        editTel = (EditText) findViewById(R.id.edit_cadastro_telefone);
        textCadastro = (TextView) findViewById(R.id.cadastrar_usuario);
        buttonCadastro = (Button) findViewById(R.id.cadastro_usuario);

        repositorioUsuarios = new RepositorioUsuarios(this);
        usuarioList = repositorioUsuarios.listar();
        textCadastro.setText("Atualizar Cadastro");
        buttonCadastro.setText("Atualizar");

        //Calendário Data Nascimento
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataCadastro = (Button) findViewById(R.id.cadastro_data);
        dataCadastro.setText(dia + "/" + (mes+1) + "/" + ano);
        for(Usuario u : usuarioList){
            if(u.getUser().equalsIgnoreCase(userLogin)){
                editUser.setText(u.getUser());
                editSenha.setText(u.getSenha());
                editNome.setText(u.getNome());
                editEmail.setText(u.getEmail());
                editTel.setText(u.getTelefone());
                dataCadastro.setText(u.getDatanasc());
            }
        }
    }

    //Implementação calendário no campo Data de Nascimento
    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(R.id.cadastro_data == id) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth){
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataCadastro.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };

    //Adicionar usuário ao Banco de Dados
    public void cadastrarUsuario(View view) {
        //Recuperando Dados do EditText
        String user = editUser.getText().toString();
        String senha = editSenha.getText().toString();
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTel.getText().toString();
        String datanasc = dataCadastro.getText().toString();
        if (user.isEmpty() || senha.isEmpty() || nome.isEmpty() ||
                email.isEmpty() || telefone.isEmpty()) {
            Toast toast = Toast.makeText(this, R.string.cadastro_invalido, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            usuario = new Usuario(user, senha, nome, email, telefone, datanasc);
            repositorioUsuarios.alterar(usuario, userLogin);
            Toast toast = Toast.makeText(this, R.string.cadastro_alterado, Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }
}
