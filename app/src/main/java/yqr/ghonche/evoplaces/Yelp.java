package yqr.ghonche.evoplaces;

/**
 * Created by user on 5/19/2017.
 */
public class Yelp {
    private String name;
    private String phone;
    private String picture;
    private String category;
    private String subCategory;
    private String address;
    private String coordinate_lat;
    private String coordinate_lng;
    private String suitableFor;

    public Yelp(String name, String phone, String picture, String category, String subCategory,
                String address, String coordinate_lng, String coordinate_lat , String suitableFor){
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.category = category;
        this.subCategory = subCategory;
        this.address = address;
        this.coordinate_lat = coordinate_lat;
        this.coordinate_lng=coordinate_lng;
        this.suitableFor = suitableFor;

    }

    //suitableFor
    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
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
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
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

    //longitude
    public String getCoordinate_lng() {
        return coordinate_lng;
    }

    public void setCoordinate_lng(String coordinate_lng) {
        this.coordinate_lng = coordinate_lng;
    }

    //latitude
    public String getCoordinate_lat() {
        return coordinate_lat;
    }

    public void setCoordinate_lat(String coordinate_lat) {
        this.coordinate_lat = coordinate_lat;
    }

    //subCategory
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String toString(){

        String total = "";

        total += "name =" + this.name + "\n";
        total += "phone =" + this.phone + "\n";
//        total += "picture =" + this.picture + "\n";
        total += "category =" + this.category + "\n";
        total += "subcategory =" + this.subCategory + "\n";
        total += "lat =" + this.coordinate_lat + "\n";
        total += "long =" + this.coordinate_lng + "\n";
        total += "suit =" + this.suitableFor + "\n";
        total += "address =" + this.address + "\n";

        return total;
    }

}
