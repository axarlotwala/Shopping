package shopping.akshar.com.shopping.pojo;

public class Reviewmodel {

    private String user_image;
    private String name;
    private String rate;
    private String date;
    private String review_comment;

    public Reviewmodel() {
    }

    public Reviewmodel(String user_image, String name, String rate, String date, String review_comment) {
        this.user_image = user_image;
        this.name = name;
        this.rate = rate;
        this.date = date;
        this.review_comment = review_comment;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview_comment() {
        return review_comment;
    }

    public void setReview_comment(String review_comment) {
        this.review_comment = review_comment;
    }
}
