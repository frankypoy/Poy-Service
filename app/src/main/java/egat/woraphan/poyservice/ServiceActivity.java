package egat.woraphan.poyservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private String nameString, avataString;
    private TextView nameTextView;
    private ImageView avataImageView;
    private ListView serviceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView8);
        avataImageView = (ImageView) findViewById(R.id.imageView7);
        serviceListView = (ListView) findViewById(R.id.listView);

        //Get Value From Intent
        nameString = getIntent().getStringExtra("Name");
        avataString = getIntent().getStringExtra("Avata");

        Log.d("7AugV2", "Name ==> " + nameString);
        Log.d("7AugV2", "Avata ==> " + avataString);

        //Show View
        nameTextView.setText(nameString);
        avataImageView.setImageResource((new MyAlert().findAvata(Integer.parseInt(avataString))));

    }   //Main Method

}   //Main Class
