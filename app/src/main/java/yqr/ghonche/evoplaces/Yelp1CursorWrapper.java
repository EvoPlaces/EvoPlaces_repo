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
        byte [] picture = getBlob(getColumnIndex(DataBaseSchema.Yelp1.culs.PIC));
        String category = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT));
        String address = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.ADDRESS));
        String coordinate = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD));

        Yelp current = new Yelp(name, phone, picture, category, address, coordinate);
        return current;
    }



    public Bundle getRowBundle () {
        String name = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.NAME)) ;
        String coordinate = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CRD)) ;
        byte [] picture = getBlob(getColumnIndex(DataBaseSchema.Yelp1.culs.PIC)) ;
        String address = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.ADDRESS)) ;
        String phone = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.PHONE)) ;
         String category = getString(getColumnIndex(DataBaseSchema.Yelp1.culs.CAT)) ;

        Bundle data = new Bundle() ;
        data.putString(DataBaseSchema.Yelp1.culs.NAME , name);
        data.putString(DataBaseSchema.Yelp1.culs.PHONE , phone);
        data.putString(DataBaseSchema.Yelp1.culs.ADDRESS , address);
        data.putString(DataBaseSchema.Yelp1.culs.CAT , category);
        data.putString(DataBaseSchema.Yelp1.culs.CRD , coordinate);
        data.putByteArray(DataBaseSchema.Yelp1.culs.PIC , picture);

        return data ;
    }

}
