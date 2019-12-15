package hr.foi.air.food2go.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import hr.foi.air.core.Artikl;
import hr.foi.air.food2go.R;
import hr.foi.air.food2go.fragmenti.trenutna_narudzba.TrenutnaNarudzbaViewModel;

public class TrenutnaNarudzbaRecyclerAdapter extends RecyclerView.Adapter<TrenutnaNarudzbaRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList <Artikl> ArtikliNarudzbe;

    public TrenutnaNarudzbaRecyclerAdapter(Context context, ArrayList<Artikl> artikliNarudzbe) {
        this.context=context;
        this.ArtikliNarudzbe=artikliNarudzbe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =LayoutInflater.from(context).inflate(R.layout.trenutna_narudzba_artikl,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).asBitmap().load(ArtikliNarudzbe.get(position).getUrlSlike()).into(holder.slika);
        holder.naziv.setText(ArtikliNarudzbe.get(position).getNaziv());
        DecimalFormat df = new DecimalFormat("0.00");
        holder.cijena.setText(df.format(ArtikliNarudzbe.get(position).getCijena()).replace('.',',')+" kn");
        holder.kolicina.setText("0");
        holder.dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolicina= holder.kolicina.getText().toString();
                Integer kolicinaInt= Integer.parseInt(kolicina);
                kolicinaInt++;
                holder.kolicina.setText(kolicinaInt.toString());
            }
        });
        holder.oduzmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolicina= holder.kolicina.getText().toString();
                Integer kolicinaInt= Integer.parseInt(kolicina);
                kolicinaInt--;
                holder.kolicina.setText(kolicinaInt.toString());
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView slika;
        TextView naziv;
        TextView cijena;
        Button dodaj;
        Button oduzmi;
        TextView kolicina;
        RelativeLayout  trenutnaNarudzbaItemLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slika=itemView.findViewById(R.id.artikl_slika);
            naziv =itemView.findViewById(R.id.txtNaziv);
            cijena = itemView.findViewById(R.id.txtCijena);
            dodaj=itemView.findViewById(R.id.uiActionPlus);
            oduzmi=itemView.findViewById(R.id.uiActionMinus);
            kolicina=itemView.findViewById(R.id.txtKolicina);
            trenutnaNarudzbaItemLayout=itemView.findViewById(R.id.trenutnaNarudzba_artikl_layout);
        }
    }
    @Override
    public int getItemCount() {
        return ArtikliNarudzbe.size();
    }
}
