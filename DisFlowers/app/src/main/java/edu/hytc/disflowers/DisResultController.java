package edu.hytc.disflowers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import edu.hytc.disflowers.biz.service.AuthUtils;
import edu.hytc.disflowers.biz.service.GsonUtils;
import edu.hytc.disflowers.biz.service.MyData;
import edu.hytc.disflowers.biz.service.PlanService;
import edu.hytc.disflowers.entity.ResultEntity;
import edu.hytc.disflowers.util.SavePhoto;

public class DisResultController extends AppCompatActivity {

    //処理バタンを押して
    private Button seeLog;

    private ImageView imageView;

    private TextView showDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_result_controller);

        imageView = findViewById(R.id.imageView);

        byte[] appIcons = MyData.data;
        Bitmap bitmap = BitmapFactory.decodeByteArray(appIcons, 0, appIcons.length);

        imageView.setImageBitmap(bitmap);
        seeLog = findViewById(R.id.seeLog);
        seeLog.setOnClickListener(this::back2Main);

        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            initComment();
        }
    };

    /**
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "call back", Toast.LENGTH_SHORT).show();
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                //创建savephoto类保存图片
                SavePhoto savePhoto = new SavePhoto(DisResultController.this);
                savePhoto.SaveBitmapFromView(imageView);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    private void initComment() {

        //建立链接
        String token = AuthUtils.getAuth();

        Log.i("token", token);


        Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        Bitmap bitmap1 = Bitmap.createBitmap(image);


        String result = PlanService.plantData(bitmap2Bytes(bitmap1), token);

        ResultEntity resultEntity = GsonUtils.fromJson(result, ResultEntity.class);


    }


    private void back2Main(View view) {
        //得到
        Intent intent = new Intent(this, HomePageController.class);
        startActivity(intent);
    }

    private byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}