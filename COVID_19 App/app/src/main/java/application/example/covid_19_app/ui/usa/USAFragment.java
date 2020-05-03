package application.example.covid_19_app.ui.usa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import application.example.covid_19_app.R;

public class USAFragment extends Fragment {

    RecyclerView rvCovidState;
    ProgressBar progressBar;

    private static final String TAG = USAFragment.class.getSimpleName();

    ArrayList<CovidState> covidStates;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_usa, container, false);

        rvCovidState = root.findViewById(R.id.rvCovidUSA);
        progressBar = root.findViewById(R.id.progress_circular_country);
        rvCovidState.setLayoutManager(new LinearLayoutManager(getActivity()));

        //call volley
        getDataFromServerUS();


        return root;
    }

    private void showRecyclerView(){
        CovidStateAdapter covidStateAdapter = new CovidStateAdapter(covidStates);
        rvCovidState.setAdapter(covidStateAdapter);
    }

    private void getDataFromServerUS(){
        String url = "https://corona.lmao.ninja/v2/states";

        covidStates = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            covidStates.add(new CovidState(data.getString("state"), data.getString("cases")));
                        }
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}