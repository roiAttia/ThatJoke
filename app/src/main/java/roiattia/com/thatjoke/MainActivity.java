package roiattia.com.thatjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Joker joker = new Joker();

        Button button = findViewById(R.id.btn_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra("joke", joker.getJoke());
                startActivity(intent);
//                Toast.makeText(MainActivity.this, joker.getJoke(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
