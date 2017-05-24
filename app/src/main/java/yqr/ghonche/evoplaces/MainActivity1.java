package yqr.ghonche.evoplaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity1 extends AppCompatActivity {


    Button SaveBttn;
    Button GetCordinateBttn;
    EditText name_Edittxt;
    EditText phone_Editxt;
    EditText category_Edittxt;
    EditText address_Edittxt;
    public static EditText Cordinate_Edittxt;
    LocationServiceManager locationServiceManager;
    CheckBox family_chk;
    CheckBox date_chk;
    CheckBox working_chk;
    CheckBox group_chk;

//    private int progressValue = 0;
    public static DataBaseManager dataBaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        date_chk = (CheckBox) findViewById(R.id.suitable_for_dates);
        family_chk = (CheckBox) findViewById(R.id.suitable_for_familiy);
        group_chk = (CheckBox) findViewById(R.id.suitable_for_groups);
        working_chk = (CheckBox) findViewById(R.id.suitable_for_working);

        name_Edittxt = (EditText) findViewById(R.id.name_edittxt_id);
        phone_Editxt = (EditText) findViewById(R.id.phone_edittxt_id);
        category_Edittxt = (EditText) findViewById(R.id.category_edittxt_id);
        address_Edittxt = (EditText) findViewById(R.id.address_edittxt_id);
        Cordinate_Edittxt = (EditText) findViewById(R.id.cordinate_edittxt_id);
        SaveBttn = (Button) findViewById(R.id.save_bttn_id);
        GetCordinateBttn = (Button) findViewById(R.id.getCordinate_bttn_id);
        dataBaseManager = new DataBaseManager(MainActivity1.this);
        locationServiceManager = new LocationServiceManager(getApplicationContext(), MainActivity1.this);

        SaveBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name_Edittxt.getText().toString().equals("") ||
                        address_Edittxt.getText().toString().equals("") ||
                        category_Edittxt.getText().toString().equals("") ||
                phone_Editxt.getText().toString().equals("")) {


                    Toast.makeText(MainActivity1.this, "fields can not be empty", Toast.LENGTH_LONG).show();

                } else {


                    Yelp yelp = new Yelp
                            (name_Edittxt.getText().toString(),
                                    phone_Editxt.getText().toString(),
                                    DbBitmapUtility.getBytes(MainActivity2.image),
                                    category_Edittxt.getText().toString(),
                                    address_Edittxt.getText().toString(),
                                    " ", 0);
                    dataBaseManager.add_to_yelp_firstProject_DataBase(yelp);

                    ShowProgress.showProgress(MainActivity1.this, "sending data to database...", 10);


                }

            }
        });


        GetCordinateBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //----------------------------------------------TRY1------------------------------------
//                    // instantiate the location manager, note you will need to request permissions in your manifest
//                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    // get the last know location from your location manager.
//                    if (ActivityCompat.checkSelfPermission(MainActivity1.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                            != PackageManager.PERMISSION_GRANTED &&
//                            ActivityCompat.checkSelfPermission(MainActivity1.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                                    != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//
//                    Location location = locationManager.
//                            getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        // now get the lat/lon from the location and do something with it.4
//                    Toast.makeText(MainActivity1.this,
//                            location.getLatitude()+"    ___  "+location.getLongitude(), Toast.LENGTH_LONG);
////                        nowDoSomethingWith(location.getLatitude(), location.getLongitude());



                //----------------------------------------TRY2--------------------------------------



//                Intent intent=new Intent(MainActivity1.this, MapsActivity.class);
//
//                startActivity(intent);

//
//                LatLng currentLatLng =
//                        new LatLng(locationServiceManager.getlatitude(),
//                                locationServiceManager.getlongtitude());
//
//                Cordinate_Edittxt.setText(currentLatLng+" ");


                //--------------------------------TRY3-----------------------------------------------





            }


        });




    }
}
