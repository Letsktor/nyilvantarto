package hu.undieb.nyilvantarto.activites.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.model.Jelenlet;

public class JelenletRecViewAdapter extends RecyclerView.Adapter<JelenletRecViewAdapter.ViewHolder> {
    private List<Jelenlet> hallgatok;

    public JelenletRecViewAdapter(List<Jelenlet> hallgatok) {
        this.hallgatok = hallgatok;
    }

    @NonNull
    @Override
    public JelenletRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statisztika_hallgato,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JelenletRecViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(hallgatok.get(position).getName());
        if(hallgatok.get(position).getStatus()== Jelenlet.Status.MISSING)
        {
            holder.cross.setVisibility(View.VISIBLE);
        } else if (hallgatok.get(position).getStatus()== Jelenlet.Status.PRESENT) {
            holder.green_check.setVisibility(View.VISIBLE);
        } else if (hallgatok.get(position).getStatus()== Jelenlet.Status.RECORDEDBYTEACHER) {
            holder.orange_check.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return hallgatok.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView cross, green_check, orange_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtHallgato);
            cross=itemView.findViewById(R.id.imgCross);
            green_check=itemView.findViewById(R.id.imgGreenCheck);
            orange_check=itemView.findViewById(R.id.imgOrangeCheck);
        }
    }
}
