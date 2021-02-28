package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class CarInfoResistActivity extends AppCompatActivity {

    Context context;
    private Spinner makeridSpinner;
    private Spinner lineUpIdSpinner;
    private Spinner yearIdSpinner;

    private SpinnerUpdate spinnerUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info_resist);
        context = getApplicationContext();
        makeridSpinner = (Spinner) findViewById(R.id.makerid);
        lineUpIdSpinner = (Spinner) findViewById(R.id.lineUpid);
        yearIdSpinner = (Spinner) findViewById(R.id.yearid);

        spinnerUpdate = new SpinnerUpdate(makeridSpinner,lineUpIdSpinner,yearIdSpinner);

        spinnerUpdate.Spinner_maker_lineup_year_Update(context);
    }

    public void onCarUpdateBtnClicked(View v){
        Toast.makeText(context,"차량정보:"+spinnerUpdate.getCarInfo()+"/ 차량 id:"+spinnerUpdate.getCarInfoId(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("CarInfo",spinnerUpdate.getCarInfo());
        intent.putExtra("CarInfoId",spinnerUpdate.getCarInfoId());
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onbtnDelete(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}