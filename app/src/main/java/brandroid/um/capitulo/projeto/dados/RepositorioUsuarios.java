package brandroid.um.capitulo.projeto.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import brandroid.um.capitulo.projeto.modelo.Usuario;

/**
 * Created by Katrina on 28/11/2015.
 */
public class RepositorioUsuarios {
    private UsuarioOpenHelper usuarioOpenHelper;

    public RepositorioUsuarios(Context context){
        this.usuarioOpenHelper = new UsuarioOpenHelper(context);
    }

    public void inserir(Usuario usuario){
        SQLiteDatabase db = this.usuarioOpenHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario.getUser());
        valores.put("senha",usuario.getSenha());
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("telefone", usuario.getTelefone());
        valores.put("datanasc",usuario.getDatanasc());
        db.insert(usuarioOpenHelper.TABELA_USUARIO,null,valores);
        db.close();
    }
    public void deletar(Usuario usuario) {
        SQLiteDatabase db = this.usuarioOpenHelper.getWritableDatabase();
        db.delete(UsuarioOpenHelper.TABELA_USUARIO,"usuario ='" + usuario.getUser()+ "'",null);
        db.close();
    }

    public List<Usuario> listar(){
        SQLiteDatabase db = this.usuarioOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(
                usuarioOpenHelper.TABELA_USUARIO,
                new String[]{"usuario", "senha", "nome", "email", "telefone","datanasc"},
                null, null, null, null, null);
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        while (cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndex("usuario"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String datanasc = cursor.getString(cursor.getColumnIndex("datanasc"));
                Usuario usuario = new Usuario(user,senha,nome,email,telefone,datanasc);
                listaUsuario.add(usuario);

        }
        return listaUsuario;
    }
}
