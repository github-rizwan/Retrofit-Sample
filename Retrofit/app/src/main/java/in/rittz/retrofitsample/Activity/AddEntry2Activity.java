package in.rittz.retrofitsample.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import in.rittz.retrofitsample.API.ApiClient;
import in.rittz.retrofitsample.API.ApiInterface;
import in.rittz.retrofitsample.Config.FilePath;
import in.rittz.retrofitsample.Config.ToastConfig;
import in.rittz.retrofitsample.POJO.Product;
import in.rittz.retrofitsample.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEntry2Activity extends AppCompatActivity {

    private EditText editName, editDesc, editPrice;
    private String sname, sdesc, sprice;
    private ApiInterface apiInterface;
    private ToastConfig toastConfig;
    private int PICK_REQUEST = 1;
    private static final int STORAGE_REQUEST_CODE = 123;
    private Uri filePath;

    private ImageView imageUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry2);

        init();
        RequestPermission();
    }

    public void RequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastConfig.DisplayToast("Permission Granted");
            } else {
                toastConfig.DisplayToast("Permission Denied");
            }
        }
    }

    public void SelectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageUpload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        editName = findViewById(R.id.editName);
        editDesc = findViewById(R.id.editDesc);
        editPrice = findViewById(R.id.editPrice);
        imageUpload = findViewById(R.id.imageUpload);

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
        } else {

            if(filePath != null)
            {
                Call<Product> call = apiInterface.PostProduct1(createPartFromString(sname), createPartFromString(sdesc), createPartFromString(sprice), prepareFilePart("image", filePath));
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

                        toastConfig.DisplayToast("Server Connection Error 404");
                    }
                });
            }
            else
            {
                toastConfig.DisplayToast("Select Image");
            }
        }
    }

    private RequestBody createPartFromString(String string) {
        return RequestBody.create(MultipartBody.FORM,string);
    }

    private MultipartBody.Part prepareFilePart (String partname , Uri filePath)
    {
        File file = FileUtils.getFile(FilePath.getPath(this,filePath));
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath)),file);

        return MultipartBody.Part.createFormData(partname,file.getName(),requestBody);
    }
}
