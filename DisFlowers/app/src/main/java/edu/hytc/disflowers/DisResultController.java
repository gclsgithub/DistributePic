package edu.hytc.disflowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import edu.hytc.disflowers.biz.service.AuthUtils;
import edu.hytc.disflowers.biz.service.GsonUtils;
import edu.hytc.disflowers.biz.service.MyData;
import edu.hytc.disflowers.biz.service.PlanService;
import edu.hytc.disflowers.entity.Result;
import edu.hytc.disflowers.entity.ResultEntity;
import edu.hytc.disflowers.util.SavePhoto;

public class DisResultController extends AppCompatActivity {

    //処理バタンを押して
    private Button seeLog;

    private ImageView imageView;

    private ImageView imageView3;

    private ImageView imageView4;

    private ImageView imageView5;

    private TextView textView11;

    private TextView textView22;

    private TextView textView33;

    private TextView textView111;

    private TextView textView222;

    private TextView textView333;

    private Handler handler1;
    private Handler handler2;
    private Handler handler3;
    private Handler handler4;
    private Handler handler5;
    private Handler handler6;
    private Handler handler7;
    private Handler handler8;
    private Handler handler9;


    private static final int COMPLETED = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_result_controller);

        imageView = findViewById(R.id.imageView);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView4);
        textView11 = findViewById(R.id.textView11);
        textView22 = findViewById(R.id.textView22);
        textView33 = findViewById(R.id.textView33);

        textView111 = findViewById(R.id.textView111);
        textView222 = findViewById(R.id.textView222);
        textView333 = findViewById(R.id.textView333);

        byte[] appIcons = MyData.data;
        Bitmap bitmap = BitmapFactory.decodeByteArray(appIcons, 0, appIcons.length);

        imageView.setImageBitmap(bitmap);
        seeLog = findViewById(R.id.seeLog);
        seeLog.setOnClickListener(this::back2Main);


        new Thread(runnable).start();

         handler1 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                Bitmap bt1 = (Bitmap)msg.obj;//类型转化
                imageView3.setImageBitmap(bt1);
            }
        };


        handler2 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                Bitmap bt2 = (Bitmap)msg.obj;//类型转化
                imageView4.setImageBitmap(bt2);
            }
        };


        handler3 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                Bitmap bt3 = (Bitmap)msg.obj;//类型转化
                imageView5.setImageBitmap(bt3);
            }
        };

        handler4 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView11.setText(text1);
            }
        };

        handler5 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView22.setText(text1);
            }
        };

        handler6 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView33.setText(text1);
            }
        };

        handler7 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView111.setText(text1);
            }
        };

        handler8 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView222.setText(text1);
            }
        };

        handler9 =new Handler(){// 创建一个handler对象 ，用于监听子线程发送的消息
            public void handleMessage(Message msg)//接收消息的方法
            {
                String text1 = (String)msg.obj;//类型转化
                textView333.setText(text1);
            }
        };

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

        List<Result> resultList = resultEntity.getResult();


        if (resultList.size()  == 3){

            Bitmap bitmap_1 = getHttpBitmap(resultList.get(0).getBaike_info().getImage_url());
            Bitmap bitmap_2 = getHttpBitmap(resultList.get(1).getBaike_info().getImage_url());
            Bitmap bitmap_3 = getHttpBitmap(resultList.get(2).getBaike_info().getImage_url());

            Message msg1 = new Message();//创建消息类
            msg1.obj=bitmap_1;//消息类对象中存入消息
            handler1.sendMessage(msg1);//通过handler对象发送消息

            Message msg2 = new Message();//创建消息类
            msg2.obj=bitmap_2;//消息类对象中存入消息
            handler2.sendMessage(msg2);//通过handler对象发送消息

            Message msg3 = new Message();//创建消息类
            msg3.obj=bitmap_3;//消息类对象中存入消息
            handler3.sendMessage(msg3);//通过handler对象发送消息


            Message msg4 = new Message();//创建消息类
            msg4.obj=resultList.get(0).getName();//消息类对象中存入消息
            handler4.sendMessage(msg4);//通过handler对象发送消息


            Message msg5 = new Message();//创建消息类
            msg5.obj=resultList.get(1).getName();//消息类对象中存入消息
            handler5.sendMessage(msg5);//通过handler对象发送消息

            Message msg6 = new Message();//创建消息类
            msg6.obj=resultList.get(2).getName();//消息类对象中存入消息
            handler6.sendMessage(msg6);//通过handler对象发送消息

            Message msg7 = new Message();//创建消息类
            msg7.obj=resultList.get(0).getBaike_info().getDescription();//消息类对象中存入消息
            handler7.sendMessage(msg7);//通过handler对象发送消息

            Message msg8 = new Message();//创建消息类
            msg8.obj=resultList.get(1).getBaike_info().getDescription();//消息类对象中存入消息
            handler8.sendMessage(msg8);//通过handler对象发送消息

            Message msg9 = new Message();//创建消息类
            msg9.obj=resultList.get(2).getBaike_info().getDescription();//消息类对象中存入消息
            handler9.sendMessage(msg9);//通过handler对象发送消息

        }
    }

    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

    private void back2Main(View view) {
        //得到
        Intent intent = new Intent(this, HomePageController.class);
        startActivity(intent);
    }

    private byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}