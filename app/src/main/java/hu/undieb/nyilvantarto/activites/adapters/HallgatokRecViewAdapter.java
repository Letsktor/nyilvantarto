package hu.undieb.nyilvantarto.activites.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.undieb.nyilvantarto.activites.JelenletRog;
import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.pop_up_kartyaActivity;

public class HallgatokRecViewAdapter extends RecyclerView.Adapter<HallgatokRecViewAdapter.ViewHolder> {
    public HallgatokRecViewAdapter(List<Hallgato> hallgatok, String kurzus_nev) {
        this.hallgatok = hallgatok;
        this.kurzus_nev = kurzus_nev;
    }

    private List<Hallgato> hallgatok;
    private String kurzus_nev;

    @NonNull
    @Override
    public HallgatokRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hallgatok, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallgatokRecViewAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(hallgatok.get(position).getName());
        holder.parent.setOnLongClickListener(v -> {
            holder.con.setVisibility(View.VISIBLE);
            holder.edit.setOnClickListener(v1 -> {
                EditButton(holder, position);
            });
            holder.delete.setOnClickListener(v2 -> {
                KurzusokUtils.getInstance().removeHallgato(kurzus_nev, Integer.toString(position));
                holder.con.setVisibility(View.GONE);
            });
            return true;
        });
        if (!hallgatok.get(position).getCardId().equals("") && !hallgatok.get(position).getCardNumber().equals("")) {


            holder.card.setAlpha(1.0f);
            holder.parent.setOnClickListener(v -> {
                Intent intent = new Intent(holder.parent.getContext(), JelenletRog.class);
                intent.putExtra("tanulo_nev", holder.txtView.getText().toString());
                holder.parent.getContext().startActivity(intent);
            });

        } else {


            holder.parent.setOnClickListener(v -> {
                Intent intent = new Intent(holder.parent.getContext(), pop_up_kartyaActivity.class);
                intent.putExtra("tanulo_nev", holder.txtView.getText().toString());
                intent.putExtra("hallgato_id", Integer.toString(position));
                holder.parent.getContext().startActivity(intent);
                holder.card.setAlpha(1.0f);
            });
        }
    }

    private void EditButton(@NonNull ViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
        View view = LayoutInflater.from(holder.parent.getContext()).inflate(R.layout.activity_edit, null);
        EditText name = view.findViewById(R.id.txtName);
        EditText nfc = view.findViewById(R.id.txtNfc);
        EditText cardNumber = view.findViewById(R.id.txtCadNumber);
        name.setHint(hallgatok.get(position).getName());
        nfc.setHint("NFC: " + hallgatok.get(position).getCardId());
        cardNumber.setHint("Kártya szám: " + hallgatok.get(position).getCardNumber());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        Button btn = view.findViewById(R.id.btnModify);
        btn.setOnClickListener(v3 -> {
            KurzusokUtils.getInstance().updateHallgato(
                    new Hallgato(
                            name.getText().toString().equals("") ? name.getHint().toString() : name.getText().toString(),
                            cardNumber.getText().toString().equals("")?cardNumber.getHint().toString().substring(cardNumber.getHint().toString().indexOf(":")+1).trim():cardNumber.getText().toString(),
                            nfc.getText().toString().equals("")?nfc.getHint().toString().substring(nfc.getHint().toString().indexOf(":")+1).trim():nfc.getText().toString()
                    ),
                    kurzus_nev,
                    Integer.toString(position)
            );
            dialog.cancel();
            view.refreshDrawableState();
        });
    }

    @Override
    public int getItemCount() {
        return hallgatok.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        ImageView card, check;
        CardView parent;
        ConstraintLayout con;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parentCardView);
            txtView = itemView.findViewById(R.id.txtHallgato);
            card = itemView.findViewById(R.id.imgCardView);
            check = itemView.findViewById(R.id.imgCheck);
            con = itemView.findViewById(R.id.constLayHidden);
            edit = itemView.findViewById(R.id.btnEdit);
            delete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
