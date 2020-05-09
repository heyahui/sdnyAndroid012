package cn.edu.sdwu.android02.classroom.sn170507180206;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.TextureView;
import android.view.View;

public class Ch16Activity1 extends AppCompatActivity {
    private TextureView textureView;
    private SurfaceTexture surfaceTexture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch16_1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);

            }
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    public void call(View view) {
        //判断当前用户手机系统版本，是否是6.0之后的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.CALL_PHONE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);

        }
    }

    public void chgOri(View view) {
        //改变屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
        // setRequestedOrientat ion (ActivityInfo.SCREEN _ORIENTATION_PORTRAIT)竖屏
    }
    public void sms(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.SEND_SMS);
            if (result == PackageManager.PERMISSION_GRANTED) {
                sendSms();
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 102);
            }
        }
    }
    @Override

    public void onRequestPermissionsResult (int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        }
        if (requestCode == 102) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                sendSms();
            }
        }
        if (requestCode == 104) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            }
        }
    }
    private void sendSms(){
//借助FSmsManager工具进行发送
        SmsManager smsManager=SmsManager.getDefault();
        smsManager. sendTextMessage(" 13305311234 ", "1311111111"," short message test" , null, null) ;
    }
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel://13956789999"));
        //startActivity(intent);
    }
}
