package brandroid.um.capitulo.projeto.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Katrina on 17/11/2015.
 */
public class UsuarioOpenHelper extends SQLiteOpenHelper {
    public static final String TABELA_USUARIO = "usuario";
    private static final String DATABASE_NAME = "usuario_database";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_TABELA_USUARIO = "CREATE TABLE " + TABELA_USUARIO + "(" +
            "usuario text not null primary key," +
            "senha text not null," +
            "nome text not null," +
            "email text not null," +
            "telefone text not null)";

    public UsuarioOpenHelper(Context contexto){
        super(contexto,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
