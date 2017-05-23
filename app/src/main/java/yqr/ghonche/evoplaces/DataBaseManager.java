package yqr.ghonche.evoplaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by user on 5/19/2017.
 */

public class DataBaseManager {

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public DataBaseManager(Context context){
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    //adding a yelp to the local DataBase
    public void add_to_yelp_firstProject_DataBase(Yelp ylp){
        ContentValues values = getYelpValues(ylp);
        Yelp1CursorWrapper cursor = queryYelp1();

        mSQLiteDatabase.insert(DataBaseSchema.Yelp1.NAME, null, values);
    }

    private ContentValues getYelpValues(Yelp ylp) {
        ContentValues values = new ContentValues();

        values.put(DataBaseSchema.Yelp1.culs.NAME, ylp.getName());
        values.put(DataBaseSchema.Yelp1.culs.PHONE, ylp.getPhone());
        values.put(DataBaseSchema.Yelp1.culs.PIC, ylp.getPicture());
        values.put(DataBaseSchema.Yelp1.culs.CAT, ylp.getCategory());
        values.put(DataBaseSchema.Yelp1.culs.ADDRESS, ylp.getAddress());
        values.put(DataBaseSchema.Yelp1.culs.CRD, ylp.getCoordinate());


        return values;
    }

    private Yelp1CursorWrapper queryYelp1() {
        Cursor cursor = mSQLiteDatabase.query(DataBaseSchema.Yelp1.NAME
        , null, null, null, null, null, null);
        return new Yelp1CursorWrapper(cursor);
    }

    //number of rows in the local DataBase
    public int getDataBaseSize(){
        ArrayList<Yelp> yelps = new ArrayList<>();
        Yelp1CursorWrapper cursor = queryYelp1();

        cursor.moveToFirst();

        while(! cursor.isAfterLast()){
            Yelp ylp = cursor.getYelp1();
            yelps.add(ylp);
            cursor.moveToNext();
        }

        return yelps.size();

    }

    public void deleteAll(){
        mSQLiteDatabase.delete(DataBaseSchema.Yelp1.NAME, null, null);
    }




    public String getDBrowinJson() {
        String row = new String("\"[");

        Yelp1CursorWrapper cursor = queryYelp1();

        Bundle bundle ;

        try {
            cursor.moveToFirst();
            bundle = cursor.getRowBundle();

            row = row.concat(String.format( "{ \"%s\" : \"%s\" , \"%s\" : \"%s\" " +
                    ", \"%s\" : \"%s\" , \"%s\" : \"%s\" \"%s\" : \"%s\" } ",
                    "Name", bundle.get(DataBaseSchema.Yelp1.culs.NAME),
                    "Address"   , bundle.get(DataBaseSchema.Yelp1.culs.ADDRESS),
                    "Coordinate"     , bundle.get(DataBaseSchema.Yelp1.culs.CRD),
                    "category" , bundle.get(DataBaseSchema.Yelp1.culs.CAT),
                    "Picture"    ,bundle.get(DataBaseSchema.Yelp1.culs.PIC))
            );

        } finally {
            cursor.close();
        }
        row = row.concat("]\"");

//        mDatabase.delete(driverStateTable.NAME ,
// cursor.getTaxiState().getString(Constant.DB_key_Longitude)
// + "=" + temp.getString(Constant.DB_key_Longitude) , null) ;

        return row ;
    }



}
