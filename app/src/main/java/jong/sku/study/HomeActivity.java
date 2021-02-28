package jong.sku.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ramotion.foldingcell.FoldingCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jong.sku.study.retrofit.PartsItemModel;
import jong.sku.study.retrofit.PartsUserModel;
import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context;
    private View nav_header;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Intent intent;
    private PartsUserModel userModel;
    private RetrofitInterface retrofitInterface;
    private ArrayList<PartsItemModel> partsItemList;
    private Spinner makeridSpinner;
    private Spinner lineUpIdSpinner;
    private Spinner yearIdSpinner;
    private SpinnerUpdate spinnerUpdate;
    private Spinner categorySpinner;
    private String carId = "";
    private String categoryId="";
    private ListView listView;
    private ImageView profile, car;
    private static int REQUEST_MYPAGE = 10;

    private RelativeLayout category_layout;
    private boolean categoryToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryToggle = true;
        setContentView(R.layout.activity_home);
        category_layout = (RelativeLayout) findViewById(R.id.category_layout);
        category_layout.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.listView);
        context = getApplicationContext();
        retrofitInterface = RetrofitUtility.getRetrofitInterface();
        intent = getIntent();
        userModel = (PartsUserModel) intent.getSerializableExtra("userModel");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        nav_header = navigationView.getHeaderView(0);


        car = (ImageView)nav_header.findViewById(R.id.carView);
        profile = (ImageView)nav_header.findViewById(R.id.profileView);

        File tempProfile = new File(userModel.getProfileTempPath());

        File tempCar = new File(userModel.getCarTempPath());

//        Log.e("byteforbitmap",userModel.getProfileBitmapBytes().toString());
        byte[] byteProfile = userModel.getProfileBitmapBytes();
        byte[] byteCar = userModel.getCarBitmapBytes();




        Bitmap profileBm = fileToBitmap(tempProfile);
        Bitmap carBm = fileToBitmap(tempCar);
//        if (byteProfile != null) {
//            profileBm = byteArrayToBitmap(byteProfile);
//            Log.e("Bitmap", profileBm.toString());
//        }
//
////        Log.e("byteforbitmap",userModel.getCarBitmapBytes().toString());
//        if (byteCar != null) {
//            carBm = byteArrayToBitmap(byteCar);
//            Log.e("Bitmap", carBm.toString());
//        }
        try {
            if (profileBm != null) {
                profile.setImageBitmap(profileBm);
            }
            if (carBm != null) {
                car.setImageBitmap(carBm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TextView navi_header_text = (TextView) nav_header.findViewById(R.id.navi_head_text1);
        TextView navi_header_text2 = (TextView) nav_header.findViewById(R.id.navi_head_text2);


        navi_header_text.setText(userModel.getUsername()+"님 어서오세요!");
        navi_header_text2.setText(userModel.getEmail());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_myplaces);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();
                Log.d("NavigationView","["+id+"]"+"/ ["+R.id.menu1+"]");

                if (id == R.id.menu1){
                    Toast.makeText(context,title+": 마이페이지 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(context, MypageActivity.class);
                    intent1.putExtra("userModel",userModel);
                    startActivityForResult(intent1,REQUEST_MYPAGE);
                } else if (id == R.id.menu2){
                    Toast.makeText(context,title+": 관심 상품 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(context, LikeItemActivity.class);
                    intent2.putExtra("userModel", userModel);
                    startActivity(intent2);
                } else if (id == R.id.nav_btn_oilStation){
                    Toast.makeText(context,title+": 주변 주유소 정보를 확인합니다.", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(context,NaverMapActivity.class);
                    startActivity(intent3);
                } else if (id == R.id.nav_btn_logout){
                    Toast.makeText(context,title+": 로그아웃 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
                return true;
            }
        });



        // parts load Item

        load_mycar_item(userModel, this);


        makeridSpinner = (Spinner) findViewById(R.id.c_makerid);
        lineUpIdSpinner = (Spinner) findViewById(R.id.c_lineUpid);
        yearIdSpinner = (Spinner) findViewById(R.id.c_yearid);
        categorySpinner = (Spinner) findViewById(R.id.c_category);
        spinnerUpdate = new SpinnerUpdate(makeridSpinner,lineUpIdSpinner,yearIdSpinner);

        spinnerUpdate.Spinner_maker_lineup_year_Update(context);

        List<CommonSpinner> categorySpinnerItems = new ArrayList<CommonSpinner>();
        categorySpinnerItems.add(new CommonSpinner("2010","전면부"));
        categorySpinnerItems.add(new CommonSpinner("2020","측면부"));
        categorySpinnerItems.add(new CommonSpinner("2030","후면부"));
        categorySpinnerItems.add(new CommonSpinner("2040","전장/의장/엔진"));

        ArrayAdapter<CommonSpinner> categoryidAdapter = new ArrayAdapter<CommonSpinner>(context,android.R.layout.simple_spinner_item, categorySpinnerItems);
        categoryidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CommonSpinner categoryidList = (CommonSpinner)parent.getItemAtPosition(position);
                categoryId = categoryidList.getKey();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        categorySpinner.setAdapter(categoryidAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setQueryHint("검색어를 입력하세요");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:{
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
            case R.id.action_search:{
                //TO DO Search Query CODE THIS HEAR
                if (categoryToggle == false){
                    category_layout.setVisibility(View.GONE);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(category_layout,"translationY",-1200);
//            objectAnimator.setDuration(500);
//            objectAnimator.start();
                }else {
                    category_layout.setVisibility(View.VISIBLE);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(category_layout,"translationY",0);
//            objectAnimator.setDuration(500);
//            objectAnimator.start();
                }
                categoryToggle = !categoryToggle;
                return true;
            }
            case R.id.action_mycar:{
                load_mycar_item(userModel, this);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_loadItem(View view){
        String ca_id = categoryId;
        carId = spinnerUpdate.getCarInfoId();
        ListView listView = (ListView) findViewById(R.id.listView);

//        Toast.makeText(context,"carId ="+carId,Toast.LENGTH_LONG).show();
        retrofitInterface.load_item_category(carId,ca_id).enqueue(new Callback<ArrayList<PartsItemModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PartsItemModel>> call, Response<ArrayList<PartsItemModel>> response) {
                if (response.isSuccessful()){
                    partsItemList = response.body();
                    if (partsItemList != null){
                        for (int i = 0; i < partsItemList.size();i++){
                            Log.d("HomeActivity",partsItemList.get(i).getText()+"\n"+partsItemList.get(i).getPrice()+"\n"+partsItemList.get(i).getImg()+'\n'+partsItemList.get(i).getUrl());
                            PartsItemModel item = partsItemList.get(i);

                            item.setRequestBtnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("HomeActivity","item Click event check");
                                    Toast.makeText(context,item.getUrl(),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context,PopupActivity.class);
                                    intent.putExtra("url",item.getUrl());
                                    startActivity(intent);
                                }
                            });

                            item.setLikeBtnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("HomeActivity", "like Click event check");
                                    item.setLikeToggle(!item.isLikeToggle());
                                    if (item.isLikeToggle() == true) {
                                        Toast.makeText(context, "해당상품을 관심상품으로 등록하였습니다.", Toast.LENGTH_LONG).show();
                                        like_item_update(userModel,item);
                                        ImageButton likeBtn = (ImageButton) v.findViewById(R.id.like_item);
                                        likeBtn.setImageResource(android.R.drawable.star_on);
                                    } else {
                                        Toast.makeText(context, "해당상품을 관심상품에서 삭제하였습니다.", Toast.LENGTH_LONG).show();
                                        like_item_delete(userModel,item);
                                        ImageButton likeBtn = (ImageButton) v.findViewById(R.id.like_item);
                                        likeBtn.setImageResource(android.R.drawable.star_off);
                                    }
                                }
                            });

                        }

                        final FoldingCellListAdapter fcPartsAdapter = new FoldingCellListAdapter(context,partsItemList,userModel);

                        listView.setAdapter(fcPartsAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ((FoldingCell)view).toggle(false);
                                //register in adapter that state for selected cell is toggled
                                Log.d("HomeActivity","Item Click in homeActivity previous click");
                                fcPartsAdapter.registerToggle(position);
                                Log.d("HomeActivity","Item Click in homeActivity after click");
//                                Toast.makeText(context,fcPartsAdapter.getItem(position).getText(),Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        Log.d("HomeActivity","Parts Item List is Null..");
                    }
                }else {
                    int statusCode = response.code();
                    Log.d("HomeActivity",String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PartsItemModel>> call, Throwable t) {
                Log.e("HomeActivity", t.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MYPAGE) {
            if (resultCode == RESULT_OK) {
                userModel = (PartsUserModel)data.getSerializableExtra("user");
                profile.setImageBitmap(fileToBitmap(new File(userModel.getProfileTempPath())));
                car.setImageBitmap(fileToBitmap(new File(userModel.getCarTempPath())));

                load_mycar_item(userModel,this);
            }
        }
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

    private void like_item_update(PartsUserModel userModel, PartsItemModel itemModel){
        retrofitInterface.likeUpdate(userModel.getEmail(),itemModel.getUrl()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void like_item_delete(PartsUserModel userModel, PartsItemModel itemModel){
        retrofitInterface.likeDelete(userModel.getEmail(),itemModel.getUrl()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void load_mycar_item(PartsUserModel userModel,Context context){
        retrofitInterface.load_item(userModel.getCarId()).enqueue(new Callback<ArrayList<PartsItemModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PartsItemModel>> call, Response<ArrayList<PartsItemModel>> response) {
                if (response.isSuccessful()){
                    partsItemList = response.body();
                    if (partsItemList != null){
                        for (int i = 0; i < partsItemList.size();i++){
                            Log.d("HomeActivity",partsItemList.get(i).getText()+"\n"+partsItemList.get(i).getPrice()+"\n"+partsItemList.get(i).getImg()+'\n'+partsItemList.get(i).getUrl());
                            PartsItemModel item = partsItemList.get(i);


                            item.setRequestBtnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d("HomeActivity","item Click event check");
                                    Toast.makeText(context,item.getUrl(),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context,PopupActivity.class);
                                    intent.putExtra("url",item.getUrl());
                                    startActivity(intent);
                                }
                            });

                            item.setLikeBtnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("HomeActivity", "like Click event check");
                                    item.setLikeToggle(!item.isLikeToggle());
                                    if (item.isLikeToggle() == true) {
                                        Toast.makeText(context, "해당상품을 관심상품으로 등록하였습니다.", Toast.LENGTH_LONG).show();
                                        like_item_update(userModel,item);
                                        ImageButton likeBtn = (ImageButton) v.findViewById(R.id.like_item);
                                        likeBtn.setImageResource(android.R.drawable.star_on);
                                    } else {
                                        Toast.makeText(context, "해당상품을 관심상품에서 삭제하였습니다.", Toast.LENGTH_LONG).show();
                                        like_item_delete(userModel,item);
                                        ImageButton likeBtn = (ImageButton) v.findViewById(R.id.like_item);
                                        likeBtn.setImageResource(android.R.drawable.star_off);
                                    }
                                }
                            });
                        }

                        final FoldingCellListAdapter fcPartsAdapter = new FoldingCellListAdapter(context,partsItemList,userModel);

                        listView.setAdapter(fcPartsAdapter);

//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                ((FoldingCell)view).toggle(false);
//                                //register in adapter that state for selected cell is toggled
//                                Log.d("HomeActivity","cell item clicked call registerToggle");
//                                fcPartsAdapter.registerToggle(position);
////                                Toast.makeText(context,fcPartsAdapter.getItem(position).getText(),Toast.LENGTH_LONG).show();
//                            }
//                        });
                    } else {
                        Log.d("HomeActivity","Parts Item List is Null..");
                    }
                }else {
                    int statusCode = response.code();
                    Log.d("HomeActivity",String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PartsItemModel>> call, Throwable t) {
                Log.e("HomeActivity", t.toString());
            }
        });
    }
    //    public void onClick_go_shop(){
//
//    }
}