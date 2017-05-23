//package yqr.ghonche.evoplaces;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
//
//    DataBaseManager dbManager;
//
//    private String name;
//    private String phone;
//    private String picture;
//    private String category;
//    private String address;
//    private String coordinate;
//
//    EditText nameEditText;
//    EditText phoneEditText;
//    EditText categoryEditText;
//    EditText addressEditText;
//
//    Button btnSubmit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//
//        dbManager = new DataBaseManager(this);
//
//        nameEditText = (EditText) findViewById(R.id.name_edittxt_id);
//        phoneEditText = (EditText) findViewById(R.id.phone_edittxt_id);
//        categoryEditText = (EditText) findViewById(R.id.category_edittxt_id);
//        addressEditText = (EditText) findViewById(R.id.address_edittxt_id);
//
//        btnSubmit = (Button) findViewById(R.id.save_bttn_id);
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null){
//            if (extras.containsKey("picture")){
//                picture = extras.getString("picture");
//            }
//        }
//
//        btnSubmit.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        if(nameEditText.getText().toString() == null
//                || phoneEditText.getText().toString() == null
//                || categoryEditText.getText().toString() == null
//                || addressEditText.getText().toString() == null){
//            Toast.makeText(this, "Complete the editTexts to submit!", Toast.LENGTH_SHORT).show();
//        }else{
//            name = nameEditText.getText().toString();
//            phone = phoneEditText.getText().toString();
//            address = addressEditText.getText().toString();
//            coordinate = "162.25";
//            Yelp ylp = new Yelp(name, phone, picture, category, address, coordinate);
//
//            dbManager.add_to_yelp_firstProject_DataBase(ylp);
//
//            Toast.makeText(this, "it is added to local DataBase", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
