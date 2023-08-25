package org.example.cats;

public class Cat {
    private String id;
    private String url;
    private String apiKEY;
    private String image;

    public Cat(String id, String url, String apiKEY, String image) {
        this.id = id;
        this.url = url;
        this.apiKEY = apiKEY;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
