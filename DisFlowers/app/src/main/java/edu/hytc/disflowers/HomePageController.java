package edu.hytc.disflowers;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageController extends AppCompatActivity {

    protected static final int REQUEST_IMAGE_CAPTURE = 1;

    protected static final int REQUEST_ABLUM_CAPTURE = 2;


    static final int REQUEST_TAKE_PHOTO = 1;

    private Uri outputFileUri;


    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

    String currentPhotoPath;

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

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void processPicAndMove2Result(View view) {

        //获取图片信息
        //处理图片（识别功能）
        //得到
        Intent intent = new Intent(this, DisResultController.class);


        //调用setimage方法，得到返回值bitmap
        Bitmap bitmap = setimage(imageView);
        intent.putExtra("image",bitmap);

        startActivity(intent);
    }

    /**
     * 调用拍照功能
     * 　　　１.调用系统相机
     * 　*　　　２.拍照好了之后回掉
     * ３.跳转画面
     *
     * @param view
     */
    private void takePicturefunction(View view) {
        Toast.makeText(this, "调用相机", Toast.LENGTH_SHORT).show();
        //１.调用系统相机
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //把图片转换成bitmap形式传递通过intent形式传递过去
    private Bitmap setimage(ImageView view1){
        Bitmap image = ((BitmapDrawable)view1.getDrawable()).getBitmap();
        Bitmap bitmap1 = Bitmap.createBitmap(image);
        return bitmap1;
    }

    /**
     * 调用拍照功能
     * ２.拍照好了之后回掉  保存图片
     * ３.跳转画面
     *
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
        } else if (requestCode == REQUEST_ABLUM_CAPTURE && resultCode == RESULT_OK) {

            imageView.setImageURI(data.getData());
        }
    }

    /**
     * 显示相册
     *
     * @param view
     */
    private void showAblum(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        galleryIntent.setType("image/*");//图片
        startActivityForResult(galleryIntent, REQUEST_ABLUM_CAPTURE);//跳转，传递打开相册请求码
    }


    /**
     * android4.4以后返回的URI只有图片编号
     * 获取图片真实路径
     *
     * @param contentURI
     * @return
     */
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


}