package in.rittz.retrofitsample.POJO;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("response")
    private String Response;

    @SerializedName("msg")
    private String Msg;

    @SerializedName("name")
    private String Name;

    @SerializedName("product_id")
    private String Product_ID;

    @SerializedName("description")
    private String Description;

    @SerializedName("price")
    private String Price;

    @SerializedName("image")
    private String Product_Image;

    public String getResponse() {
        return Response;
    }

    public Product(String response, String msg) {
        Response = response;
        Msg = msg;
    }
    public Product() {
      }


    public void setResponse(String response) {
        Response = response;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProduct_Image() {
        return Product_Image;
    }

    public void setProduct_Image(String product_Image) {
        Product_Image = product_Image;
    }
}
