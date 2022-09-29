package com.experientialetc.Hr;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class FirstFragment extends Fragment {
    private static final String url="https://www.experientialetc.com/hrapp/api/datetime.php";
    String datee = "11.11.11";
    AppCompatImageButton imageButton;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    Boolean isswitchOn = false;
    String da,sa;
    TextView time,date;
    SharedPreferences preferences;
    View Clock_in;
    View Clock_out;
    int value;
    @SuppressLint("MissingInflatedId")
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

       Clock_in=rootView.findViewById(R.id.clock_in_imag);
       //imageButton=rootView.findViewById(R.id.clockinbtn);
       Clock_out= rootView.findViewById(R.id.clock_out_imag);
        //ImageView imageView = (ImageView) rootView.findViewById(R.id.clockintn);
        TextView textView = (TextView) rootView.findViewById(R.id.date);
        requestQueue= Volley.newRequestQueue(getContext());
        time = (TextView) rootView.findViewById(R.id.time);
        date = (TextView) rootView.findViewById(R.id.date);
        //e1 = (EditText) rootView.findViewById(R.id.edit);

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM");
        time.setText(formatter.format(today));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        //SimpleDateFormat sdfa = new SimpleDateFormat("HH:mmr");
        String currentDateandTime = sdf.format(new Date());
        //String currentDateandTimea = sdfa.format(new Date());
        //textView.setText(currentDateandTime);
        //time.setText(currentDateandTimea);

        time.setText(GetTime());
        date.setText(currentDateandTime);
        preferences = this.getActivity().getSharedPreferences("hr_app", MODE_PRIVATE);
       value= preferences.getInt("save",0);
         if(value==1){

             Clock_in.setVisibility(View.GONE);
             Clock_out.setVisibility(View.VISIBLE);

         }if(value==2){
            Clock_in.setVisibility(View.VISIBLE);
            Clock_out.setVisibility(View.GONE);
         }


        Clock_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("save",1);
                    editor.apply();
                    Toast.makeText(getActivity(), "You Clock-out at "+GetTime()+GetDate(), Toast.LENGTH_LONG).show();
                    Clock_in.setVisibility(View.GONE);
                    Clock_out.setVisibility(View.VISIBLE);
                    da = GetTime();
                Intent intent = new Intent(getActivity().getApplication(), clockin.class);
                startActivity(intent);
                  //  SendData();
            }
        });
   Clock_out.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("save",2);
        editor.apply();
        Toast.makeText(getActivity(), "You Clock-out at "+GetTime()+GetDate(), Toast.LENGTH_LONG).show();
        Clock_in.setVisibility(View.VISIBLE);
        Clock_out.setVisibility(View.GONE);
        da = GetTime();
      //  SendData();
    }
});

        return rootView;
    }

    private void SendData() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getContext(), "Data-Sent Successfully", Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("myTag", ""+error);


            }
        }) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> data = new HashMap<String, String>();

                DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter dt1 = DateTimeFormatter.ofPattern("hh:mm:ss");
                LocalDateTime now1 = LocalDateTime.now();

                data.put("date", dt.format(now));
                //Log.d("myTag", "This is my message" +  dtf.format(now));
                data.put("time", dt1.format(now1));
                //data.put("time", "11:11 AM");

            /*    String currentDate= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                data.put("date", currentDate);
                Log.d("myTag", "This is my message"+currentDate);
                String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
                data.put("time", currentTime);*/
                //data.put("date"+job);
                //String job = GetTime();



                return data;
            }
        };

        requestQueue.add(request);
    }

  /*  private void getSignUp() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //Toast.makeText(getActivity(), "You Clock-out at "+GetTime(), Toast.LENGTH_LONG).show();

                Toast.makeText(getActivity(), "Signup Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<String, String>();
                data.put("time",time.getText().toString());
//                data.put("email", e2.getText().toString());
//                data.put("phone", e3.getText().toString());
//                data.put("password", e4.getText().toString());
                return data;
            }
        };

        requestQueue.add(request);
    }*/

   /* private void sendTimeAndDate()
    {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), " Successfully", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<String, String>();
                data.put("time", time.getText().toString());

                return data;

            }


        };
        requestQueue.add(request);

    };*/


    public String GetTime(){

        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

    }
    public String GetDate(){

        return new SimpleDateFormat(" yyyy-MM-dd", Locale.getDefault()).format(new Date());

    }


    private void updateDetail() {
        Intent intent = new Intent(getActivity(), clockin.class);
        startActivity(intent);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {




    }
    private void blink()
    {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        if(time.getVisibility() == View.VISIBLE) {
                            time.setVisibility(View.INVISIBLE);
                        }
                        else {
                            time.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }



}
