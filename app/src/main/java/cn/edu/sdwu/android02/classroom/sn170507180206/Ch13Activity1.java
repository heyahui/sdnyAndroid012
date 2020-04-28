package cn.edu.sdwu.android02.classroom.sn170507180206;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.Manifest;

public class Ch13Activity1 extends AppCompatActivity {
        private EditText ip;
    private EditText port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch13_1);
        ip=(EditText)findViewById(R.id.ch13_1_ip);
        port=(EditText)findViewById(R.id.ch13_1_port);
        SharedPreferences sharedPreference=getSharedPreferences("prefs",MODE_PRIVATE);
        ip.setText(sharedPreference.getString("ip",""));
        port.setText(sharedPreference.getString("port",""));

    }

    public void write(View view){
        EditText editText=(EditText)findViewById(R.id.ch13_1_et);
        String content=editText.getText().toString();
        try{
            FileOutputStream findOutPutString=openFileOutput("andriod2.text",MODE_PRIVATE);
            findOutPutString.write(content.getBytes());
            findOutPutString.flush();
            findOutPutString.close();
        }catch(Exception e){
            Log.e(Ch13Activity1.class.toString(),e.toString());
        }
    }

    public void readRaw(View view){
        Resources resources=getResources();
        InputStream inputStream=resources.openRawResource(R.raw.read);

        try{
            int size=inputStream.available();
            byte[] bytes=new byte[size];
            inputStream.read(bytes);
            String content=new String(bytes);
            Toast.makeText(this,content,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.e(Ch13Activity1.class.toString(),e.toString());

        }finally {
            try{
                inputStream.close();
            }catch (Exception e){
                Log.e(Ch13Activity1.class.toString(),e.toString());
            }
        }
    }
    public void read(View view){
        try{
            FileInputStream fileInputStream=openFileInput("android02.txt");
            int size=fileInputStream.available();
            byte[] bytes=new byte[size];
            fileInputStream.read(bytes);
            String content=new String(bytes);

            fileInputStream.close();
            Toast.makeText(this,content,Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Log.e(Ch13Activity1.class.toString(),e.toString());

        }
    }

    public void saveSharePref(View view){
       SharedPreferences sharedPreferences=getSharedPreferences("prefs",MODE_PRIVATE);
       SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("ip",ip.getText().toString());
        editor.putString("port",port.getText().toString());
        editor.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==101){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                EditText editText=(EditText)findViewById(R.id.ch13_1_et);
                String content=editText.getText().toString();
                writeExternal(content);
            }
        }
        if (requestCode==102){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                readExternal();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void writeSd(View view){
        EditText editText=(EditText)findViewById(R.id.ch13_1_et);
        String content=editText.getText().toString();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            // 1.判断当前用户是否已经授权过
           int result=checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result== PackageManager.PERMISSION_GRANTED){
                writeExternal(content);
            }else {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            }
        }else {
            writeExternal(content);
        }

    }

    private void writeExternal(String content){
        FileOutputStream fileOutputStream=null;
        File file=new File(Environment.getExternalStorageDirectory(),"abcde.text");
        try{
            file.createNewFile();
            if(file.exists()&&file.canWrite()){
                fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(content.getBytes());
            }
        }catch(Exception e){
            Log.e(Ch13Activity1.class.toString(),e.toString());
    }
       if(fileOutputStream!=null){
           try{
               fileOutputStream.flush();
               fileOutputStream.close();
           }catch(Exception e){
               Log.e(Ch13Activity1.class.toString(),e.toString());
           }

       }
    }

    public void readSd(View view){
        EditText editText=(EditText)findViewById(R.id.ch13_1_et);
        String content=editText.getText().toString();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            // 1.判断当前用户是否已经授权过
            int result=checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            if (result== PackageManager.PERMISSION_GRANTED){
                writeExternal(content);
            }else {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},102);
            }
        }else {
            writeExternal(content);
        }

    }
    private void readExternal(){
        File file=new File(Environment.getExternalStorageDirectory(),"abcde.text");
        FileInputStream fileInputStream=null;

        try{
            if (file.exists()&&file.canRead()){
                fileInputStream=new FileInputStream(file);
                int size=fileInputStream.available();
                byte[] bytes=new byte[size];
                fileInputStream.read(bytes);
                Toast.makeText(this,new String(bytes),Toast.LENGTH_LONG);
            }

        }catch (Exception e){
            Log.e(Ch13Activity1.class.toString(),e.toString());
        }finally {
            try{
                fileInputStream.close();
            }catch (Exception e){
                Log.e(Ch13Activity1.class.toString(),e.toString());
            }
        }
    }
}














































