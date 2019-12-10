package hr.foi.air.food2go.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import hr.foi.air.food2go.R;
import hr.foi.air.core.Artikl;

public class OdabirKategorijeRecyclerAdapter extends RecyclerView.Adapter<OdabirKategorijeRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Artikl> artikli;

    public OdabirKategorijeRecyclerAdapter(Context context, ArrayList<Artikl> artikli){
        this.context = context;
        this.artikli = artikli;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.odabir_kategorije_artikl_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(artikli.get(position).getUrlSlike())
                .into(holder.slika);

        holder.naziv.setText(artikli.get(position).getNaziv());
        DecimalFormat df = new DecimalFormat("0.00");
        holder.cijena.setText(df.format(artikli.get(position).getCijena()).replace('.', ','));

        holder.artiklItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, artikli.get(position).getNaziv(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikli.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView slika;
        TextView naziv;
        TextView cijena;
        RelativeLayout artiklItemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slika = itemView.findViewById(R.id.artikl_thumbnail);
            naziv = itemView.findViewById(R.id.artikl_ime);
            cijena = itemView.findViewById(R.id.artikl_cijena);
            artiklItemLayout = itemView.findViewById(R.id.artikl_item_layout);
        }
    }
}
