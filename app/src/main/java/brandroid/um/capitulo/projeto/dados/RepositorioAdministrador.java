package brandroid.um.capitulo.projeto.dados;

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
