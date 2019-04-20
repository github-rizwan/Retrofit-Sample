package in.rittz.retrofitsample.API;

import java.util.List;

import in.rittz.retrofitsample.POJO.Product;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @POST("add_product.php")
    @FormUrlEncoded
    Call<Product> PostProduct(@Field("name") String name, @Field("desc") String description , @Field("price") String price);

    @GET("get_products.php")
    Call<List<Product>> GetProductList();

    @Multipart
    @POST("add_product1.php")
    Call<Product> PostProduct1(@Part("name") RequestBody name, @Part("desc") RequestBody desc, @Part("price") RequestBody price , @Part MultipartBody.Part file);

    @GET("get_products1.php")
    Call<List<Product>> GetImageProductList();

}
