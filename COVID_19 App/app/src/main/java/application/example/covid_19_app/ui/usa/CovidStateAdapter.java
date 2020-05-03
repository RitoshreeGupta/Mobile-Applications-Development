package application.example.covid_19_app.ui.usa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import application.example.covid_19_app.R;

public class CovidStateAdapter extends RecyclerView.Adapter<CovidStateAdapter.ViewHolder> {

    ArrayList<CovidState> covidstates;
    public CovidStateAdapter(ArrayList<CovidState> covidstates){
        this.covidstates = covidstates;
    }


    @NonNull
    @Override
    public CovidStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_usa, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CovidStateAdapter.ViewHolder holder, int position) {

        CovidState covidstate = covidstates.get(position);
        holder.tvTotalUSCases.setText(covidstate.getmUSCases());
        holder.tvStatesName.setText(covidstate.getmCovidState());
    }

    @Override
    public int getItemCount() {
        return covidstates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTotalUSCases, tvStatesName;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTotalUSCases = itemView.findViewById(R.id.tvTotalUSCases);
            tvStatesName = itemView.findViewById(R.id.tvStateName);


        }


    }
}
