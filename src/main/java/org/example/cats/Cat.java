package org.example.cats;

public class Cat {
    private int id;
    private String url;
    private String apiKEY= "live_WnOrPwahsislSmfF6A9OsWap9Fk8CEwoW5W2w5J2oNVn6bmvKsQEignB6ZVCajfn";
    private String image;

    public Cat(int id, String url, String apiKEY, String image) {
        this.id = id;
        this.url = url;
        this.apiKEY = apiKEY;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKEY() {
        return apiKEY;
    }

    public void setApiKEY(String apiKEY) {
        this.apiKEY = apiKEY;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
