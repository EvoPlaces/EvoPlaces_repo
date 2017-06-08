package yqr.ghonche.evoplaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by user on 5/19/2017.
 */

public class DataBaseManager {

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public DataBaseManager(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    //adding a yelp to the local DataBase
    public void add_to_yelp_firstProject_DataBase(Yelp ylp) {
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
        values.put(DataBaseSchema.Yelp1.culs.CAT_SUB, ylp.getSubCategory());
        values.put(DataBaseSchema.Yelp1.culs.ADDRESS, ylp.getAddress());
        values.put(DataBaseSchema.Yelp1.culs.CRD_LNG, ylp.getCoordinate_lng());
        values.put(DataBaseSchema.Yelp1.culs.CRD_LAT, ylp.getCoordinate_lat());
        values.put(DataBaseSchema.Yelp1.culs.SUIT, ylp.getSuitableFor());


        return values;
    }

    private Yelp1CursorWrapper queryYelp1() {
        Cursor cursor = mSQLiteDatabase.query(DataBaseSchema.Yelp1.NAME
                , null, null, null, null, null, null);
        return new Yelp1CursorWrapper(cursor);
    }

    //number of rows in the local DataBase
    public int getDataBaseSize() {
        ArrayList<Yelp> yelps = new ArrayList<>();
        Yelp1CursorWrapper cursor = queryYelp1();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Yelp ylp = cursor.getYelp1();
            yelps.add(ylp);
            cursor.moveToNext();
        }

        return yelps.size();

    }

    public void deleteAll() {
        mSQLiteDatabase.delete(DataBaseSchema.Yelp1.NAME, null, null);
    }


    public int postDataHttpUrlConnection() {

        Yelp1CursorWrapper cursor = queryYelp1();



        try {

            MainActivity2.urlConnection.setRequestMethod("POST");
            Log.d("***","***");
            MainActivity2.urlConnection.setDoOutput(true);
            Log.d("****","***");
            MainActivity2.urlConnection.setDoInput(true);
            Log.d("*****","***");
            MainActivity2.urlConnection.setUseCaches(false);
            Log.d("******","***");
            MainActivity2.urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            Log.d("*******","***");
//            MainActivity2.urlConnection.setRequestProperty("Content-Type", "application/json");
            MainActivity2.urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            Log.d("********","***");
            MainActivity2.urlConnection.connect();
            Log.d("*********","***");
            MainActivity2.urlConnection.setConnectTimeout(10000);
            Log.d("**********","***");
            MainActivity2.urlConnection.setReadTimeout(10000);
            Log.d("***********","***");
            /*
            Create JSONObject here
            */
            cursor.moveToFirst();


            JSONArray total = new JSONArray();
            for(Yelp1CursorWrapper cursoR = cursor ; !cursoR.isAfterLast() ; cursoR.moveToNext()){
                JSONObject jsonParam = new JSONObject();

                Yelp yelp = cursor.getYelp1();

                Log.d("yelp json", yelp.toString());

                jsonParam.put("Name" , yelp.getName());
                jsonParam.put("Description", yelp.getAddress());
                jsonParam.put("x_coordinate", yelp.getCoordinate_lng());
                jsonParam.put("y_coordinate", yelp.getCoordinate_lat());
                jsonParam.put("subCategoryName", "test");
//                jsonParam.put("subCategoryName", yelp.getSubCategory());
                jsonParam.put("categoryName", yelp.getCategory());
                jsonParam.put("phoneNumber", yelp.getPhone());
                jsonParam.put("picAddress", yelp.getPicture());

                Log.d("img from db",yelp.getPicture());

                total.put(jsonParam);

            }


//            DataOutputStream wr = new DataOutputStream(MainActivity2.urlConnection.getOutputStream());
            OutputStreamWriter wr = new OutputStreamWriter(
                    MainActivity2.urlConnection.getOutputStream(), "UTF-8");

            wr.write(total.toString());
//            wr.write(a.toString());
            wr.flush();
            wr.close();

            int HttpResult = MainActivity2.urlConnection.getResponseCode();
            Log.d("****************", String.valueOf(HttpResult));
            return HttpResult;

        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        } catch (JSONException e) {
            e.printStackTrace();
            return 3;
        } finally

        {
            if (MainActivity2.urlConnection != null)
                MainActivity2.urlConnection.disconnect();
        }


    }

}
