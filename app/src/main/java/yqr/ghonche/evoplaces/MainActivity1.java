package yqr.ghonche.evoplaces;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.functions.Action1;


public class MainActivity1 extends AppCompatActivity {


    Button SaveBttn;
    Button GetCordinateBttn;
    EditText name_Edittxt;
    EditText phone_Editxt;
    EditText category_Edittxt;
    EditText address_Edittxt;
    EditText Cordinate_Edittxt;
    LocationServiceManager locationServiceManager;
    CheckBox family_chk;
    CheckBox date_chk;
    CheckBox working_chk;
    CheckBox group_chk;

    public static String Encodedimg;

    //    private int progressValue = 0;
    public DataBaseManager dataBaseManager;


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

        dataBaseManager = new DataBaseManager(MainActivity1.this);

        SaveBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name_Edittxt.getText().toString().equals("") ||
                        address_Edittxt.getText().toString().equals("") ||
                        category_Edittxt.getText().toString().equals("") ||
                        phone_Editxt.getText().toString().equals("")) {


                    Toast.makeText(MainActivity1.this, "fields can not be empty", Toast.LENGTH_LONG).show();

                } else {
                    String suitableFor = "";

                    boolean b = false;
                    if (family_chk.isChecked()) {
                        suitableFor = suitableFor.concat("1");
                        b = true;
                    }
                    if (date_chk.isChecked()) {
                        suitableFor = suitableFor.concat("2");
                        b = true;
                    }
                    if (working_chk.isChecked()) {
                        suitableFor = suitableFor.concat("3");
                        b = true;
                    }
                    if (group_chk.isChecked()) {
                        suitableFor = suitableFor.concat("4");
                        b = true;
                    }


                    if (b) {

                        Yelp yelp = new Yelp
                                (name_Edittxt.getText().toString(),
                                        phone_Editxt.getText().toString(),
                                        Encodedimg,
                                        category_Edittxt.getText().toString(),
                                        address_Edittxt.getText().toString(),
                                        Cordinate_Edittxt.getText().toString(),
                                        suitableFor);

                        dataBaseManager.add_to_yelp_firstProject_DataBase(yelp);

//                        Log.d("DB log", "name:   " + name_Edittxt.getText().toString() + "phone:   " +
//                                phone_Editxt.getText().toString() + "image:  " +
//                                DbBitmapUtility.getBytes(MainActivity2.image) + "category:   " +
//                                category_Edittxt.getText().toString() + "address:   " +
//                                address_Edittxt.getText().toString() + " coordinate:  " +
//                                Cordinate_Edittxt.getText().toString() + "suitable for:   " +
//                                Integer.parseInt(suitableFor));

                        ShowProgress.showProgress(MainActivity1.this, "sending data to database...", 10);

                        phone_Editxt.setText("");
                        name_Edittxt.setText("");
                        category_Edittxt.setText("");
                        address_Edittxt.setText("");
                        Cordinate_Edittxt.setText("");
                        phone_Editxt.setText("");
                        MainActivity2.imageView.setImageResource(R.drawable.galleryicon2);

                        Log.d("*****************", String.valueOf(dataBaseManager.getDataBaseSize()));

                        Toast.makeText(MainActivity1.this, "this place saved", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(MainActivity1.this, "check at least one checkbox", Toast.LENGTH_LONG).show();
                    }
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


                ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(getApplicationContext());

                if (ActivityCompat.checkSelfPermission(MainActivity1.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity1.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationProvider.getLastKnownLocation()
                        .subscribe(new Action1<Location>() {
                            @Override
                            public void call(Location location) {

                                ShowProgress.showProgress(MainActivity1.this,"getting GPS coordinates",20);

                                Cordinate_Edittxt.setText(location.getLatitude()+" , "+location.getLongitude());


                                Geocoder geocoder;
                                List<Address> addresses = null;
                                geocoder = new Geocoder(MainActivity1.this, Locale.getDefault());

                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                String postalCode = addresses.get(0).getPostalCode();
                                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                                String s = addresses.get(0).getCountryName()+" - "+
                                        addresses.get(0).getAddressLine(0)+" - "+
                                        addresses.get(0).getLocality()+" - "+
                                        addresses.get(0).getFeatureName();

                                Toast.makeText(MainActivity1.this, s , Toast.LENGTH_LONG
                                       ).show();

                            }
                        });
            }
        });



    }
}
