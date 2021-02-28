package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jong.sku.study.retrofit.PartsUserModel;
import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    PartsUserModel userModel;
    Intent intent;
    TextView userName, userEmail, userCarInfo;
    ImageView userProfile, userCar;
    private RetrofitInterface retrofitInterface;
    private String imgProfile,imgCar;
    private Context mContext;
    private static String TAG = "UpdateActivity";
    private static int REQUEST_RESET_CAR = 3;
    private static int REQUEST_PROFILEIMG = 0;
    private static int REQUEST_CARIMG = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        checkAndRequestPermission();
        mContext = this;
        imgProfile = "";
        imgCar = "";
        intent = getIntent();
        retrofitInterface = RetrofitUtility.getRetrofitInterface();
        userModel = (PartsUserModel) intent.getSerializableExtra("userModel");

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userCarInfo = findViewById(R.id.user_carinfo);

        userProfile = findViewById(R.id.user_profile);
        userCar = findViewById(R.id.user_car);


        File tempProfile = new File(userModel.getProfileTempPath());

        File tempCar = new File(userModel.getCarTempPath());

        Bitmap pre_profile = fileToBitmap(tempProfile);
        Bitmap pre_car = fileToBitmap(tempCar);

        userProfile.setImageBitmap(pre_profile);
        userCar.setImageBitmap(pre_car);

        userName.setText(userModel.getUsername());
        userEmail.setText(userModel.getEmail());
        userCarInfo.setText(userModel.getCarInfo());

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGallery(REQUEST_PROFILEIMG);
            }
        });
        userCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGallery(REQUEST_CARIMG);
            }
        });
    }

    public void btn_ResetCarInfo(View v){
        Intent intent = new Intent(this,CarInfoResistActivity.class);
        startActivityForResult(intent,REQUEST_RESET_CAR);
    }

    public void btn_UpdateClicked(View v){
        RequestBody userEmail = RequestBody.create(MediaType.parse("text/plain"),userModel.getEmail());
        RequestBody userCarInfo = RequestBody.create(MediaType.parse("text/plain"),userModel.getCarInfo());
        RequestBody userCarId = RequestBody.create(MediaType.parse("text/plain"),userModel.getCarId());
        RequestBody userPassword = RequestBody.create(MediaType.parse("text/plain"),userModel.getPassword());

        File profileImg = new File(imgProfile);
        File carImg = new File(imgCar);

//        Bitmap profileBm = BitmapFactory.decodeFile(userModel.getImgProfilePath());
//        Bitmap carBm = BitmapFactory.decodeFile(carImg.getPath());

        userModel.setImgProfile(profileImg);
        userModel.setImgCar(carImg);
//        userModel.setProfileBm(profileBm);
//        userModel.setCarBm(carBm);

        RequestBody reqProfile = RequestBody.create(MediaType.parse("multipart/form-data"),profileImg);
        RequestBody reqCar = RequestBody.create(MediaType.parse("multipart/form-data"),carImg);
        MultipartBody.Part profile = MultipartBody.Part.createFormData("image",profileImg.getName(),reqProfile);
        MultipartBody.Part car = MultipartBody.Part.createFormData("image",carImg.getName(),reqCar);

        if (imgProfile.equals("") && imgCar.equals("")){
            retrofitInterface.user_update(userModel.getEmail(),userModel.getCarInfo(),userModel.getCarId(),userModel.getPassword()).enqueue(new Callback<PartsUserModel>() {
                @Override
                public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                    if (response.isSuccessful()){
                        Log.d(TAG,response.message());
                        Intent intent = new Intent();
                        intent.putExtra("user",userModel);

                        setResult(RESULT_OK,intent);
                        finish();

                    } else {
                        int statusCode = response.code();
                        Log.d(TAG, String.valueOf(statusCode));
                    }
                }

                @Override
                public void onFailure(Call<PartsUserModel> call, Throwable t) {

                }
            });
        } else if (imgCar.equals("")){
            retrofitInterface.user_updateWithProfile(userEmail,userCarInfo,userCarId,userPassword,profile).enqueue(new Callback<PartsUserModel>() {
                @Override
                public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                    if (response.isSuccessful()){
                        Log.d(TAG,response.message());
                        userModel.setImgProfile(null);
                        userModel.setProfileTempPath(profileImg.getPath());
                        Intent intent = new Intent();
                        intent.putExtra("user",userModel);
                        setResult(RESULT_OK,intent);
                        finish();

                    } else {
                        int statusCode = response.code();
                        Log.d(TAG, String.valueOf(statusCode));
                    }
                }

                @Override
                public void onFailure(Call<PartsUserModel> call, Throwable t) {

                }
            });

        } else if (imgProfile.equals("")){
            retrofitInterface.user_updateWithCar(userEmail,userCarInfo,userCarId,userPassword,car).enqueue(new Callback<PartsUserModel>() {
                @Override
                public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                    if (response.isSuccessful()){
                        Log.d(TAG,response.message());
                        userModel.setImgCar(null);
                        userModel.setCarTempPath(carImg.getPath());
                        Intent intent = new Intent();
                        intent.putExtra("user",userModel);
                        setResult(RESULT_OK,intent);
                        finish();

                    } else {
                        int statusCode = response.code();
                        Log.d(TAG, String.valueOf(statusCode));
                    }
                }

                @Override
                public void onFailure(Call<PartsUserModel> call, Throwable t) {

                }
            });
        }

        retrofitInterface.user_updateWithProfileAndCar(userEmail,userCarInfo,userCarId,userPassword,profile,car).enqueue(new Callback<PartsUserModel>() {
            @Override
            public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                if (response.isSuccessful()){
                     Log.d(TAG,response.message());
                     userModel.setImgProfile(null);
                     userModel.setImgCar(null);
                     userModel.setProfileTempPath(profileImg.getPath());
                     userModel.setCarTempPath(carImg.getPath());
                     Intent intent = new Intent();
                     intent.putExtra("user",userModel);
                     Log.e(TAG,userModel.toString());   // 그냥 이대로 인텐트에 모델을 넘기면 이미지 사이즈 때문에 FAILED BINDER TRANSACTION Exception 발생.

                     setResult(RESULT_OK,intent);
                     finish();

                } else {
                    int statusCode = response.code();
                    Log.d(TAG, String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<PartsUserModel> call, Throwable t) {

            }
        });

    }

    public void btn_backClicked(View v){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PROFILEIMG) {
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                imgProfile = getRealPathFromURI(uri);
                File profile = new File(imgProfile);
                Log.e(TAG,"profileImgFile: "+profile.toString());
//                Log.e(TAG,imgProfile);
                try {
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                    userModel.setProfileBitmapBytes(bitmapToByteArray(bm));
                    userProfile.setImageBitmap(bm);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else if(requestCode == REQUEST_CARIMG){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                imgCar = getRealPathFromURI(uri);
                File car = new File(imgCar);
                Log.e(TAG,"carImgfile: "+car.toString());
                try {
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                    userModel.setCarBitmapBytes(bitmapToByteArray(bm));
                    userCar.setImageBitmap(bm);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else if (requestCode == REQUEST_RESET_CAR){
            if (resultCode == RESULT_OK){
                String carInfo_Up = data.getStringExtra("CarInfo");
                String carInfoId_Up = data.getStringExtra("CarInfoId");
                Toast.makeText(this,"차량정보:"+carInfo_Up+"/ Id:"+carInfoId_Up,Toast.LENGTH_LONG).show();
                userModel.setCarInfo(carInfo_Up);
                userModel.setCarId(carInfoId_Up);
                userCarInfo.setText(carInfo_Up);
            }

        }else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURI(Uri contentUri){
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri,proj,null,null,null);
        cursor.moveToFirst();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        return cursor.getString(column_index);
    }

    private void getGallery(int requestCode){

        if (Build.VERSION.SDK_INT < 19){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            ((Activity)mContext).startActivityForResult(Intent.createChooser(intent, "Get Album"),requestCode);
        }else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            ((Activity)mContext).startActivityForResult(Intent.createChooser(intent, "Get Album"),requestCode);
        }

    }
    private Bitmap byteArrayToBitmap(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap;
    }

    private byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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

    private boolean checkAndRequestPermission(){
        int storageread = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionNeeded = new ArrayList<>();

        if (storageread != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this,listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),1000);
            return false;
        }
        return true;
    }
}