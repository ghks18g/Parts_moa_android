package jong.sku.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.naver.maps.map.overlay.InfoWindow;

public class Oil_Adapter extends InfoWindow.DefaultViewAdapter {
    private final Context mContext;
    private final ViewGroup mParent;
    private String oil_station_name;
    private String oil_station_price;
    private String oil_brand;

    public Oil_Adapter(@NonNull Context context,ViewGroup parent,String oil_station_name,String oil_station_price, String oil_brand) {
        super(context);
        mContext = context;
        mParent = parent;
        this.oil_station_name = oil_station_name;
        this.oil_station_price = oil_station_price;
        this.oil_brand = oil_brand;
    }

    @NonNull
    @Override
    protected View getContentView(@NonNull InfoWindow infoWindow) {
        View view = (View) LayoutInflater.from(mContext).inflate(R.layout.oil_station_info,mParent,false);
        TextView station_name = (TextView)view.findViewById(R.id.station_name);
        TextView price = (TextView)view.findViewById(R.id.price);
        ImageView brand_ic = (ImageView) view.findViewById(R.id.brand_ic);

        station_name.setText(oil_station_name);
        price.setText(oil_station_price);
        switch (oil_brand){
            case "SKE":
                brand_ic.setImageResource(R.mipmap.ic_sk);
                break;
            case "SOL":
                brand_ic.setImageResource(R.mipmap.ic_soil);
                break;
            case "HDO":
                brand_ic.setImageResource(R.mipmap.ic_hyundai);
                break;
            case "GSC":
                brand_ic.setImageResource(R.mipmap.ic_gs);
                break;
            default:
                brand_ic.setImageResource(R.mipmap.ic_default);
                break;
        }
        return view;
    }
}
