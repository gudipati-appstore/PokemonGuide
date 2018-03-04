package com.gcoders.pokemon.pokemonguide.pokemonguide.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gcoders.pokemon.pokemonguide.pokemonguide.R;

import java.util.List;

/**
 * Created by kondareddygudipati on 1/21/18.
 */

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {

    private List<String> pokemonTypeList;
    private Context mContext;

    public PokemonTypeAdapter(Context mContext, List<String> pokemonTypeList) {
        this.mContext = mContext;
        this.pokemonTypeList = pokemonTypeList;
    }

    @Override
    public PokemonTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imageView = inflater.inflate(R.layout.button_pokemon_types, parent, false);

        return new MyViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(final PokemonTypeAdapter.MyViewHolder holder, int position) {
        final String pokemonType = pokemonTypeList.get(position);
        holder.pokemon_category_type.setText(pokemonType);
    }

    @Override
    public int getItemCount() {
        return pokemonTypeList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemon_category_type;

        MyViewHolder(View itemView) {
            super(itemView);
            pokemon_category_type = itemView.findViewById(R.id.pokemon_category_type);
        }
    }
}
