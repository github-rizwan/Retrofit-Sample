package in.rittz.retrofitsample.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.rittz.retrofitsample.API.ApiClient;
import in.rittz.retrofitsample.API.ApiInterface;
import in.rittz.retrofitsample.Adapter.ImageListViewAdapter;
import in.rittz.retrofitsample.Config.ToastConfig;
import in.rittz.retrofitsample.POJO.Product;
import in.rittz.retrofitsample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowEntry2Activity extends AppCompatActivity {

    private RecyclerView listProducts;
    private ArrayList<Product> AllProducts;
    private ApiInterface apiInterface;
    private ImageListViewAdapter adapter;
    private ToastConfig toastConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entry2);

        init();

    }

    public void init()
    {
        listProducts = findViewById(R.id.listView);
        listProducts.setHasFixedSize(true);

        listProducts.setLayoutManager(new LinearLayoutManager(this));

        AllProducts = new ArrayList<Product>();

        adapter = new ImageListViewAdapter(this,AllProducts);
        listProducts.setAdapter(adapter);

        apiInterface =  ApiClient.getApiClient().create(ApiInterface.class);
        toastConfig = new ToastConfig(this);

        GetProductList();

    }

    public void GetProductList()
    {
        Call<List<Product>> call = apiInterface.GetImageProductList();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.isSuccessful()) {
                    AllProducts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    toastConfig.DisplayToast("Server Connection Error");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                toastConfig.DisplayToast("Server Connection Error");
            }
        });
    }
}
