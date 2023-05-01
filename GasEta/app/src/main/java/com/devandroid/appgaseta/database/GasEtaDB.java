package com.devandroid.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devandroid.appgaseta.controller.CombustivelController;
import com.devandroid.appgaseta.model.Combustivel;

import java.util.ArrayList;
import java.util.List;

public class GasEtaDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;
    private Cursor cursor;
    private SQLiteDatabase db;

    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //QUERY PARA CRIAR TABELA
        String sqlTabelaCombustivel = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";

        sqLiteDatabase.execSQL(sqlTabelaCombustivel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void salvarObjecto(String tabela, ContentValues dados){
        db.insert(tabela, null,dados);
    }

    public List<Combustivel> listarDados(){
        List<Combustivel> lista = new ArrayList<>();

        //Representa um registo que está salvo na tabela de
        // Combustivel do banco de dados da aplicação
        Combustivel registro;
        String query = "SELECT * FROM Combustivel";
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do{
                registro = new Combustivel();
                registro.setId(cursor.getInt(0));
                registro.setNomeDoCombustivel(cursor.getString(1));
                registro.setPrecoDoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));

                lista.add(registro);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public void alterarObjeto(String tabela, ContentValues dados){
        int id = dados.getAsInteger("id");
        db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)});
    }

    public void deletarObjeto(String tabela, int id){
        db.delete(tabela,"id=?", new String[]{Integer.toString(id)});
    }
}
