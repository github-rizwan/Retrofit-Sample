package in.rittz.retrofitsample.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

import in.rittz.retrofitsample.API.ApiClient;
import in.rittz.retrofitsample.API.ApiInterface;
import in.rittz.retrofitsample.Config.ToastConfig;
import in.rittz.retrofitsample.POJO.Product;
import in.rittz.retrofitsample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEntryActivity extends AppCompatActivity {

    private EditText editName, editDesc, editPrice;
    private String sname, sdesc, sprice;
    private ApiInterface apiInterface;
    private ToastConfig toastConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        init();
    }

    public void init() {
        editName = findViewById(R.id.editName);
        editDesc = findViewById(R.id.editDesc);
        editPrice = findViewById(R.id.editPrice);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        toastConfig = new ToastConfig(this);
    }

    public void AddProduct(View view) {
        sname = editName.getText().toString().trim();
        sdesc = editDesc.getText().toString().trim();
        sprice = editPrice.getText().toString().trim();

        if (sname.isEmpty()) {
            editName.setError("Enter Product Name");
            return;
        } else if (sdesc.isEmpty()) {
            editDesc.setError("Enter Product Description");
            return;
        } else if (sprice.isEmpty()) {
            editPrice.setError("Enter Product Price");
            return;
        } else
            {

                Call<Product> call = apiInterface.PostProduct(sname, sdesc, sprice);
                call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {

                    if(response.isSuccessful())
                    {
                        toastConfig.DisplayToast(response.body().getMsg());
                    }
                    else
                    {
                        toastConfig.DisplayToast("Server Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                    toastConfig.DisplayToast("Server Connection Error");
                }
            });

        }
    }

}
