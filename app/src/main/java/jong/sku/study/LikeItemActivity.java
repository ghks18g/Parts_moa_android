package jong.sku.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import jong.sku.study.retrofit.PartsItemModel;
import jong.sku.study.retrofit.PartsUserModel;
import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeItemActivity extends AppCompatActivity {

    private ListView listView;
    private RetrofitInterface retrofitInterface;
    private Intent intent;
    private PartsUserModel userModel;
    private Context context;
    private ArrayList<PartsItemModel> partsItemList;
    private ImageButton backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_item);

        context = getApplicationContext();
        listView = (ListView) findViewById(R.id.likelistView);
        retrofitInterface = RetrofitUtility.getRetrofitInterface();

        intent = getIntent();
        userModel = (PartsUserModel) intent.getSerializableExtra("userModel");
        backToHome = (ImageButton)findViewById(R.id.btn_back_to_home);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        retrofitInterface.load_like_item(userModel.getEmail()).enqueue(new Callback<ArrayList<PartsItemModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PartsItemModel>> call, Response<ArrayList<PartsItemModel>> response) {
                if (response.isSuccessful()){
                    partsItemList = response.body();
                    if (partsItemList != null){
                        for (int i = 0; i < partsItemList.size();i++){
                            PartsItemModel item = partsItemList.get(i);
                            if(item == null){
                                Log.e("LikeItemActivity","에러발생 포인트.");
                            }else {
                                Log.d("LikeItemActivity",item.getText()+"\n"+item.getPrice()+"\n"+item.getImg()+'\n'+item.getUrl());
                            }


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

            }
        });
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
}