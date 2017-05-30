package yqr.ghonche.evoplaces;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;

/**
 * Created by user on 5/19/2017.
 */

public class Yelp1CursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public Yelp1CursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Yelp getYelp1(){
        String name = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.NAME));
        String phone = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.PHONE));
        String picture = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.PIC));
        String category = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT));
        String sub_category;
        try{
            sub_category = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT_SUB));
        }catch (Exception e){
            sub_category = "not yet!";
        }
        String address = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.ADDRESS));
        String coordinate_lng = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD_LNG));
        String coordinate_lat = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD_LAT));
        String suitableFor = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.SUIT));

        Yelp current = new Yelp(name, phone, picture, category, sub_category, address, coordinate_lng,coordinate_lat , suitableFor);
        return current;
    }



    public Bundle getRowBundle () {
        String name = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.NAME)) ;
        String coordinate_lng = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD_LNG)) ;
        String coordinate_lat = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD_LAT)) ;
        String picture = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.PIC)) ;
        String address = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.ADDRESS)) ;
        String phone = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.PHONE)) ;
        String category = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT)) ;
        String subCategory = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT_SUB)) ;
        String suitableFor = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.SUIT));

        Bundle data = new Bundle() ;
        data.putString(DataBaseSchema.Yelp1.culs.NAME , name);
        data.putString(DataBaseSchema.Yelp1.culs.PHONE , phone);
        data.putString(DataBaseSchema.Yelp1.culs.ADDRESS , address);
        data.putString(DataBaseSchema.Yelp1.culs.CAT , category);
        data.putString(DataBaseSchema.Yelp1.culs.CAT_SUB , subCategory);
        data.putString(DataBaseSchema.Yelp1.culs.CRD_LNG , coordinate_lng);
        data.putString(DataBaseSchema.Yelp1.culs.CRD_LAT , coordinate_lat);
        data.putString(DataBaseSchema.Yelp1.culs.PIC , picture);
        data.putString(DataBaseSchema.Yelp1.culs.SUIT , suitableFor);

        return data ;
    }

}
