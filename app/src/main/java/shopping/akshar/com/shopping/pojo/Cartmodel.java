package shopping.akshar.com.shopping.pojo;

public class Cartmodel {

    private String cart_id;
    private String token;
    private String product_id;
    private String product_name;
    private String product_price;
    private String cart_imageurl;
    private String cart_date;
    private String cart_qty;
    private String cust_email;

    public Cartmodel() {
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCart_date() {
        return cart_date;
    }

    public void setCart_date(String cart_date) {
        this.cart_date = cart_date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getCart_imageurl() {
        return cart_imageurl;
    }

    public void setCart_imageurl(String cart_imageurl) {
        this.cart_imageurl = cart_imageurl;
    }

    public String getCart_qty() {
        return cart_qty;
    }

    public void setCart_qty(String cart_qty) {
        this.cart_qty = cart_qty;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }
}
