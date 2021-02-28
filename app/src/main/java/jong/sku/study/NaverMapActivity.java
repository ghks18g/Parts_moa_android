package jong.sku.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

public class NaverMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "NaverMapActivity";
    private static final int REQUEST_CODE_LOCATION = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private GeoPoint my_Geo_pt_Katech;

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;

    private LocationManager mLocationManager;

    private InfoWindow mInfoWindow;
    private static List<Oil_Station> list_oil_station;
    private List<LatLng> markersPosition = new ArrayList<LatLng>();
    private List<Marker> activeMarkers = new ArrayList<Marker>();
    private List<String> infoWindowText = new ArrayList<String>();
    private Spinner oil_species;
    List<CommonSpinner> oil_species_item;
    private String oil_code="";
    private String oil_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map);

//        for (int i = 0; i<100; i++){
//            oil_station[i] = new Oil_Station("","","","","");
//        }

        oil_species = (Spinner) findViewById(R.id.oil_species);
        oil_species_item = new ArrayList<CommonSpinner>();

        oil_species_item.add(new CommonSpinner("B027","휘발유"));
        oil_species_item.add(new CommonSpinner("D047","경유"));

        ArrayAdapter<CommonSpinner> oil_species_Adapter = new ArrayAdapter<CommonSpinner>(getApplicationContext(),android.R.layout.simple_spinner_item, oil_species_item);
        oil_species_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        oil_species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CommonSpinner oilList = (CommonSpinner)parent.getItemAtPosition(position);
                oil_code = oilList.getKey();
                oil_name = oilList.getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        oil_species.setAdapter(oil_species_Adapter);

        // 현재 위치 좌표 정보 받아오기.
        mLocationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            double lat = userLocation.getLatitude();
            double lng = userLocation.getLongitude();
            System.out.println("현재 위치 값은 : " +lng+ ", " + lat);

            System.out.println("///////////////////////////");
            GeoPoint in_point = new GeoPoint(lng,lat);
            GeoPoint katec_point=GeoTrans.convert(GeoTrans.GEO,GeoTrans.KATEC,in_point);
            System.out.println(katec_point.getX()+"/"+katec_point.getY());
            my_Geo_pt_Katech = katec_point;
        }

        //지도 객체 생성
        FragmentManager fm = getSupportFragmentManager();
        MapFragment  mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this); // 비동기로 onMapReady 콜백 메소드 호출
                                                // onMapReady 에서 NaverMap 객체를 받음
        //위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);


    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d( TAG, "onMapReady");

        //NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

        //권한 확인, 결과는 onRequestPermissionResult 콜백 메소드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한 획득 여부 확인

        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }

        }
    }


    private Location getMyLocation() {
        Location currentLoacation = null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("//// 사용 권한 요청 ");
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation();
        } else {
            System.out.println(" // 권한 요청이 안될 경우 수동으로 구하기");
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLoacation = mLocationManager.getLastKnownLocation(locationProvider);
            if (currentLoacation != null) {
                double lng = currentLoacation.getLongitude();
                double lat = currentLoacation.getLatitude();
                Log.d("GET My Location", "lat:" + lat + ", lng:" + lng);
            }
        }
        return currentLoacation;
    }

    public void onClickBack(View view){
        finish();
    }

    public void onClick_5km_around(View view){
        System.out.println("///////////");
        System.out.println("현재 위치 주변 5km 이내의 주유소 정보를 수집합니다. ");

        String url = "http://www.opinet.co.kr/api/aroundAll.do?code=F859200928&x="+my_Geo_pt_Katech.getX()
                +"&y="+my_Geo_pt_Katech.getY()+"&radius=5000&sort=1&prodcd="+oil_code+"&out=xml";
        System.out.println(url);


//        Marker marker = new Marker();
//        marker.setPosition( new LatLng(37.5290,126.7885));
//        marker.setMap(mNaverMap);
        OpenAPI opinetApi = new OpenAPI(url);
        try {
            list_oil_station = opinetApi.execute().get();
            Log.d("NAVER_MAP","oil_station 값 얻기 성공.");

        }catch (Exception e){
            e.printStackTrace();
        }

        for (final Oil_Station oil_station : list_oil_station){
            System.out.println("------------------[리턴값 확인]---------------------");
            System.out.println(oil_station.getStation_name());
            System.out.println(oil_station.getPrice());
            System.out.println(oil_station.getUser_to_station());
            System.out.println(Double.parseDouble(oil_station.getGis_x()));
            System.out.println(Double.parseDouble(oil_station.getGis_y()));
            System.out.println(oil_station.getBrand());
            System.out.println(oil_station.getStation_code());

            GeoPoint oil_station_katech = new GeoPoint(Double.parseDouble(oil_station.getGis_x()),Double.parseDouble(oil_station.getGis_y()));
            GeoPoint oil_station_geo = GeoTrans.convert(GeoTrans.KATEC,GeoTrans.GEO,oil_station_katech);
            Log.d("Oil_station_list",oil_station_geo.getX()+"/"+oil_station_geo.getY());
            markersPosition.add(new LatLng(oil_station_geo.getY(),oil_station_geo.getX()));

            infoWindowText.add(oil_station.getStation_name()+":"+oil_station.getPrice());

            Marker marker = new Marker();
            marker.setIcon(OverlayImage.fromResource(R.mipmap.station_marker));
            marker.setPosition(new LatLng(oil_station_geo.getY(),oil_station_geo.getX()));
            marker.setMap(mNaverMap);
            mInfoWindow = new InfoWindow();
//            mInfoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplication()) {
//                @NonNull
//                @Override
//                public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                    return oil_station.getBrand()+"\n"+oil_station.getStation_name()+":"+oil_station.getPrice();
//                }
//            });
            mInfoWindow.setAlpha(0.85f);
            mInfoWindow.setAdapter(new InfoWindow.DefaultViewAdapter(getApplication()) {
                @NonNull
                @Override
                protected View getContentView(@NonNull InfoWindow infoWindow) {
                    View view = View.inflate(getApplication(),R.layout.oil_station_info,null);
                    switch (oil_station.getBrand()){
                        case "SKE":
                            ((ImageView) view.findViewById(R.id.brand_ic)).setImageResource(R.mipmap.ic_sk);
                            break;
                        case "SOL":
                            ((ImageView) view.findViewById(R.id.brand_ic)).setImageResource(R.mipmap.ic_soil);
                            break;
                        case "HDO":
                            ((ImageView) view.findViewById(R.id.brand_ic)).setImageResource(R.mipmap.ic_hyundai);
                            break;
                        case "GSC":
                            ((ImageView) view.findViewById(R.id.brand_ic)).setImageResource(R.mipmap.ic_gs);
                            break;
                        default:
                            ((ImageView) view.findViewById(R.id.brand_ic)).setImageResource(R.mipmap.ic_default);
                            break;
                    }
                    if (oil_code.equals("B027")) {
                        ((ImageView) view.findViewById(R.id.oil_species)).setImageResource(R.mipmap.ic_gasolin);
                    }else if (oil_code.equals("D047")){
                        ((ImageView)view.findViewById(R.id.oil_species)).setImageResource(R.mipmap.ic_diesel);
                    }
                    ((TextView)view.findViewById(R.id.station_name)).setText(oil_station.getStation_name());
                    ((TextView)view.findViewById(R.id.price)).setText(oil_station.getPrice());

                    return view;
                }
            });
           mInfoWindow.open(marker);
        }
//        for (LatLng markerPosition : markersPosition){
//            Marker marker = new Marker();
//            marker.setPosition(markerPosition);
//            marker.setMap(mNaverMap);
//            activeMarkers.add(marker);
//        }


    }
}