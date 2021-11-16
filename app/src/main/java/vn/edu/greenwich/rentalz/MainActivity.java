package vn.edu.greenwich.rentalz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinProperty = findViewById(R.id.spinnerPropety);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> propertyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item , getResources().getStringArray(R.array.property_dropdown));
        propertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        spinProperty.setAdapter(propertyAdapter);

        Spinner spinFurniture = findViewById(R.id.spinnerFumiture);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> furnitureAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item , getResources().getStringArray(R.array.furniture_dropdown));
        furnitureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        spinFurniture.setAdapter(furnitureAdapter);

        Button btnPost = findViewById((R.id.btnPost));

        btnPost.setOnClickListener(postRentalInfo);
    }

    private View.OnClickListener postRentalInfo = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            Boolean isValid = true;

            TextView tvPrice = findViewById(R.id.editTextPrice);
            TextView tvAddress = findViewById(R.id.editTextAddress);
            Spinner spProperty = findViewById(R.id.spinnerPropety);
            TextView tvBedroom = findViewById(R.id.editTextBed);
            TextView tvBathroom = findViewById(R.id.editTextBath);
            Spinner spFurniture = findViewById(R.id.spinnerFumiture);
            TextView tvNote = findViewById(R.id.editTextNote);
            TextView tvPoster = findViewById(R.id.editTextPoster);

            String price = tvPrice.getText().toString();
            int intPrice = 0;
            String address = tvAddress.getText().toString();
            String property = spProperty.getSelectedItem().toString();
            String bed = tvBedroom.getText().toString();
            int intBed = 0;
            String bath = tvBathroom.getText().toString();
            int intBath = 0;
            String furniture = spFurniture.getSelectedItem().toString();
            String note = tvNote.getText().toString();
            String poster = tvPoster.getText().toString();
            String error = "";

            TextView priceError = findViewById(R.id.priceError);
            TextView addressError = findViewById(R.id.addressError);
            TextView propError = findViewById(R.id.propError);
            TextView numError = findViewById(R.id.numError);
            TextView posterError = findViewById(R.id.posterError);

            if(TextUtils.isEmpty(price)){
                priceError.setText("* Must input to The price field");
                isValid = false;
            }else{
                intPrice = Integer.parseInt(price);
                if(intPrice < 0) {
                    priceError.setText("* Must input to The price value cannot less than 0!");
                    isValid = false;
                }else {
                    priceError.setText("");
                }
            }

            if(TextUtils.isEmpty(address)){
                addressError.setText( "* Must input to The address field!");
                isValid = false;
            }else{
                addressError.setText("");
            }

            if(TextUtils.isEmpty(property)){
                propError.setText( "* Must input to the property field!");
                isValid = false;
            }else{
                propError.setText("");
            }

            if(TextUtils.isEmpty(bed)){
                error += "* The bed field cannot be blank!\n";
                isValid = false;
            }else{
                intBed = Integer.parseInt(price);
                if(intBed < 0){
                    error += "* The bedroom value cannot less than 0!\n";
                    isValid = false;
                }else{
                    error = "";
                }
            }

            if(TextUtils.isEmpty(bath)){
                error += "* The bath field cannot be blank!\n";
                isValid = false;
            }else{
                intBath = Integer.parseInt(price);
                if(intBath < 0){
                    error += "* The bathroom value cannot less than 0!\n";
                    isValid = false;
                }else{
                    error = "";
                }
            }

            if(TextUtils.isEmpty(poster)){
                posterError.setText("* Must input to The name of poster field");
                isValid = false;
            }else{
                posterError.setText("");
            }

            if(isValid){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.now();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Confirm Information");
                builder.setMessage("price: "+ price + "\nAddress: " + address +"\nProperty Type: " + property + "\nBedroom " + bed +"\nBathroom: "+bath+"\nFurniture: " + furniture + "\nnote: "+note+"Poster: "+poster+"\nPost Date:"+date);
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 1nd transfer data to the order activity
                                Bundle data = new Bundle();
                                data.putString("price", price);
                                data.putString("address", address);
                                data.putString("property", property);
                                data.putString("bed", bed);
                                data.putString("bath", bath);
                                data.putString("furniture", furniture);
                                data.putString("note", note);
                                data.putString("poster", poster);
                                data.putString("date", dtf.format(date));

                                Intent TestActivity = new Intent(v.getContext(), ShowActivity.class); // Call activity
                                TestActivity.putExtras(data); // Transfer data

                                startActivity(TestActivity); // Start move to "TestActivity" activity
                                finish(); // End this activity
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                numError.setText(error);
            }

        }
    };
}