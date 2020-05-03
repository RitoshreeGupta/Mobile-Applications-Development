package application.example.project2_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Distance_Converter extends AppCompatActivity {

    private static final String TAG = "Distance_Converter";
    private SharedPreferences mmPreferences;
    private SharedPreferences.Editor mmEditor;

    EditText value;
    TextView answer;
    RadioButton kmTmi, miTkm;
    double ans;
    Button calculate;
    CheckBox ssavedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance__converter);

        value = (EditText)findViewById(R.id.enterDist);
        kmTmi = (RadioButton)findViewById(R.id.km2mi);
        miTkm = (RadioButton)findViewById(R.id.mi2km);
        answer = (TextView)findViewById(R.id.answer);
        calculate = (Button)findViewById(R.id.calc);
        ssavedata = (CheckBox)findViewById((R.id.checkBox));
        mmPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mmEditor = mmPreferences.edit();

        checkSharedPreferences();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = new Double (value.getText().toString());

                if(ssavedata.isChecked()){

                    mmEditor.putString(getString(R.string.save), "True");
                    mmEditor.commit();

                    mmEditor.putString(getString(R.string.enter_value), value.getText().toString());
                    mmEditor.commit();
                }
                else{
                    mmEditor.putString(getString(R.string.save), "False");
                    mmEditor.commit();

                    mmEditor.putString(getString(R.string.enter_value), "");
                    mmEditor.commit();
                }

                if(kmTmi.isChecked()){
                    ans = DistanceUnitConverter.km2mi(ans);
                }
                else{
                    ans = DistanceUnitConverter.mi2km(ans);
                }
                answer.setText(String.valueOf(ans));
            }
        });

        if(savedInstanceState != null){
            ans = savedInstanceState.getDouble("Answer");
            answer.setText(String.valueOf(ans));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu, menu);
        return true;
    }

    private void checkSharedPreferences(){
        String save_data = mmPreferences.getString(getString(R.string.save), "False");
        String enter_value = mmPreferences.getString(getString(R.string.enter_value),"");

        value.setText(enter_value);

        if(save_data.equals("True")){
            ssavedata.setChecked(true);
        }
        else{
            ssavedata.setChecked(false);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Temp:
                Intent intent = new Intent(Distance_Converter.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Temperature Converter Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Dist:
                Intent intent2 = new Intent(Distance_Converter.this, Distance_Converter.class);
                startActivity(intent2);
                Toast.makeText(this, "Distance Converter Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putDouble("Answer", ans);
    }
}