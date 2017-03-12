package pingan.com.recyclerdemo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageDrawable(new HiveDrawable(BitmapFactory.decodeResource(getResources(),R.mipmap.app_icon)));
    }
}
