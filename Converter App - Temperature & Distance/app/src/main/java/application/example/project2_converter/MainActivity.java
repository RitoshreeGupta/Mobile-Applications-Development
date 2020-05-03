package application.example.project2_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.*;
import android.view.View;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    EditText temp;
    TextView result;
    RadioButton toC, toF;
    double value;
    Button convert;
    CheckBox savedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = (EditText)findViewById(R.id.enterTemp);
        toC = (RadioButton)findViewById(R.id.C2F);
        toF = (RadioButton)findViewById(R.id.F2C);
        result = (TextView)findViewById(R.id.result);
        convert = (Button)findViewById(R.id.convert);
        savedata = (CheckBox)findViewById((R.id.checkBox));
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value = new Double (temp.getText().toString());

                if(savedata.isChecked()){

                    mEditor.putString(getString(R.string.save), "True");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.enter_temperature), temp.getText().toString());
                    mEditor.commit();
                }
                else{
                    mEditor.putString(getString(R.string.save), "False");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.enter_temperature), "");
                    mEditor.commit();
                }


                    if(toC.isChecked()){
                        value = UnitConverter.C2F(value);
                    }
                    else{
                        value = UnitConverter.F2C(value);
                    }
                    result.setText(String.valueOf(value));
            }
        });

        if(savedInstanceState != null){
            value = savedInstanceState.getDouble("0 degrees");
            result.setText(String.valueOf(value));
        }
    }

    private void checkSharedPreferences(){
        String save_data = mPreferences.getString(getString(R.string.save), "False");
        String enter_temperature = mPreferences.getString(getString(R.string.enter_temperature),"");

        temp.setText(enter_temperature);

        if(save_data.equals("True")){
            savedata.setChecked(true);
        }
        else{
            savedata.setChecked(false);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Temp:
                Intent intent=new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Temperature Converter Selected", Toast.LENGTH_SHORT).show();
                        return true;

            case R.id.Dist:
                Intent intent2=new Intent(MainActivity.this, Distance_Converter.class);
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

        outState.putDouble("0 degrees", value);
    }

}
