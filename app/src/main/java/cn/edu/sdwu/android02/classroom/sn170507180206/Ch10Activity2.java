package cn.edu.sdwu.android02.classroom.sn170507180206;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ch10Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch10_2);
    }
    public void send_broadcast(View view){
        //发送广播
        Intent intent=new Intent("com.inspur.broadcast");//指定频道
        intent.putExtra("key1","message");

        sendBroadcast(intent);//发送
    }

    public void ch10Activity(View view){
        Intent intent=new Intent(this,Ch10Activity1.class);
        EditText editText=(EditText)findViewById(R.id.ch10_1_et);
        intent.putExtra("text",editText.getText().toString());//设置传递的数值
        startActivity(intent);
    }
    public void startSubActivity(View view){
        Intent intent=new Intent(this,ch10Activity3.class);
        startActivityForResult(intent,101);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultcode, @Nullable Intent data) {
        //在父类中茯取返回值
        //requestcode用来区分从哪一个子activity返回的詰果
        if (requestCode == 101) {
            if (resultcode == RESULT_OK) {
                //用戸点击的确认，迸-歩茯取数据
                String name = data.getStringExtra("name");
                Toast.makeText( this, name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText( this, "cancel", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == 102){
            if(requestCode==RESULT_OK){
                String content=data.getDataString();
                Toast.makeText( this, "content", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText( this, "cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void web(View view){
        //使用隐式启动打开百度
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://baidu.com"));
        startActivity(intent);
    }

    public void contactsList(View view){
        //使用隐式查看联系人列表
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
        startActivity(intent);
    }
    public void contactsDetail(View view){
        //查看联系人明细
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));
        startActivity(intent);
    }
    public void showMap(View view){
        //打开地图
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:50.123.7.1434"));
        startActivity(intent);
    }
    public void showPhone(View view){
        //打开图片
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content:"));
        startActivity(intent);
    }
    public void pickContact(View view){
        // /以FActivity的形式，打开联系人列表，让用户选择一个联系人后， 返回结果
        Intent intent=new Intent(Intent.ACTION_PICK);//隐式启动
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,102);
    }
    public void implicitStart(View view) {
        Intent intent = new Intent(" com. inspur.action");
        intent.setData(Uri.parse("abc://inspur.com "));
        startActivity(intent);
    }

}






































