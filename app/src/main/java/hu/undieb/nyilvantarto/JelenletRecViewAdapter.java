package hu.undieb.nyilvantarto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JelenletRecViewAdapter extends RecyclerView.Adapter<JelenletRecViewAdapter.ViewHolder> {
    @NonNull
    @Override
    public JelenletRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statisztika_hallgato,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JelenletRecViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText("MAriska");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtHallgato);
        }
    }
}
