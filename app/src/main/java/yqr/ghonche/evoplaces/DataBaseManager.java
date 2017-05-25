package yqr.ghonche.evoplaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 5/19/2017.
 */

public class DataBaseManager {

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    HttpURLConnection urlConnection;
    String uri = "http://192.168.202.1:3000/";

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
        values.put(DataBaseSchema.Yelp1.culs.SUIT, ylp.getSuitableFor());


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




//    public String getDBrowinJson() {
//        String row = new String("\"[");
//
//        Yelp1CursorWrapper cursor = queryYelp1();
//
//        Bundle bundle ;
//
//        try {
//            cursor.moveToFirst();
//            bundle = cursor.getRowBundle();
//
//            row = row.concat(String.format( "{ \"%s\" : \"%s\" , \"%s\" : \"%s\" " +
//                    ", \"%s\" : \"%s\" , \"%s\" : \"%s\" \"%s\" : \"%s\" } ",
//                    "Name", bundle.get(DataBaseSchema.Yelp1.culs.NAME),
//                    "Address"   , bundle.get(DataBaseSchema.Yelp1.culs.ADDRESS),
//                    "Coordinate"     , bundle.get(DataBaseSchema.Yelp1.culs.CRD),
//                    "category" , bundle.get(DataBaseSchema.Yelp1.culs.CAT),
//                    "Picture"    ,bundle.get(DataBaseSchema.Yelp1.culs.PIC),
//                    "SuitableFor"    ,bundle.get(DataBaseSchema.Yelp1.culs.SUIT)
//                    )
//            );
//
//        } finally {
//            cursor.close();
//        }
//        row = row.concat("]\"");
//
////        mDatabase.delete(driverStateTable.NAME ,
//// cursor.getTaxiState().getString(Constant.DB_key_Longitude)
//// + "=" + temp.getString(Constant.DB_key_Longitude) , null) ;
//
//        return row ;
//    }


    public  int postDataHttpUrlConnection() {
        int k = 0;
//        for (k = 0; k < this.getDataBaseSize() ; k++){//for loop
            urlConnection=null;
            try {
                URL url = new URL(uri);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestProperty("Host", "android.schoolportal.gr");

                urlConnection.connect();
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);

                //
                //Create JSONObject here
                //injash bayad khoonehaye database ro entesab bdid dg
                //
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("ID", "25");
                jsonParam.put("description", "Real");
                jsonParam.put("enable", "true");

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(jsonParam.toString());
                Log.d("********", jsonParam.toString());
                wr.flush();
                wr.close();


                int HttpResult = urlConnection.getResponseCode();
                Log.d("******************", String.valueOf(HttpResult));
                return 1;

//            }//for loop

            }catch (IOException e) {
                e.printStackTrace();
                return 1;
            } catch (JSONException e) {
                e.printStackTrace();
            return 1;
            } finally

    {
        if (urlConnection != null)
            urlConnection.disconnect();
    }


    }

}
