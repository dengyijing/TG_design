package activitytest.example.com.tg_design;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class take_photoActivity extends AppCompatActivity {
    private static final int take_photo =1;
    private Uri imageUri;
    private ImageView  picture;
    //接收bundle传递的值
    private Bundle bundle;
    private int choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
       bundle =this.getIntent().getExtras();
       choice=bundle.getInt("id");
       picture = findViewById(R.id.picture_show);
       if(choice == take_photo) {
           File outputImage = new File(getExternalCacheDir(), "outputImage.jpg");
           try {
               if (outputImage.exists()) {
                   outputImage.delete();
               }
               outputImage.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
           if (Build.VERSION.SDK_INT < 24) { //处理图片
               imageUri = Uri.fromFile(outputImage);
           } else {
               imageUri = FileProvider.getUriForFile(take_photoActivity.this, "com.MapScanner.MapScanner", outputImage);
           }
           //启动相机
           Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
           intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
           startActivityForResult(intent, 1);
        try{//将拍摄照片显示出来
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            picture.setImageBitmap(bitmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }
       }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (choice == 1) {
            try {// 将拍摄的照片显示出来
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                picture.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
