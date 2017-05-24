package yqr.ghonche.evoplaces;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
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
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class MainActivity2 extends AppCompatActivity implements IPickResult {

    public static ImageView imageView=null;
    private Button next_bttn;
    private Button Yes_clearDB_dialog_bttn;
    private Button No_clearDB_dialog_bttn;

    private Button Ok_sendData_dialog_bttn;
    private EditText ip_sendData_dialog_edittxt;
    private EditText port_sendData_dialog_edittxt;


    public static Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.galleryicon2);
        next_bttn = (Button) findViewById(R.id.button);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickSetup pickSetup = new PickSetup();
                pickSetup.setWidth(300).setHeight(300);
                PickImageDialog.build(pickSetup)
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                //TODO: do what you have to...

                                if (r.getError() == null) {
                                    imageView.setImageBitmap(r.getBitmap());
                                    image=r.getBitmap();

                                    //or

                                    imageView.setImageURI(r.getUri());
                                } else {
                                    //Handle possible errors
                                    //TODO: do what you have to do with r.getError();
                                }
                            }
                        }).show(getSupportFragmentManager());
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

    @Override
    public void onPickResult(PickResult r) {
//        if (r.getError() == null) {
//            imageView.setImageBitmap(r.getBitmap());
//
//            //or
//
//            imageView.setImageURI(r.getUri());
//        } else {
//            //Handle possible errors
//            //TODO: do what you have to do with r.getError();
//        }

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final List<Intent> cameraIntents = new ArrayList<Intent>();
//                final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                final PackageManager packageManager = getPackageManager();
//                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//                for(ResolveInfo res : listCam) {
//                    final String packageName = res.activityInfo.packageName;
//                    final Intent intent = new Intent(captureIntent);
//                    intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//                    intent.setPackage(packageName);
//                    intent.putExtra(MediaStore.MEDIA_IGNORE_FILENAME, ".nomedia");
//
//                    cameraIntents.add(intent);
//
//                }
//
//                // Filesystem.
//                final Intent galleryIntent = new Intent();
//                galleryIntent.setType("image/*");
//                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//
//                // Chooser of filesystem options.
//                final Intent chooserIntent = Intent.createChooser(galleryIntent,
//                        "take a photo");
//
//                // Add the camera options.
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
//                        cameraIntents.toArray(new Parcelable[]{}));
//                startActivityForResult(chooserIntent, 1);
//
//            }
//        });
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

                            //send data...

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

                        MainActivity1.dataBaseManager.deleteAll();

                        ShowProgress.showProgress(MainActivity2.this,"deleting database...",20);

                        clearDBdialog.dismiss();

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








}//class
