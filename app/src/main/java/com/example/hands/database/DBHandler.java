package com.example.hands.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATA_NAME = "horizon.db";
    public static final int DATA_VERSION = 1;
    public  Context context;

    private static final String TABLE_NAME = "pacientes_horizon";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nome";
    private static final String COLUMN_IDADE = "idade";
    private static final String COLUMN_TEMP= "temperatura";
    private static final String COLUMN_TOSSE= "tosse";
    private static final String COLUMN_DOR= "dor";
    private static final String COLUMN_PAIS= "pais";
    private static final String COLUMN_SEMANA= "semana";
    private static final String COLUMN_STATUS= "status";

    public DBHandler(Context context) {
        super(context, DATA_NAME, null, DATA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME+ " TEXT UNIQUE," +
                COLUMN_IDADE + " INT(3)," +
                COLUMN_TEMP + " INT(2)," +
                COLUMN_TOSSE+ " INT(2), " +
                COLUMN_DOR + " INT(2), " +
                COLUMN_PAIS+ " TEXT," +
                COLUMN_SEMANA + " INT(2)," +
                COLUMN_STATUS + " TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);


    }

    public boolean addTB( String nome, int idade, int temperatura, int tosse, int dor, String pais, int semana, String status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nome.toLowerCase());
        values.put(COLUMN_IDADE, idade);
        values.put(COLUMN_TEMP, temperatura);
        values.put(COLUMN_TOSSE, tosse);
        values.put(COLUMN_DOR, dor);
        values.put(COLUMN_PAIS, pais);
        values.put(COLUMN_SEMANA , semana);
        values.put(COLUMN_STATUS , status);


        long sid = db.insert(TABLE_NAME, null, values);
        if (sid == -1) {
            Toast.makeText(context, "Nao Salvo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Salvo", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }
    public long updateData(String row_id, String nome, String idade, String temperatura,String tosse,String dor,String pais, String semana,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_IDADE, idade);
        cv.put(COLUMN_TEMP, temperatura);
        cv.put(COLUMN_TOSSE,tosse);
        cv.put(COLUMN_DOR,dor);
        cv.put(COLUMN_PAIS,pais);
        cv.put(COLUMN_SEMANA,semana);
        cv.put(COLUMN_STATUS,status);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
        return result;

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


    public void revien(String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pacientes_horizon where nome=?",new String[]{nome});
        if(cursor.getCount() == 1){
            Toast.makeText(context, "Usuario Já Existe", Toast.LENGTH_SHORT).show();
        }

    }



}
