package vn.edu.greenwich.rentalz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView tvPrice = findViewById(R.id.tvPrice_show);
        TextView tvAddress = findViewById(R.id.tvAddress_show);
        TextView tvProperty = findViewById(R.id.tvProperty_show);
        TextView tvBed = findViewById(R.id.tvBed_show);
        TextView tvBath = findViewById(R.id.tvBath_show);
        TextView tvFur = findViewById(R.id.tvFur_show);
        TextView tvNotes = findViewById(R.id.tvNote_show);
        TextView tvDate = findViewById(R.id.tvDate_show);
        TextView tvPoster = findViewById(R.id.tvPoster_show);

        // 1nd Get data from the previous activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            tvPrice.setText(bundle.getString("price"));
            tvAddress.setText(bundle.getString("address"));
            tvProperty.setText(bundle.getString("property"));
            tvBed.setText(bundle.getString("bed"));
            tvBath.setText(bundle.getString("bath"));
            tvFur.setText(bundle.getString("furniture"));
            tvNotes.setText(bundle.getString("note"));
            tvDate.setText(bundle.getString("date"));
            tvPoster.setText(bundle.getString("poster"));
        }
    }
}