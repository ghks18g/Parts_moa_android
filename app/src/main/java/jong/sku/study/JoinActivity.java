package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import jong.sku.study.retrofit.PartsUserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    EditText joinName, joinEmail, joinpassword, joinpassword2;
    private static boolean emailCheck = false;
    private static boolean passwordCheck = false;
    private static String TAG = "JOINACTIVITY";
    private Context context;
    private Spinner makeridSpinner;
    private Spinner lineUpIdSpinner;
    private Spinner yearIdSpinner;
    private String carId = "";
    private String carInfo= "";
    private SpinnerUpdate spinnerUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        context = getApplicationContext();
        retrofitInterface = RetrofitUtility.getRetrofitInterface();
        joinName = (EditText) findViewById(R.id.join_name);
        joinEmail = (EditText) findViewById(R.id.join_email);
        joinpassword = (EditText) findViewById(R.id.join_password);
        joinpassword2 = (EditText) findViewById(R.id.join_pwck);
        makeridSpinner = (Spinner) findViewById(R.id.makerid);
        lineUpIdSpinner = (Spinner) findViewById(R.id.lineUpid);
        yearIdSpinner = (Spinner) findViewById(R.id.yearid);

        spinnerUpdate = new SpinnerUpdate(makeridSpinner,lineUpIdSpinner,yearIdSpinner);

        spinnerUpdate.Spinner_maker_lineup_year_Update(context);

    }

    public void onEmailCheck(View view){
        String str_email = joinEmail.getText().toString();
        if (str_email.equals("")){
            Toast.makeText(context,"이메일을 입력해 주세요! ",Toast.LENGTH_LONG).show();
            return;
        }
        PartsUserModel toEmailCheck = new PartsUserModel();
        toEmailCheck.setEmail(str_email);
        retrofitInterface.email_check(toEmailCheck.getEmail()).enqueue(new Callback<PartsUserModel>() {
            @Override
            public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                if (response.isSuccessful()){
                    PartsUserModel ret = response.body();
                    if(ret.getEmail().equals("join_continue")){
                        emailCheck = true;
                        Toast.makeText(context,"이 이메일을 사용하여 가입하실 수 있습니다.", Toast.LENGTH_LONG).show();
                    }else if(ret.getEmail().equals("exist")){
                        emailCheck = false;
                        Toast.makeText(context,"이미 가입된 이메일 입니다. 확인해 주세요.",Toast.LENGTH_LONG).show();
                    }

                }else {
                    int statusCode = response.code();
                    Log.d(TAG,String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<PartsUserModel> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void onJoinBtnClicked(View view){
        PartsUserModel to_signIn = new PartsUserModel();
        String join_username = joinName.getText().toString();
        String join_email = joinEmail.getText().toString();
        String join_password = joinpassword.getText().toString();
        String join_password2 = joinpassword2.getText().toString();

        carId = spinnerUpdate.getCarInfoId();
        carInfo = spinnerUpdate.getCarInfo();
        Log.d(TAG,"CarId:"+carId);
        Log.d(TAG,"CarInfo:"+carInfo);
        to_signIn.setUsername(join_username);
        to_signIn.setEmail(join_email);
        to_signIn.setCarId(carId);
        to_signIn.setCarInfo(carInfo);

        if ((!join_password.equals(null))&&join_password.equals(join_password2)){
            to_signIn.setPassword(join_password);
            passwordCheck = true;
        }

        if(emailCheck && passwordCheck){
            retrofitInterface.signIn(to_signIn.getUsername(),to_signIn.getEmail(),to_signIn.getPassword(),to_signIn.getCarId(),to_signIn.getCarInfo())
                    .enqueue(new Callback<PartsUserModel>() {
                        @Override
                        public void onResponse(Call<PartsUserModel> call, Response<PartsUserModel> response) {
                            if (response.isSuccessful()){
                                PartsUserModel partsUserModel = response.body();
                                if (partsUserModel != null){
                                    Log.d(TAG, partsUserModel.getUsername()+"/"+ partsUserModel.getEmail()+"/"+ partsUserModel.getPassword()+"/"+ partsUserModel.getCarId());
                                } else {
                                    Log.d(TAG, "Test Model Is NULL");
                                }
                            } else {
                                int statusCode = response.code();
                                Log.d(TAG, String.valueOf(statusCode));
                            }
                        }

                        @Override
                        public void onFailure(Call<PartsUserModel> call, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });
            Toast.makeText(context,"회원가입이 성공적으로 이루어졌습니다. 로그인 해주세요!",Toast.LENGTH_SHORT).show();
            finish();
        } else if(!emailCheck){
            Toast.makeText(context,"이미 가입된 이메일이거나 이메일 확인이 이루어 지지 않았습니다.",Toast.LENGTH_LONG).show();
        } else if(!passwordCheck){
            Toast.makeText(context,"비밀번호를 확인해주세요.",Toast.LENGTH_LONG).show();
        }

    }

    public void onClickDelete (View view){
        finish();
    }
}