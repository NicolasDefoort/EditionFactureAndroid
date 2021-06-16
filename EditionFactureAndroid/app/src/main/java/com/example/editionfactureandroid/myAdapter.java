package com.example.editionfactureandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder>{

    ArrayList<model> dataholder;

    public myAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devis_item,parent, false);
        return  new myviewholder(view);
    }

    Context context;
    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.firstname.setText(dataholder.get(position).getFirstname());
        holder.lastname.setText(dataholder.get(position).getLastname());
        holder.date.setText(dataholder.get(position).getDate());
        holder.total.setText(dataholder.get(position).getTotal());
        holder.position = position;

        context = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CreateFacture.class);


                String s=String.valueOf(dataholder.get(position).getId());

                i.putExtra("id",s);
                i.putExtra("prenom",dataholder.get(position).getFirstname());
                i.putExtra("nom",dataholder.get(position).getLastname());
                i.putExtra("genre",dataholder.get(position).getGenre());
                i.putExtra("adresse",dataholder.get(position).getAdresse());
                i.putExtra("code postal",dataholder.get(position).getCodePostal());
                i.putExtra("ville",dataholder.get(position).getVille());
                i.putExtra("todoAdresse",dataholder.get(position).getTodoAdresse());
                i.putExtra("todoNom",dataholder.get(position).getTodoNom());
                i.putExtra("objet",dataholder.get(position).getObjet());
                i.putExtra("designation",dataholder.get(position).getDesignation());
                i.putExtra("puht",dataholder.get(position).getPuht());
                i.putExtra("quantite",dataholder.get(position).getQuantite());
                i.putExtra("numaffaire",dataholder.get(position).getNumaffaire());
                i.putExtra("tva",dataholder.get(position).getTva());


                context.startActivity(i);



            }
        });

    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }



    class myviewholder extends RecyclerView.ViewHolder {

        TextView firstname,lastname,date,total;
        Button buttonButton;
        int position;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.textViewFirstname);
            lastname=itemView.findViewById(R.id.textViewLastname);
            date=itemView.findViewById(R.id.textViewDate);
            total=itemView.findViewById(R.id.textViewTotal);






        }





    }

}
