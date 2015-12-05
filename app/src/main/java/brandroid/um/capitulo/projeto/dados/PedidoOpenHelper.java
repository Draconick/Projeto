package brandroid.um.capitulo.projeto.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Katrina on 04/12/2015.
 */
public class PedidoOpenHelper extends SQLiteOpenHelper {
    public static final String TABELA_PEDIDO = "pedido";
    private static final String DATABASE_NAME = "database_pedido";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_TABELA_PEDIDO = "Create table " + TABELA_PEDIDO + "(" +
            "id integer not null primary key autoincrement," +
            "usuario text not null," +
            "valorpedido real not null," +
            "lucropedido real not null)";

    public PedidoOpenHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABELA_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
