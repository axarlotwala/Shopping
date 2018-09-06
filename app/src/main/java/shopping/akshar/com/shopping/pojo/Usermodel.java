package shopping.akshar.com.shopping.pojo;

public class Usermodel {

    private String token;
    private String cust_name;
    private String cust_phoneno;
    private String cust_address;
    private String cust_pincode;
    private String gender;
    private String cust_email;
    private String cust_password;
    private String register_date;
    private String profile_photo;

    public Usermodel() {
    }

    public Usermodel(String token, String cust_name, String cust_phoneno, String cust_address, String cust_pincode, String gender, String cust_email, String cust_password, String register_date, String profile_photo) {
        this.token = token;
        this.cust_name = cust_name;
        this.cust_phoneno = cust_phoneno;
        this.cust_address = cust_address;
        this.cust_pincode = cust_pincode;
        this.gender = gender;
        this.cust_email = cust_email;
        this.cust_password = cust_password;
        this.register_date = register_date;
        this.profile_photo = profile_photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phoneno() {
        return cust_phoneno;
    }

    public void setCust_phoneno(String cust_phoneno) {
        this.cust_phoneno = cust_phoneno;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_pincode() {
        return cust_pincode;
    }

    public void setCust_pincode(String cust_pincode) {
        this.cust_pincode = cust_pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_password() {
        return cust_password;
    }

    public void setCust_password(String cust_password) {
        this.cust_password = cust_password;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }
}
