package hu.undieb.nyilvantarto.activites.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import hu.undieb.nyilvantarto.model.Ora;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.JelenletActivity;

public class StatisztikaRecViewAdapter  extends RecyclerView.Adapter<StatisztikaRecViewAdapter.ViewHolder> {
    private List<Ora> orak;
    Context mContext;
    public StatisztikaRecViewAdapter(List<Ora> orak, Context mContext) {
        this.orak = orak;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public StatisztikaRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statisztika,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisztikaRecViewAdapter.ViewHolder holder, int position) {
        Log.d("ORAK",orak.toString());
        holder.txtDatumView.setText(orak.get(position).getDate().substring(5));
        holder.txtJelenlevok.setText(Integer.toString(orak.get(position).getJelenlevok_szama()));
        Log.d("LUL",holder.txtJelenlevok.getText().toString());
        holder.parent.setOnClickListener(v->{
            Intent intent=new Intent(mContext, JelenletActivity.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orak.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDatumView,txtJelenlevok;
        CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDatumView=itemView.findViewById(R.id.txtHallgato);
            txtJelenlevok=itemView.findViewById(R.id.txtNumberView);
            parent=itemView.findViewById(R.id.parentCardView);

        }
    }
}
