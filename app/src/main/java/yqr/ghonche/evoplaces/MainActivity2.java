package yqr.ghonche.evoplaces;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity implements IPickResult {

    public static ImageView imageView=null;
    private Button next_bttn;
    private Button Yes_clearDB_dialog_bttn;
    private Button No_clearDB_dialog_bttn;

    private Button Ok_sendData_dialog_bttn;
    private EditText ip_sendData_dialog_edittxt;
    private EditText port_sendData_dialog_edittxt;


    //DataBase Manager
    DataBaseManager dbManager;

    public static Bitmap image;
    private String userChoosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        //DataBaseManager
        dbManager = new DataBaseManager(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.galleryicon2);
        next_bttn = (Button) findViewById(R.id.button);

        ActivityCompat.requestPermissions(MainActivity2.this,
                new String[]{android.Manifest.permission.CAMERA},
                1);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity2.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);


                selectImage();
            }
        });



        next_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageView.equals(null)){

                    Toast.makeText(MainActivity2.this,
                            "That is nt fair. tis should be MOSTAFA [-(",
                            Toast.LENGTH_LONG).show();
                }

                else {

                    Intent intent=new Intent(MainActivity2.this,MainActivity1.class);
                    startActivity(intent);
                }



            }
        });

}// onCreate
    //---------------

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(MainActivity2.this);
                if (items[item].equals("Take Photo")) {

                    ActivityCompat.requestPermissions(MainActivity2.this,
                            new String[]{android.Manifest.permission.CAMERA},
                            1);


                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
                onSelectFromGalleryResult(data);
            else if (requestCode == 0)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        image=bm;
        imageView.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        MainActivity1.Encodedimg= Base64.encodeToString(bytes.toByteArray(),Base64.DEFAULT);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image=thumbnail;
        imageView.setImageBitmap(thumbnail);
    }


    //------------------

    @Override
    public void onPickResult(PickResult r) {
    }//onpickresult




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu_layout , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.send_data_option_id: {


                final Dialog sendDataDialog = new Dialog(this);
                sendDataDialog.setContentView(R.layout.senddata_dialog_layout);
                Ok_sendData_dialog_bttn = (Button) sendDataDialog.findViewById(R.id.OK_senddata_dialog_bttn_id);
                ip_sendData_dialog_edittxt = (EditText)
                        sendDataDialog.findViewById(R.id.ip_senddata_dialog_edittxt_id);
                port_sendData_dialog_edittxt = (EditText)
                        sendDataDialog.findViewById(R.id.port_senddata_dialog_edittxt_id);

                Ok_sendData_dialog_bttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(ip_sendData_dialog_edittxt.getText().toString().equals(null)||
                                port_sendData_dialog_edittxt.getText().toString().equals(null)) {

                            Animation anim = AnimationUtils.loadAnimation(MainActivity2.this,
                                    R.anim.animation);
                            ip_sendData_dialog_edittxt.setText("");
                            port_sendData_dialog_edittxt.startAnimation(anim);
                            Vibrator v = (Vibrator) getApplicationContext().getSystemService
                                    (Context.VIBRATOR_SERVICE);
                            v.vibrate(60);

                        }

                        else {
                            String uri = "asd";
                            myTask task = new myTask();
                            task.execute(uri);

                        }

                        sendDataDialog.dismiss();

                    }
                });

                sendDataDialog.show();

                break;
            }// send data

            case R.id.clearDB_option_id: {

                final Dialog clearDBdialog = new Dialog(this);
                clearDBdialog.setContentView(R.layout.cleardb_dialog_layout);
                No_clearDB_dialog_bttn = (Button) clearDBdialog.findViewById(R.id.no_clearDB_dialog_bttn_id);
                Yes_clearDB_dialog_bttn = (Button) clearDBdialog.findViewById(R.id.yes_clearDB_dialog_bttn_id);

                Yes_clearDB_dialog_bttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dbManager.deleteAll();

                        ShowProgress.showProgress(MainActivity2.this,"deleting database...",20);

                        clearDBdialog.dismiss();
                        Log.d("*************", String.valueOf(dbManager.getDataBaseSize()));

                    }
                });

                No_clearDB_dialog_bttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearDBdialog.dismiss();
                    }
                });


                clearDBdialog.show();




                break;
            }// clear db


        }// swich case

        return true;
    } // on option selected







    private class myTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            String uri = params[0];
            int result = dbManager.postDataHttpUrlConnection();
            String line = "result is "+ result;

            return line;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            next_bttn.setText(s);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity2.this,
                            "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



}//class
