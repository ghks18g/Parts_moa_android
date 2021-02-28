package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jong.sku.study.retrofit.PartsUserModel;

public class MypageActivity extends AppCompatActivity {

    PartsUserModel userModel;
    Intent intent;
    TextView userName, userEmail, userCarInfo;
    private ImageView user_profile, user_car;
    private boolean updated = false;
    private static int REQUEST_UPDATE_PAGE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        intent = getIntent();

        userModel = (PartsUserModel) intent.getSerializableExtra("userModel");

        userName = findViewById(R.id.user_name);
        userName.setText(userModel.getUsername());

        userEmail = findViewById(R.id.user_email);
        userEmail.setText(userModel.getEmail());

        userCarInfo = findViewById(R.id.user_carinfo);
        userCarInfo.setText(userModel.getCarInfo());

        user_profile = findViewById(R.id.user_profile);
        File tempProfile = new File(userModel.getProfileTempPath());

        File tempCar = new File(userModel.getCarTempPath());

        Bitmap profileBm = fileToBitmap(tempProfile);
        Bitmap carBm = fileToBitmap(tempCar);

//        File imgProfile = userModel.getImgProfile();
//        setImageFile(user_profile,imgProfile);
        user_profile.setImageBitmap(profileBm);

        user_car = findViewById(R.id.user_car);
        user_car.setImageBitmap(carBm);
//        File imgCar = userModel.getImgCar();
//        setImageFile(user_car,imgCar);
    }

    private Bitmap fileToBitmap(File tempFile){
        try {
            InputStream inputStream = new FileInputStream(tempFile);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    private Bitmap byteArrayToBitmap(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap;
    }
    private void setImageFile(ImageView imageView, File source){
        if (source != null){
            Bitmap bmp = BitmapFactory.decodeFile(source.getPath());
            imageView.setImageBitmap(bmp);
        }
    }

    public void onBackClicked(View v){
        Intent intent = new Intent();
        intent.putExtra("user",userModel);
        if (updated) {
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    public void onUpdateClicked(View v){
        Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
        intent.putExtra("userModel", userModel);
        startActivityForResult(intent,REQUEST_UPDATE_PAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_UPDATE_PAGE) {
            if (resultCode == RESULT_OK) {
                userModel = (PartsUserModel)data.getSerializableExtra("user");
                userCarInfo.setText(userModel.getCarInfo());

//                File imgProfile = userModel.getImgProfile();
//                setImageFile(user_profile,imgProfile);
//
//                File imgCar = userModel.getImgCar();
//                setImageFile(user_car,imgCar);
                File profileImg = new File(userModel.getProfileTempPath());
                File carImg = new File(userModel.getCarTempPath());
                user_profile.setImageBitmap(fileToBitmap(profileImg));
                user_car.setImageBitmap(fileToBitmap(carImg));
                updated = true;
            }
        } else if (resultCode == RESULT_CANCELED){

        }
    }
}