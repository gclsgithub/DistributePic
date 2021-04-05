package edu.hytc.disflowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisResultController extends AppCompatActivity {

    //処理バタンを押して
    private Button seeLog;

    private ImageView imageView;

    private TextView showDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_result_controller);

        Intent intent = this.getIntent();
        Bitmap bitmap = intent.getParcelableExtra("image");
        imageView.setImageBitmap(bitmap);

        seeLog = findViewById(R.id.seeLog);
        seeLog.setOnClickListener(this::back2Main);


    }

    private void back2Main(View view){
        //得到
        Intent intent = new Intent(this, HomePageController.class);
        startActivity(intent);
    }
}