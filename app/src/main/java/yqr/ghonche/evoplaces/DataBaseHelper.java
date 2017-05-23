package yqr.ghonche.evoplaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 5/19/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    final static int VERSION = 1;
    final static String DATABASE_NAME = "yelp_1.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DataBaseSchema.Yelp1.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DataBaseSchema.Yelp1.culs.NAME + ", " +
                DataBaseSchema.Yelp1.culs.PHONE + ", " +
                DataBaseSchema.Yelp1.culs.PIC + ", " +
                DataBaseSchema.Yelp1.culs.CAT + ", " +
                DataBaseSchema.Yelp1.culs.ADDRESS + ", " +
                DataBaseSchema.Yelp1.culs.CRD +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do here yet !!
    }
}
