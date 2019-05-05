package activitytest.example.com.tg_design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int take_photo =1;
    private Button takephoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takephoto = findViewById(R.id.camera);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,take_photoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",take_photo);//传递参数id
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);//启动新的intent
            }
        });

    }
}
