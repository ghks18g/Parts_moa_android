package jong.sku.study;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.naver.maps.map.overlay.InfoWindow;

import jong.sku.study.retrofit.RepairShopModel;

public class PointAdapter extends InfoWindow.DefaultViewAdapter {

    private final Context mContext;
//    private final ViewGroup mParent;
    private RepairShopModel repairShopModel;

    public PointAdapter(@NonNull Context context, RepairShopModel repairShopModel) {
        super(context);
        this.mContext = context;
//        this.mParent = parent;
        this.repairShopModel = repairShopModel;
    }

    @NonNull
    @Override
    protected View getContentView(@NonNull InfoWindow infoWindow) {
        View view = (View) View.inflate(mContext,R.layout.repair_shop_info, null);
        ((TextView)view.findViewById(R.id.shop_name)).setText(repairShopModel.getName());
        ((TextView)view.findViewById(R.id.shop_raddr)).setText(repairShopModel.getRdnmadr());
        ((TextView)view.findViewById(R.id.shop_lnmadr)).setText(repairShopModel.getLnmadr());
        ((TextView)view.findViewById(R.id.shop_opentime)).setText(repairShopModel.getOpenTime());
        ((TextView)view.findViewById(R.id.shop_closetime)).setText(repairShopModel.getCloseTime());
        ((TextView)view.findViewById(R.id.shop_phone)).setText(repairShopModel.getPhone());

        return view;
    }
}
