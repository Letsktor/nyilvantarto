package hu.undieb.nyilvantarto.activites.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Kurzus;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.HallgatokActivity;
import hu.undieb.nyilvantarto.activites.StatisztikaActivity;

public class KurzusokRecViewAdapter extends RecyclerView.Adapter<KurzusokRecViewAdapter.ViewHolder> {
    private List<Kurzus> kurzusok;
    private Context context;

    public KurzusokRecViewAdapter(List<Kurzus> kurzusok, Context context) {
        this.kurzusok = kurzusok;
        this.context = context;
    }

    public KurzusokRecViewAdapter(List<Kurzus> kurzusok) {
        this.kurzusok = kurzusok;
    }

    @NonNull
    @Override
    public KurzusokRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kurzus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KurzusokRecViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(kurzusok.get(position).getKurzusNev());
        holder.parent.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(holder.parent.getContext()).inflate(R.layout.pop_up_statisztika_layout, null);
            TextView txtView=view.findViewById(R.id.txtView);
            ImageView imgStatisztika=view.findViewById(R.id.imgStatisztikaView);
            ImageView imgHallgatok=view.findViewById(R.id.imgHallgatokView);
            txtView.setText(kurzusok.get(position).getKurzusNev());
            builder.setView(view);
            AlertDialog dialog = builder.create();

            imgStatisztika.setOnClickListener(vo->{
                Intent intent=new Intent(context, StatisztikaActivity.class);
                context.startActivity(intent);
                dialog.dismiss();
            });
            imgHallgatok.setOnClickListener(vi->{
                Intent intent=new Intent(context, HallgatokActivity.class);
                intent.putExtra("kurzus_nev",holder.textView.getText().toString());
                context.startActivity(intent);
                dialog.dismiss();
            });


            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return kurzusok.size();
    }
    public void setKurzusok(ArrayList<Kurzus> kurzusok)
    {
        this.kurzusok=kurzusok;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent1);
            textView=itemView.findViewById(R.id.txtKurzus);
        }
    }
}
