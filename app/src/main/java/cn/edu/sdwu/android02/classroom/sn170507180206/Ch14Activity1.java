package cn.edu.sdwu.android02.classroom.sn170507180206;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContextView;
import android.util.Log;
import android.view.View;

public class Ch14Activity1 extends AppCompatActivity {
    private MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch14_1);

        myOpenHelper=new MyOpenHelper(this);
        //以可写方式打开数据库

    }
    public void insert(View view){
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            ContentValues contentValues=new ContentValues();
            contentValues.put("stuname","Tom");
            contentValues.put("stutel","13666666666");
            sqLiteDatabase.insert("student",null,contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.endTransaction();//结束食物
            sqLiteDatabase.close();
        }

    }

    public void query(View view){
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();
        try {
           Cursor cursor=sqLiteDatabase.rawQuery("select * from student where stuname=?",new String[]{"Tom"});
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String stuname=cursor.getString(cursor.getColumnIndex("stuname"));
                String stutal=cursor.getString(cursor.getColumnIndex("stutal"));
                Log.i(Ch14Activity1.class.toString(),"id:"+id+",stuname:"+stuname+"stutal:"+stutal);
            }
            cursor.close();
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.endTransaction();//结束食物
            sqLiteDatabase.close();
        }
    }

    public void delete(View view){
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try {

            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete("stedent","id=?",new  String[]{"1"});
            sqLiteDatabase.setTransactionSuccessful();

        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.endTransaction();//结束食物
            sqLiteDatabase.close();
        }

    }

    public void modify(View view){
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try {

            sqLiteDatabase.beginTransaction();
            ContentValues contentValues=new ContentValues();
            contentValues.put("stutel","1369999996");

            sqLiteDatabase.update("student",contentValues,"id=?",new  String[]{"2"});
            sqLiteDatabase.setTransactionSuccessful();

        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.endTransaction();//结束食物
            sqLiteDatabase.close();
        }

    }

}










































