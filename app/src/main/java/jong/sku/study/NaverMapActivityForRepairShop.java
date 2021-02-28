package jong.sku.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

import jong.sku.study.retrofit.RepairShopModel;
import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMapActivityForRepairShop extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "NaverMapForRepairShop";
    private static final int REQUEST_CODE_LOCATION = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private RetrofitInterface retrofitInterface;

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;

    private LocationManager mLocationManager;

    private InfoWindow mInfoWindow;

    private ArrayList<RepairShopModel> repairShopList = new ArrayList<RepairShopModel>();
    private List<LatLng> markersPosition = new ArrayList<LatLng>();
    private List<Marker> activeMarkers = new ArrayList<Marker>();
    private List<String> infoWindowText = new ArrayList<String>();
    private List<Marker> markerList = new ArrayList<>();

    private double lat;
    private double lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map_for_repair_shop);

        retrofitInterface = RetrofitUtility.getRetrofitInterface();

        mLocationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            lat = userLocation.getLatitude();
            lng = userLocation.getLongitude();
            System.out.println("현재 위치 값은 : " + lng + ", " + lat);

        }
        //지도 객체 생성
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
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
        Log.d(TAG, "onMapReady");

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

    public void repairShop_around(View view){
        System.out.println("현재 위치 주변 1km 이내의 정비업체 정보를 수집합니다. ");
        Double latitude = new Double(lat);
        Double longitude = new Double(lng);

        retrofitInterface.load_repair_shop(latitude.toString(),longitude.toString()).enqueue(new Callback<ArrayList<RepairShopModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RepairShopModel>> call, Response<ArrayList<RepairShopModel>> response) {
                if (response.isSuccessful()){
                    repairShopList = response.body();
                    if (repairShopList != null){
                        for( final RepairShopModel repairShopModel : repairShopList){
                            LatLng shopLatLng = new LatLng(new Double(repairShopModel.getLat()),new Double(repairShopModel.getLng()));
                            markersPosition.add(shopLatLng);
                            Marker marker = new Marker();
                            marker.setIcon(OverlayImage.fromResource(R.mipmap.auto_repair));
                            marker.setPosition(shopLatLng);
                            marker.setMap(mNaverMap);

                            marker.setOnClickListener(new Overlay.OnClickListener() {
                                @Override
                                public boolean onClick(@NonNull Overlay overlay) {
                                    if (overlay instanceof Marker){
                                        Marker marker = (Marker) overlay;
                                        if(marker.getInfoWindow() != null){
                                            mInfoWindow.close();
                                        }else {
                                            mInfoWindow = new InfoWindow();
                                            mInfoWindow.setAlpha(0.85f);
                                            mInfoWindow.setZIndex(10);
                                            mInfoWindow.setAdapter(new PointAdapter(getApplicationContext(),repairShopModel));
                                            mInfoWindow.setOnClickListener(new Overlay.OnClickListener() {
                                                @Override
                                                public boolean onClick(@NonNull Overlay overlay) {
                                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+repairShopModel.getPhone()));
                                                    startActivity(intent);
                                                    return false;
                                                }
                                            });
                                            mInfoWindow.open(marker);
                                        }
                                        return true;
                                    }
                                    return false;
                                }
                            });
//                            mInfoWindow.open(marker);

                        }
//                        for (int i = 0; i < repairShopList.size(); i++){
//                            String name =repairShopList.get(i).getName();
//                            String rdnmadr = repairShopList.get(i).getRdnmadr();
//                            String lnmadr = repairShopList.get(i).getLnmadr();
//                            String phone = repairShopList.get(i).getPhone();
//                            String opentime = repairShopList.get(i).getOpenTime();
//                            String closetime = repairShopList.get(i).getCloseTime();
//                            Double lat = new Double(repairShopList.get(i).getLat());
//                            Double lng = new Double(repairShopList.get(i).getLng());

//                            Log.d(TAG,name+"\n"
//                                    +rdnmadr+"\n"
//                                    +lnmadr+'\n'
//                                    +phone+'\n'
//                                    +opentime+'\n'
//                                    +closetime+'\n'
//                                    +lat+" / "+lng);
//                            Log.d(TAG,"--------------------------------");
//                            markersPosition.add(new LatLng(lat,lng));
//                            infoWindowText.add(name+'\n'+phone+'\n'+opentime+'~'+closetime);

//                            Marker marker = new Marker();
//                            marker.setIcon(OverlayImage.fromResource(R.mipmap.auto_repair));
//                            marker.setPosition(new LatLng(lat,lng));
//                            marker.setMap(mNaverMap);
//                            markerList.add(marker);
//
//                            mInfoWindow = new InfoWindow();
//                            mInfoWindow.setAlpha(0.85f);
//                            mInfoWindow.setAdapter( new PointAdapter(getApplication(),repairShopList.get(i)));
//                            mInfoWindow.setAdapter(new InfoWindow.DefaultViewAdapter(getApplication()) {
//                                @NonNull
//                                @Override
//                                protected View getContentView(@NonNull InfoWindow infoWindow) {
//                                    View view = View.inflate(getApplication(), R.layout.repair_shop_info,null);
//                                    ((TextView)view.findViewById(R.id.shop_name)).setText(name);
//                                    ((TextView)view.findViewById(R.id.shop_phone)).setText(phone);
//                                    ((TextView)view.findViewById(R.id.shop_opentime)).setText(opentime);
//                                    ((TextView)view.findViewById(R.id.shop_closetime)).setText(closetime);
//                                    ((TextView)view.findViewById(R.id.shop_raddr)).setText(rdnmadr);
//
//                                    ((Button)view.findViewById(R.id.btn_call)).setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Toast.makeText(getContext(),"정비예약하기"+name,Toast.LENGTH_LONG).show();
//                                        }
//                                    });
//
//                                    return view;
//                                }
//                            });

//                            marker.setOnClickListener(new Overlay.OnClickListener() {
//                                @Override
//                                public boolean onClick(@NonNull Overlay overlay) {
//                                    if (overlay instanceof Marker) {
//                                        if (marker.getInfoWindow() != null) {
//                                            mInfoWindow.close();
//                                        } else {
//                                            mInfoWindow.open(marker);
//                                        }
//                                        return true;
//                                    }
//                                    return false;
//                                }
//                            });
//                            mInfoWindow.open(marker);
//                        }
                    } else {
                        Log.d(TAG,"Repair Shop List is Null..");
                    }
                } else {
                    int statusCode = response.code();
                    Log.d(TAG,String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RepairShopModel>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }
}