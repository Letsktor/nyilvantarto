package hu.undieb.nyilvantarto;

import static androidx.core.content.ContextCompat.getSystemService;

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

public class KurzusokRecViewAdapter extends RecyclerView.Adapter<KurzusokRecViewAdapter.ViewHolder> {
    private ArrayList<Kurzus> kurzusok=new ArrayList<>();
    private Context context;

    public KurzusokRecViewAdapter(Context context) {
        this.context = context;
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
            txtView.setText(kurzusok.get(position).getKurzusNev());
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();
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
