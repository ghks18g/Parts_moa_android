package jong.sku.study.retrofit;

import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PartsItemModel implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("ca_id")
    @Expose
    private String ca_id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("liketoggle")
    @Expose
    private boolean likeToggle;

    private View.OnClickListener requestBtnClickListener;

    private View.OnClickListener likeBtnClickListener;

    public String getId() {
        return id;
    }

    public String getCa_id() {
        return ca_id;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public String getPrice() {
        return price;
    }

    public boolean isLikeToggle() {
        return likeToggle;
    }

    public void setLikeToggle(boolean likeToggle) {
        this.likeToggle = likeToggle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCa_id(String ca_id) {
        this.ca_id = ca_id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public View.OnClickListener getLikeBtnClickListener(){return likeBtnClickListener;}

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public void setLikeBtnClickListener(View.OnClickListener likeBtnClickListener){
        this.likeBtnClickListener = likeBtnClickListener;
    }
}
