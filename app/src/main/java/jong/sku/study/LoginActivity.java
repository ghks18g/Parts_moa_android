package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import jong.sku.study.retrofit.PartsUserModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static String TAG = "LoginActivity";

    private RetrofitInterface retrofitInterface;

    private EditText login_email;
    private EditText login_password;
    private PartsUserModel partsUserModel;
    Intent intent;
    ImageView imageTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofitInterface = RetrofitUtility.getRetrofitInterface();
        login_email = (EditText)findViewById(R.id.login_email);
        login_password = (EditText)findViewById(R.id.login_password);
        imageTest = (ImageView)findViewById(R.id.imageTest);
//        checkAndRequestPermission();
    }

    public void BackClicked(View view){
        finish();
    }

    public void loginClicked(View view) {

        PartsUserModel to_node = new PartsUserModel();
        to_node.setEmail(login_email.getText().toString());
        to_node.setPassword(login_password.getText().toString());

        RequestBody email = RequestBody.create(MediaType.parse("text/plain"),to_node.getEmail());

        retrofitInterface.login(to_node.getEmail(),to_node.getPassword()).enqueue(new Callback<PartsUserModel>() {
            @Override
            public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                if (response.isSuccessful()){
                    partsUserModel = response.body();
                    if (partsUserModel != null){
                        Log.d(TAG, partsUserModel.getUsername()+"/"+ partsUserModel.getEmail()+"/"+ partsUserModel.getPassword()+"/"+ partsUserModel.getCarId()+"/"+ partsUserModel.getCarInfo());

                        Toast.makeText(getApplicationContext(), partsUserModel.getUsername()+"님 어서오세요 !",Toast.LENGTH_LONG).show();

                        new AsyncTask<Void, Void, ArrayList<File>>() {
                            @Override
                            protected ArrayList<File> doInBackground(Void... voids) {
                                Response<ResponseBody> profileResponse = null;
                                Response<ResponseBody> carResponse = null;
                                try {
                                    profileResponse = retrofitInterface.profileImgDown(to_node.getEmail()).execute();
                                    carResponse = retrofitInterface.carImgDown(to_node.getEmail()).execute();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                ArrayList<File> tempFileList = new ArrayList<File>();

                                if (profileResponse != null){
                                    InputStream profileIs = profileResponse.body().byteStream();
                                    try {
                                        File tempFile = convertInputStreamToFile(profileIs);
                                        tempFileList.add(0,tempFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

//                    bitmapList.add(0,BitmapFactory.decodeStream(profileIs));
                                }
                                if (carResponse != null){
                                    InputStream carIs = carResponse.body().byteStream();
                                    try {
                                        File tempFile = convertInputStreamToFile(carIs);
                                        tempFileList.add(1,tempFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
//                    bitmapList.add(1,BitmapFactory.decodeStream(carIs));
                                }

//                Bitmap bm = BitmapFactory.decodeStream(is);
                                return tempFileList;
                            }

                            @Override
                            protected void onPostExecute(ArrayList<File> data) {
                                super.onPostExecute(data);
//                InputStream is = responseBodyResponse.body().byteStream();

//                Log.e("imageBitmapData:",data.toString());
                                if (data.size() != 0) {
                                    if (data.get(0) != null) {
                                        String profileTempPath = data.get(0).getPath();
                                        Log.e("tempProfilePath",profileTempPath);
                                        partsUserModel.setProfileTempPath(profileTempPath);
                                    }


//                Log.e("imageBitmapData:",data.toString());
                                    if (data.get(1) != null) {
                                        String carTempPath = data.get(1).getPath();
                                        Log.e("tempCarPath",carTempPath);
                                        partsUserModel.setCarTempPath(carTempPath);
                                    }
                                }
                                intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("userModel", partsUserModel);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        }.execute();
                    } else {
                        Log.d(TAG, "Test Model Is NULL");
                    }
                } else {
                    int statusCode = response.code();

                    if (statusCode == 400){
                        Toast.makeText(getApplicationContext(),"해당 이메일주소로 등록된 정보를 찾을 수 없습니다.",Toast.LENGTH_LONG).show();
                    } else if (statusCode == 402){
                        Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요..",Toast.LENGTH_LONG).show();
                    }

                    Log.d(TAG, String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<PartsUserModel> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }

    public static File convertInputStreamToFile(InputStream in) throws IOException {
        File tempFile = File.createTempFile(String.valueOf(in.hashCode()),".tmp");
        tempFile.deleteOnExit();
        copyInputStreamToFile(in,tempFile);
        return  tempFile;
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file){
        try (FileOutputStream outputStream = new FileOutputStream(file)){
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

}