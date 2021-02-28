package jong.sku.study.retrofit;

public class RetrofitUtility {
    public static final String BASE_URL = "http://10.0.2.2:4000/";

    public static RetrofitInterface getRetrofitInterface(){
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }
}
