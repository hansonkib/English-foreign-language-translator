package com.example.translate;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryData(String sql){
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
    }
    public void insertHistory(String sourceLanguage,String targetLanguage){
        SQLiteDatabase database = getWritableDatabase();
        String sql ="INSERT INTO history VALUES(?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,sourceLanguage);
        statement.bindString(2,targetLanguage);
        statement.executeInsert();
    }
    public void insertFavourite(String sourceLanguage,String targetLanguage){
        SQLiteDatabase database = getWritableDatabase();
        String sql ="INSERT INTO favourites VALUES(?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,sourceLanguage);
        statement.bindString(2,targetLanguage);
        statement.executeInsert();
    }

//    updating student data
//    public boolean udateStudent(){
//SQLiteDatabase db=this.getWritableDatabase(String name,String regNo,String Course,String year);
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(name,name);
//        contentValues.put(String regNo,regNo);
//        contentValues.put(Course,Course);
//        contentValues.put(year,year);
//        db.update(tableName,contentValues,"regNo = ?", new String[] { regNo});
//        return true;
//    }
//    deleting students data
//    public Integer deleteStudent(){
//        SQLiteDatabase db=this.getWritableDatabase(String regNo);
//        return db.delete("student","regNo = ?", new String[] { regNo });
//    }
    //retrieving all data in the student database
    public Cursor allHistory(){
       SQLiteDatabase db=this.getReadableDatabase();
               Cursor cursor=db.rawQuery("SELECT * FROM history",null);
               return cursor;
    }
    public Cursor allFavourites(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM favourites",null);
        return cursor;
    }
    public boolean checkUsernam(String name){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM users WHERE name=?",new String[]{name});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public boolean checkReg(String regNo){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM student WHERE regNo=?",new String[]{regNo});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public boolean deleteStudent(String regNo){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("student","regNo=?",new String[]{regNo})>0;
    }
    public String  getAllStudents(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT name FROM student",new String[]{});
        return String.valueOf(cursor);
    }
    public boolean checkUsername(String username){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM users WHERE username=?",new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public boolean login(String username,String password){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM users WHERE username=? AND password=?",new String[]{username,password});
        if (cursor.getCount()>1) return true;
        else return false;
    }
    public boolean allUsers(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM user",new String[]{});
        if (cursor.getCount()<0) return false;
        else return true;
    }
    public Cursor getUser(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
