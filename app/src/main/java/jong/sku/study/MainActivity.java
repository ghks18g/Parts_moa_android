package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity"," is running ok");
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            lat = userLocation.getLatitude();
            lng = userLocation.getLongitude();
            System.out.println("현재 위치 값은 : " +lng+ ", " + lat);

            System.out.println("///////////////////////////");
            GeoPoint in_point = new GeoPoint(lng,lat);
            GeoPoint katec_point=GeoTrans.convert(GeoTrans.GEO,GeoTrans.KATEC,in_point);
            System.out.println(katec_point.getX()+"/"+katec_point.getY());
            GeoPoint sample_KATEC_pt = new GeoPoint(292840.30000,548077.90000);
            GeoPoint sample_Geo_pt = GeoTrans.convert(GeoTrans.KATEC,GeoTrans.GEO,sample_KATEC_pt);
            System.out.println(sample_Geo_pt.getX()+"/"+sample_Geo_pt.getY());
        }

    }

    private Location getMyLocation(){
        Location currentLoacation = null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            System.out.println("//// 사용 권한 요청 ");
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation();
        } else {
            System.out.println(" // 권한 요청이 안될 경우 수동으로 구하기");
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLoacation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLoacation != null) {
                double lng = currentLoacation.getLongitude();
                double lat = currentLoacation.getLatitude();
                Log.d("GET My Location","lat:"+lat+", lng:"+lng);
            }
        }
        return currentLoacation;
    }

    public void loginBtn_Clicked(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        startActivity(intent);
    }

    public void joinBtn_Clicked(View view){
        Intent intent = new Intent(this, JoinActivity.class);
        startActivityForResult(intent,0);
    }
//    public void naverMapBtn_Clicked(View view){
//        Intent intent = new Intent(this, NaverMapActivity.class);
//        startActivity(intent);
//    }
}