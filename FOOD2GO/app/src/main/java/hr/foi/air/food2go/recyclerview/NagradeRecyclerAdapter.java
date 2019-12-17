package hr.foi.air.food2go.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hr.foi.air.core.Nagrada;
import hr.foi.air.food2go.R;

public class NagradeRecyclerAdapter extends RecyclerView.Adapter<NagradeRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Nagrada> nagrade;

    public NagradeRecyclerAdapter(Context context, ArrayList<Nagrada> nagrade){
        this.context = context;
        this.nagrade = nagrade;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nagrada_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.popust.setText(nagrade.get(position).getNaziv());
        holder.brojPotrebnihBodova.setText("Potrebno bodova: " + Integer.toString(nagrade.get(position).getBrojBodova()));
    }

    @Override
    public int getItemCount() {
        return nagrade.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView popust;
        TextView brojPotrebnihBodova;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popust = itemView.findViewById(R.id.popust);
            brojPotrebnihBodova = itemView.findViewById(R.id.potreban_broj_bodova);
        }
    }
}
