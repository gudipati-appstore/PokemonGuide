package com.gcoders.pokemon.pokemonguide.pokemonguide.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcoders.pokemon.pokemonguide.pokemonguide.R;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kondareddygudipati on 1/21/18.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private List<Pokemon> pokemonList;
    private Context mContext;
    private ButtonClick buttonClick;
    private Picasso picasso;

    public PokemonListAdapter(Context mContext, List<Pokemon> pokemonList, Picasso picasso, ButtonClick buttonClick) {
        this.mContext = mContext;
        this.pokemonList = pokemonList;
        this.buttonClick = buttonClick;
        this.picasso = picasso;
    }

    @Override
    public PokemonListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imageView = inflater.inflate(R.layout.card_pokemon_name_image, parent, false);

        return new MyViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(final PokemonListAdapter.MyViewHolder holder, int position) {
        final Pokemon pokemonCharacter = pokemonList.get(position);

        holder.pokemon_name.setText(pokemonCharacter.getName());

        picasso.load(pokemonCharacter.getImg()).into(holder.pokemon_image);


        holder.pokemon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick.onButtonClick(pokemonCharacter);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void updateList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }


    public interface ButtonClick {
        void onButtonClick(Pokemon pokemonCharacter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemon_image;
        private TextView pokemon_name;

        MyViewHolder(View itemView) {
            super(itemView);
            pokemon_image = itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
