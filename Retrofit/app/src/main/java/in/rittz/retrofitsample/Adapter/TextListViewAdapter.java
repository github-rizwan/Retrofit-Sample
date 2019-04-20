package in.rittz.retrofitsample.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.rittz.retrofitsample.Activity.ShowEntryActivity;
import in.rittz.retrofitsample.Config.ToastConfig;
import in.rittz.retrofitsample.POJO.Product;
import in.rittz.retrofitsample.R;

public class TextListViewAdapter extends RecyclerView.Adapter<TextListViewAdapter.MyViewHolder> {

    private Context context;
    private List<Product> productList;
    public TextListViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_item_list,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Product product = productList.get(i);

        myViewHolder.textName.setText(product.getName());
        myViewHolder.textDesc.setText(product.getDescription());
        myViewHolder.textPrice.setText(product.getPrice());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastConfig toastConfig = new ToastConfig(context);

                toastConfig.DisplayToast(product.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView textName,textDesc,textPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textDesc = itemView.findViewById(R.id.textDesc);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}
