package egat.woraphan.poyservice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    //ตัวแปรค่าคงที่ ไม่ให้ตัวอื่นมาเปลี่ยน
    private static final String urlJSON = "http://swiftcodingthai.com/6aug/get_user_poy.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);

    }   //Main Method

    //Create Inner Class ดึงเฉพาะData
    //AsyncTask ก่อนต่อ ตอนต่อ หลังต่อ
    private class SynchronizeUser extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private String myUserString, myPasswordString;

        //Constructor
        public SynchronizeUser(Context context,
                               String myUserString,
                               String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //พยายามต่อเน็ต

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                //จ่าหน้าซอง ชี้เป้า php
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                //สิ่งที่อ่านได้จากbody
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            //ถ้าต่อเน็ตไม่ได้ ก็พยายามต่อใหม่
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            //return null;
        }  // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Show Debug
            Log.d("7AugV1", "JSON ==> " + s);
        }
    } // SynUser Class



    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordEditText.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, 3, "Have Space", "Please Fill All Every Blank");
        } else {

            //Call SynUser
            SynchronizeUser synchronizeUser = new SynchronizeUser(this, userString, passwordString);
            synchronizeUser.execute();

        }

    } //clickSignIn

    public void clickSignIUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   //Main Class
