package edu.hytc.disflowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryList extends AppCompatActivity {

    //処理バタンを押して
    private Button back2Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        back2Home = findViewById(R.id.back2Home);

        back2Home.setOnClickListener(this::back2Home);
    }


    private void back2Home(View view){

        Intent intent = new Intent(this, HomePageController.class);
        startActivity(intent);
    }
}