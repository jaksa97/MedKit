package veljkojaksic.medkit.functionality;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import veljkojaksic.medkit.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTextView;
    public CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemTextView = itemView.findViewById(R.id.itemTextView);
        cardView = itemView.findViewById(R.id.singleItem);
    }
}
