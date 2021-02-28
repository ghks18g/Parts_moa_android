package jong.sku.study;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import jong.sku.study.retrofit.PartsItemModel;
import jong.sku.study.retrofit.PartsUserModel;
import jong.sku.study.retrofit.RetrofitInterface;
import jong.sku.study.retrofit.RetrofitUtility;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoldingCellListAdapter extends ArrayAdapter<PartsItemModel> {
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultBtnClickListener;
    private PartsUserModel userModel;
    public FoldingCellListAdapter(Context context, List<PartsItemModel> objects, PartsUserModel userModel) {
        super(context, 0, objects);
        this.userModel = userModel;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        // get item for selected view
        PartsItemModel item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;

        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
//            cell.findViewById(R.id.folding_cell)
            // binding view parts to view holder
            viewHolder.partsThumbnail = (ImageView) cell.findViewById(R.id.partsThumbnail);
            viewHolder.partsTitle = (TextView) cell.findViewById(R.id.partsTitle);
            viewHolder.partsPrice = (TextView) cell.findViewById(R.id.partsPrice);
            viewHolder.partsThumbnail_contents = (ImageView) cell.findViewById(R.id.partsThumbnail_contents);
            viewHolder.partsTitle_contents = (TextView) cell.findViewById(R.id.partsTitle_contents);
            viewHolder.partsPrice_contents = (TextView) cell.findViewById(R.id.partsPrice_contents);
//            viewHolder.partsUrl_contents = (TextView) cell.findViewById(R.id.partsUrl_contents);
            viewHolder.goShop = (Button) cell.findViewById(R.id.goShop);
            viewHolder.like_item = (ImageButton) cell.findViewById(R.id.like_item);
            RetrofitInterface retrofitInterface = RetrofitUtility.getRetrofitInterface();
            FoldingCell finalCell = cell;
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalCell.toggle(false);
                }
            });
            retrofitInterface.likeCheck(userModel.getEmail(),item.getUrl()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String resMessage = response.body().string();   // response.body().string() 은 1회성만 가능. 한번 호출 후 다시 호출하면 null 반환.
                        Log.d("LikeCheck: ",resMessage);
                        if (resMessage.equals("\"exist\"")){
                            Log.d("checkLike: ", "\"exist\""+" / "+item.getText());
//                            ImageButton likeBtn = (ImageButton) listView.findViewById(R.id.like_item);
                            viewHolder.like_item.setImageResource(android.R.drawable.star_on);
                            item.setLikeToggle(!item.isLikeToggle());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);

//                Log.d("getView","open, unfoldedIndexes:"+unfoldedIndexes.toString());
            } else {
                cell.fold(true);

//                Log.d("unfoldedIndexes",unfoldedIndexes.toString());
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item){
            return cell;
        }

        // bind data from selected element to view through view holder
        Glide.with(cell)
                .load(item.getImg())
                .fallback(R.drawable.ic_launcher_background)
                .into(viewHolder.partsThumbnail);
        viewHolder.partsTitle.setText(item.getText());
        viewHolder.partsPrice.setText(item.getPrice());

        Glide.with(cell)
                .load(item.getImg())
                .fallback(R.drawable.ic_launcher_background)
                .into(viewHolder.partsThumbnail_contents);
        viewHolder.partsTitle_contents.setText(item.getText());
        viewHolder.partsPrice_contents.setText(item.getPrice());

        if (item.getRequestBtnClickListener() != null){
            viewHolder.goShop.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            viewHolder.goShop.setOnClickListener(defaultBtnClickListener);
        }

        if (item.getLikeBtnClickListener() != null){
            viewHolder.like_item.setOnClickListener(item.getLikeBtnClickListener());
        } else {
            viewHolder.like_item.setOnClickListener(defaultBtnClickListener);
        }
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        Log.d("FoldingCellAdapter","Item Clicked : "+position);
        if (unfoldedIndexes.contains(position)) {
//            Log.d("FoldingCellAdapter","open 상태에서 다시 눌렸으니 close 하자");
            registerFold(position);
        }else {
//            Log.d("FoldingCellAdapter","눌렀으니 open 상태로 전환하자");
            registerUnfold(position);
//            Log.d("unfoldedIndexes",unfoldedIndexes.toString());
        }
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultBtnClickListener() {
        return defaultBtnClickListener;
    }


    public void setDefaultBtnClickListener(View.OnClickListener defaultBtnClickListener) {
        this.defaultBtnClickListener = defaultBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        ImageView partsThumbnail;
        TextView partsTitle;
        TextView partsPrice;
        ImageView partsThumbnail_contents;
        TextView partsTitle_contents;
        TextView partsUrl_contents;
        TextView partsPrice_contents;
        Button goShop;
        ImageButton like_item;
//        WebView mWebView;
    }
}
