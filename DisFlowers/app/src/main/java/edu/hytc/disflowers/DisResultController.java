package edu.hytc.disflowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisResultController extends AppCompatActivity {

    //処理バタンを押して
    private Button seeLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_result_controller);

        seeLog = findViewById(R.id.seeLog);
        seeLog.setOnClickListener(this::processSeeLog);
    }

    private void processSeeLog(View view){


        //得到
        Intent intent = new Intent(this, HistoryList.class);
        startActivity(intent);
    }
}