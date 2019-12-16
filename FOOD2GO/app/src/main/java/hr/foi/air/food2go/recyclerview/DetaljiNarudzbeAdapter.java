package hr.foi.air.food2go.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import hr.foi.air.core.StavkeRacuna;
import hr.foi.air.food2go.R;

public class DetaljiNarudzbeAdapter extends RecyclerView.Adapter<DetaljiNarudzbeAdapter.ViewHolder>{
    private ArrayList<StavkeRacuna> artikli;
    public Context context;
    private String ukupnoCijena;

    public DetaljiNarudzbeAdapter(Context context, ArrayList<StavkeRacuna> artikli){
        this.context = context;
        this.artikli = artikli;
    }

    @NonNull
    @Override
    public DetaljiNarudzbeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_detaljinarudzbe_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetaljiNarudzbeAdapter.ViewHolder holder, int position) {
        holder.naziv.setText(artikli.get(position).getNaziv());
        holder.cijena.setText(artikli.get(position).getArtiikl_Temporalno_Cijena());
        holder.kolicina.setText(artikli.get(position).getKolicina());
    }

    @Override
    public int getItemCount() {
        return artikli.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView naziv;
        TextView cijena;
        TextView kolicina;
        Button ocijeni;
        RelativeLayout detaljiNarudzbe_item_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            naziv = itemView.findViewById(R.id.nazivArtikla);
            cijena = itemView.findViewById(R.id.cijenaArtikla);
            kolicina = itemView.findViewById(R.id.uikolicinaArtikla);
            ocijeni = itemView.findViewById(R.id.uiActionOcijeni);
            detaljiNarudzbe_item_layout = itemView.findViewById(R.id.detaljiNarudzbe_item_layout);
        }
    }
}
