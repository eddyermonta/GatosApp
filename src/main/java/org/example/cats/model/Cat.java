package org.example.cats.model;

public class Cat {
    private String id;
    private String image_id;
    private String sub_id;
    private  String created_at;
    private CatImage image;


    public Cat(CatImage image) {
        this.image = image;
    }

    public Cat(String image_id) {
        this.image_id = image_id;
    }

    public Cat() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CatImage getImage() {
        return image;
    }

    public void setImage(CatImage image) {
        this.image = image;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                ", image_id='" + image_id + '\'' +
                ", sub_id='" + sub_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", image=" + image +
                '}';
    }
}
