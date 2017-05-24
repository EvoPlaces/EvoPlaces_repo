package yqr.ghonche.evoplaces;

/**
 * Created by user on 5/19/2017.
 */
public class Yelp {
    private String name;
    private String phone;
    private byte [] picture;
    private String category;
    private String address;
    private String coordinate;
    private int suitableFor;

    public int getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(int suitableFor) {
        this.suitableFor = suitableFor;
    }

    public Yelp(String name, String phone, byte[] picture, String category,
                String address, String coordinate , int suitableFor){
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.category = category;
        this.address = address;
        this.coordinate = coordinate;
        this.suitableFor = suitableFor;

    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Picture
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte [] picture) {
        this.picture = picture;
    }

    //Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Coordinate
    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

}
