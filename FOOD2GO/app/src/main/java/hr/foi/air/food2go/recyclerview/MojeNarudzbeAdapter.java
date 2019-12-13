package hr.foi.air.food2go.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import hr.foi.air.core.Racun;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.fragmenti.moje_narudzbe.MojeNarudzbeFragment;

public class MojeNarudzbeAdapter extends RecyclerView.Adapter<MojeNarudzbeAdapter.ViewHolder> {
    private ArrayList<Racun> racuni;
    private Context context;

    public MojeNarudzbeAdapter(Context context, ArrayList<Racun> racuni){
        this.context = context;
        this.racuni = racuni;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_moje_narudzbe_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.brojRacuna.setText(racuni.get(position).getBrojRacuna());

        DecimalFormat df = new DecimalFormat("0.00");
        holder.cijena.setText(df.format(racuni.get(position).getUkupno()).replace('.', ',') + " kn");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        holder.datum.setText(dateFormat.format(racuni.get(position).getDatum()));

        holder.artiklItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Racun racun = racuni.get(position);
                MojeNarudzbeFragment.Racun = racun;
                MojeNarudzbeFragment.PrikaziRacun(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return racuni.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brojRacuna;
        TextView datum;
        TextView cijena;

        RelativeLayout artiklItemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brojRacuna = itemView.findViewById(R.id.brojRacuna);
            cijena = itemView.findViewById(R.id.artiklCijena);
            datum = itemView.findViewById(R.id.artiklDatum);
            artiklItemLayout = itemView.findViewById(R.id.narudzba);
        }
    }
}
