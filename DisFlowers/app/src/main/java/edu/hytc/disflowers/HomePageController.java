package edu.hytc.disflowers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomePageController extends AppCompatActivity {

    protected static final int REQUEST_IMAGE_CAPTURE = 1;

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

    private Bitmap bitmap;

    //拍照按钮
    private Button takePicture;

    //相册按钮
    private Button myAlbum;

    //処理バタンを押して
    private Button doProcess;

    //图片显示
    private ImageView imageView;

    public HomePageController() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_controller);

        //初始化按钮
        takePicture = findViewById(R.id.takePicture);
        myAlbum = findViewById(R.id.myAlbum);
        imageView = findViewById(R.id.showImage);
        doProcess = findViewById(R.id.doProcess);


        //设置监听事件 -- 拍照
        takePicture.setOnClickListener(this::takePicturefunction);
        myAlbum.setOnClickListener(this::showAblum);
        doProcess.setOnClickListener(this::processPicAndMove2Result);

        //设置监听事件 -- 显示相册
        myAlbum.setOnClickListener(this::showAblum);

    }

    private void processPicAndMove2Result(View view){

        //获取图片信息


        //处理图片（识别功能）


        //得到
        Intent intent = new Intent(this, DisResultController.class);
        startActivity(intent);
    }

    /**
     * 调用拍照功能
     * 　　　１.调用系统相机
    　*　　　２.拍照好了之后回掉
     *      ３.跳转画面
     * @param view
     */
    private void takePicturefunction(View view){
        Toast.makeText(this, "调用相机", Toast.LENGTH_SHORT).show();
        //１.调用系统相机
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * 调用拍照功能
     *      ２.拍照好了之后回掉  保存图片
     *      ３.跳转画面
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "call back", Toast.LENGTH_SHORT).show();
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    /**
     *  显示相册
     * @param view
     */
    private void showAblum(View view){

    }


}