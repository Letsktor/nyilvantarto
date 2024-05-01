package hu.undieb.nyilvantarto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HallgatokRecViewAdapter extends RecyclerView.Adapter<HallgatokRecViewAdapter.ViewHolder> {
    public HallgatokRecViewAdapter(List<Hallgato> hallgatok) {
        this.hallgatok = hallgatok;
    }

    private List<Hallgato> hallgatok;
    @NonNull
    @Override
    public HallgatokRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hallgatok,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallgatokRecViewAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(hallgatok.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return hallgatok.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView=itemView.findViewById(R.id.txtHallgato);
        }
    }
}
