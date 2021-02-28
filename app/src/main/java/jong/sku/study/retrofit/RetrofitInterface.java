package jong.sku.study.retrofit;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("login")
    Call<PartsUserModel> login(@Field("login_email")String email, @Field("login_password")String password);
    @FormUrlEncoded
    @POST("sign_in")
    Call<PartsUserModel> signIn(@Field("join_username")String username, @Field("join_email")String email, @Field("join_password")String password, @Field("join_carid")String carid, @Field("join_carinfo")String carinfo);
    @FormUrlEncoded
    @POST("join_emailCheck")
    Call<PartsUserModel> email_check(@Field("join_email")String email);

    @Multipart
    @POST("userImageUpload")
    Call<ResponseBody> imgupload(@Part MultipartBody.Part image, @Part("email") RequestBody email);

    @FormUrlEncoded
    @POST("profiledownload")
    Call<ResponseBody> profileImgDown(@Field("email") String email);

    @FormUrlEncoded
    @POST("cardownload")
    Call<ResponseBody> carImgDown(@Field("email") String email);

    @Multipart
    @POST("userUpdateWithImg")
    Call<PartsUserModel> user_updateWithProfileAndCar(@Part("email") RequestBody email, @Part("carinfo") RequestBody carinfo, @Part("carid") RequestBody carid, @Part("password") RequestBody password
            ,@Part MultipartBody.Part profile, @Part MultipartBody.Part car);
    @Multipart
    @POST("userUpdateWithProfile")
    Call<PartsUserModel> user_updateWithProfile(@Part("email") RequestBody email, @Part("carinfo") RequestBody carinfo, @Part("carid") RequestBody carid, @Part("password") RequestBody password
            ,@Part MultipartBody.Part profile);
    @Multipart
    @POST("userUpdateWithCar")
    Call<PartsUserModel> user_updateWithCar(@Part("email") RequestBody email, @Part("carinfo") RequestBody carinfo, @Part("carid") RequestBody carid, @Part("password") RequestBody password
            , @Part MultipartBody.Part car);

    @FormUrlEncoded
    @POST("userUpdate")
    Call<PartsUserModel> user_update(@Field("email") String email, @Field("carinfo") String carinfo, @Field("carid") String carid, @Field("password") String password);

    @FormUrlEncoded
    @POST("loadItem")
    Call<ArrayList<PartsItemModel>> load_item(@Field("carid")String carid);

    @FormUrlEncoded
    @POST("loadItemWithCategory")
    Call<ArrayList<PartsItemModel>> load_item_category(@Field("carid")String carid, @Field("ca_id")String ca_id);

    @FormUrlEncoded
    @POST("loadRepairShop")
    Call<ArrayList<RepairShopModel>> load_repair_shop(@Field("lat")String lat, @Field("lng")String lng);

    @FormUrlEncoded
    @POST("likeItemUpdate")
    Call<ResponseBody> likeUpdate(@Field("user_email")String user_email,@Field("item_url")String item_url);

    @FormUrlEncoded
    @POST("likeItemDelete")
    Call<ResponseBody> likeDelete(@Field("user_email")String user_email,@Field("item_url")String item_url);

    @FormUrlEncoded
    @POST("likeItemCheck")
    Call<ResponseBody> likeCheck(@Field("user_email")String user_email, @Field("item_url")String item_url);

    @FormUrlEncoded
    @POST("likeItemload")
    Call<ArrayList<PartsItemModel>> load_like_item(@Field("user_email")String user_email);
}
