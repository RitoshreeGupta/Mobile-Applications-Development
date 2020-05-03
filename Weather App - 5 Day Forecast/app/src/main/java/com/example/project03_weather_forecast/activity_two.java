package com.example.project03_weather_forecast;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_two extends AppCompatActivity {
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Bundle get_info = getIntent().getExtras();

        String info = get_info.getString("city_name");
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(info+"\n");

        TextView show_time = (TextView)findViewById(R.id.myText);
        show_time.setText(info);


        requestQueue = Volley.newRequestQueue(this);

        //create object request
        final TextView msg = (TextView)findViewById(R.id.myText);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,    //the request method
                        "https://api.openweathermap.org/data/2.5/forecast?q="+info+"&appid=645fa44edacf310ed5db68465b4b9d1d",  //the URL
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //this prints the WHOLE string
                                //Log.i("JSON response", response.toString());
                                boolean flag = true;
                                try {
                                    //get description of weather // response is the JSONObject (main full body)
                                    {
                                        JSONArray list = response.getJSONArray("list");

                                        //JSONArray mainAR = response.getJSONArray("main");
                                        for (int i = 0; i < list.length(); i+=8) {
                                            JSONObject currentWeather = list.getJSONObject(i);
                                            String DateInLong = currentWeather.getString("dt_txt");
                                            //from main info
                                            JSONObject sub_currentWeather = currentWeather.getJSONObject("main");
                                            Double temp = sub_currentWeather.getDouble("temp");
                                            Double fl_like = sub_currentWeather.getDouble("feels_like");
                                            Double min = sub_currentWeather.getDouble("temp_min");
                                            Double max = sub_currentWeather.getDouble("temp_max");

                                            temp = (temp-273.15)*(9/5)+32.00;
                                            fl_like = (fl_like-273.15)*(9/5)+32.00;
                                            min = (min-273.15)*(9/5)+32.00;
                                            max = (max-273.15)*(9/5)+32.00;

                                            int humid = sub_currentWeather.getInt("humidity");


                                            //information from weather array object

                                            JSONArray sub_weatherArray = currentWeather.getJSONArray("weather");
                                            JSONObject weather_info = sub_weatherArray.getJSONObject(0);
                                            String des = weather_info.getString("description");
                                            String main_info = weather_info.getString("main");


                                            //wind
                                            JSONObject sub_wind = currentWeather.getJSONObject("wind");
                                            Double speed_s = sub_wind.getDouble("speed");
                                            Double speed = speed_s*2.237;

                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date myDate = null;
                                            try {
                                                myDate = simpleDateFormat.parse(DateInLong);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            if(i == 0){
                                                flag = true;
                                            }else{
                                                flag = false;
                                            }
                                            //String description = currentWeather.getString("description");

                                            // Log.i("JSON info", "ID: " + id);
                                            Log.i("JSON info", "TEMP: " + temp);
                                            Log.i("JSON info", "Fl_Lyk: " + fl_like);
                                            Log.i("JSON info", "MIN: " + min);
                                            Log.i("JSON info", "MAX: " + max);

                                            Log.i("JSON info", "HUMIDITY: " + humid);
                                            Log.i("JSON info", "DT_TXT" + myDate);
                                            Log.i("JSON info","SPEED "+ speed);
                                            Log.i("JSON info","MAIN" + main_info);
                                            Log.i("JSON info","Description" + des);
                                            String only_date = myDate.toString().substring(0,10);

                                            //Log.i("JSON info","Description" + des);
                                            if(flag == true){
                                                msg.setText(only_date+"\n\nTemp: "+new DecimalFormat("###.##").format(temp)+"F    H:"+
                                                        new DecimalFormat("###").format(max)+ ",  L: " + new DecimalFormat("###").format(min)+
                                                        "\nFeels Like: "+new DecimalFormat("###.##").format(fl_like)+" F\nHumidity: "+humid+"\nWind Speed: "
                                                        +new DecimalFormat("###.##").format(speed)+"mph\n" + main_info + ": " + des +"\n-----------------------------------------------------" +
                                                        "-------------\n\n ");

                                                int resourceID = getResources().getIdentifier(des, "drawable", main_info);


                                            }
                                            if(flag == false) {
                                                msg.append(only_date+"\n\nTemp: "+new DecimalFormat("###.##").format(temp)+"F    H:"+
                                                        new DecimalFormat("###").format(max)+ ",  L: " + new DecimalFormat("###").format(min)+
                                                        "\nFeels Like: "+new DecimalFormat("###.##").format(fl_like)+" F\nHumidity: "+humid+"\nWind Speed: "
                                                        +new DecimalFormat("###.##").format(speed)+"mph\n" + main_info + ": " + des +"\n-----------------------------------------------------" +
                                                        "-------------\n\n ");
                                            }


                                        }
                                    }
                                }
                                catch(JSONException ex) {
                                    Log.e("JSON Error", ex.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }

                );//end of JSON object request

        requestQueue.add(jsonObjectRequest);


    }






}
