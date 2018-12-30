package shopping.akshar.com.shopping.pojo;

public class Graidmodel {

    private String url;
    private String name;

    public Graidmodel() {
    }

    public Graidmodel(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
