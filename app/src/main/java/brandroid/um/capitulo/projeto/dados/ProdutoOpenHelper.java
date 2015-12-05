package brandroid.um.capitulo.projeto.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Katrina on 29/11/2015.
 */
public class ProdutoOpenHelper extends SQLiteOpenHelper {
    public static final String TABELA_PRODUTO = "produto";
    private static final String DATABASE_NAME = "database_produto";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_TABELA_PRODUTO = "CREATE TABLE " + TABELA_PRODUTO + "("+
            "nome text not null primary key," +
            "categoria text not null," +
            "qntestoque integer not null," +
            "preco real not null," +
            "valorCompra real not null," +
            "unidadesCompradas integer not null)";

    public ProdutoOpenHelper(Context contexto){
        super(contexto,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABELA_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
