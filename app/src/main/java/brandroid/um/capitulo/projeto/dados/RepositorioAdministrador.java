package brandroid.um.capitulo.projeto.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import brandroid.um.capitulo.projeto.modelo.Administrador;

/**
 * Created by Katrina on 29/11/2015.
 */
public class RepositorioAdministrador {
    private AdministradorOpenHelper administradorOpenHelper;

    public RepositorioAdministrador(Context context){
        administradorOpenHelper = new AdministradorOpenHelper(context);
    }
    public void adicionar(Administrador administrador){
        SQLiteDatabase db = administradorOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", administrador.getUser());
        values.put("senha",administrador.getSenha());
        values.put("nome",administrador.getNome());
        values.put("email",administrador.getEmail());
        values.put("telefone",administrador.getTelefone());
        values.put("datanasc", administrador.getDatanasc());
        db.insert(administradorOpenHelper.TABELA_ADMIN,null,values);
        db.close();
    }
    public void deletar(Administrador administrador){
        SQLiteDatabase db = administradorOpenHelper.getWritableDatabase();
        db.delete(administradorOpenHelper.TABELA_ADMIN,
                "usuario = '"+administrador.getUser() + "'",null);
    }
    public List<Administrador> listar(){
        SQLiteDatabase db = administradorOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(administradorOpenHelper.TABELA_ADMIN,
                new String[]{"usuario", "senha", "nome", "email", "telefone","datanasc"}, null, null, null, null, null);
        List<Administrador> listarAdministrador = new ArrayList<Administrador>();
        while(cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndex("usuario"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String datanasc = cursor.getString(cursor.getColumnIndex("datanasc"));
            Administrador administrador = new Administrador(user,senha,nome,email,telefone,datanasc);
            listarAdministrador.add(administrador);
        }
        return listarAdministrador;
    }
}
