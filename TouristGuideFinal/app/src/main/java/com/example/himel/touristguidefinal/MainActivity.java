package com.example.himel.touristguidefinal;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     private EditText userNameEt,passwordEt,confrmPasswordEt,emailEt;
     private Button signBt;
     private ProgressDialog progressDialog;
    final int totalProgressTime = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEt = findViewById(R.id.ETusername);
        passwordEt = findViewById(R.id.ETpassword);
        confrmPasswordEt = findViewById(R.id.ETconfirmpassword);
        emailEt = findViewById(R.id.ETemail);
        signBt = findViewById(R.id.signupbtn);

        signBt.setOnClickListener(this);


    }
    private  void registerUser(){

        final String username = userNameEt.getText().toString().trim();
        final String pass = passwordEt.getText().toString().trim();
        final String email = emailEt.getText().toString().trim();
        final String confrmpass = confrmPasswordEt.getText().toString().trim();

        //progressBar
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.show();

        final CustomThread  thread = new CustomThread();
        thread.start();

        /*final Thread t = new Thread() {

            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progressDialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };*/
       // t.start();

        if (pass.equals(confrmpass)){

            //use volley for stringreques
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //pass JSON Object Value
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }){
                //override getparam method

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params = new HashMap<>();

                    params.put("username",username);
                    params.put("email",email);
                    params.put("password",pass);
                    return params;
                }
            };

           RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        }
        else {
            Toast.makeText(getApplicationContext(),"Your Password Does Not Match",Toast.LENGTH_LONG).show();
           // progressDialog.hide();



        }





    }

    @Override
    public void onClick(View v) {
        if (v == signBt){
            registerUser();
        }

    }

    class CustomThread  extends Thread{

        @Override
        public void run() {
            int jumpTime = 0;

            while(jumpTime < totalProgressTime) {
                try {
                    sleep(1000);
                    jumpTime += 1;
                    progressDialog.setProgress(jumpTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}




