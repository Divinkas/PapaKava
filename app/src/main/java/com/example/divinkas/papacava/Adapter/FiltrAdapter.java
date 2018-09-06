package com.example.divinkas.papacava.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.divinkas.papacava.Fragment.CavaFragment;
import com.example.divinkas.papacava.R;

import java.util.List;

public class FiltrAdapter extends RecyclerView.Adapter<FiltrAdapter.ViewHolder>{
    private Context context;
    private List<String> listFiltr;
    private boolean []show;

    public FiltrAdapter(Context context, List<String> list) {
        this.context = context;
        listFiltr = list;
        show = new boolean[list.size()];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.filtr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.checkBox.setText(listFiltr.get(position));
        holder.checkBox.setChecked(true);
        holder.checkBox.setBackgroundResource(R.drawable.cbx_bg_active);
        show[position] = true;
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                show[position] = isChecked;
                if(isChecked){ holder.checkBox.setBackgroundResource(R.drawable.cbx_bg_active); }
                else {holder.checkBox.setBackgroundResource(R.drawable.cbx_bg);}
                CavaFragment.filtration(show, context);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listFiltr.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.cbxFiltrItem);
        }
    }
}
