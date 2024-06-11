package hu.undieb.nyilvantarto.activites.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.pop_up_kartyaActivity;

public class HallgatokRecViewAdapter extends RecyclerView.Adapter<HallgatokRecViewAdapter.ViewHolder> {
    public HallgatokRecViewAdapter(List<Hallgato> hallgatok, String kurzus_nev) {
        this.hallgatok = hallgatok;
        this.kurzus_nev=kurzus_nev;
    }

    private List<Hallgato> hallgatok;
    private String kurzus_nev;
    @NonNull
    @Override
    public HallgatokRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hallgatok,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallgatokRecViewAdapter.ViewHolder holder, int position) {
        boolean longclick_check;
        holder.txtView.setText(hallgatok.get(position).getName());
        holder.parent.setOnLongClickListener(v->{
            holder.con.setVisibility(View.VISIBLE);
            holder.edit.setOnClickListener(v1->{
                hallgatok.get(position).setCardId("");
                hallgatok.get(position).setCardNumber("");
                hallgatok.get(position).setName("");
            });
            holder.delete.setOnClickListener(v2->{
                KurzusokUtils.getInstance().removeHallgato(kurzus_nev,"0",Integer.toString(position));
                holder.con.setVisibility(View.GONE);
            });
          return true;
        });
        if(!hallgatok.get(position).getCardId().equals("") && !hallgatok.get(position).getCardNumber().equals(""))
        {


            holder.card.setAlpha(1.0f);
            holder.parent.setOnClickListener(v->{
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                View view = LayoutInflater.from(holder.parent.getContext()).inflate(R.layout.pop_up_jelenlet, null);
                TextView name=view.findViewById(R.id.txtName);
                name.setText(hallgatok.get(position).getName());
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
                holder.check.setAlpha(1.0f);
            });

        }
        else{


            holder.parent.setOnClickListener(v->{
                Intent intent=new Intent(holder.parent.getContext(), pop_up_kartyaActivity.class);
                intent.putExtra("tanulo_nev",holder.txtView.getText());
                holder.parent.getContext().startActivity(intent);

                /*AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                View view = LayoutInflater.from(holder.parent.getContext()).inflate(R.layout.pop_up_kartya, null);
                TextView name=view.findViewById(R.id.txtName);
                name.setText(hallgatok.get(position).getName());
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();*/
                holder.card.setAlpha(1.0f);
            });
        }
    }

    @Override
    public int getItemCount() {
        return hallgatok.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        ImageView card,check;
        CardView parent;
        ConstraintLayout con;
        Button edit, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parentCardView);
            txtView=itemView.findViewById(R.id.txtHallgato);
            card=itemView.findViewById(R.id.imgCardView);
            check=itemView.findViewById(R.id.imgCheck);
            con=itemView.findViewById(R.id.constLayHidden);
            edit=itemView.findViewById(R.id.btnEdit);
            delete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
