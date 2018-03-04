package com.gcoders.pokemon.pokemonguide.pokemonguide.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcoders.pokemon.pokemonguide.pokemonguide.PokemonGoApplication;
import com.gcoders.pokemon.pokemonguide.pokemonguide.R;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.NextEvolution;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.Pokemon;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.PrevEvolution;
import com.gcoders.pokemon.pokemonguide.pokemonguide.view.adapter.PokemonTypeAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailActivity extends AppCompatActivity {


    private TextView pokemon_name_detail;
    private TextView text_pokemon_type_weak_agnst;
    private TextView text_pokemon_type_evolution_cycle;
    private RecyclerView rv_pokemon_type;
    private RecyclerView rv_pokemon_type_weak_agnst;
    private RecyclerView rv_pokemon_type_evolution_cycle;
    private ImageView pokemon_image_selected;

    private Pokemon pokemonObj;
    private Picasso picasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && null != bundle.getSerializable("pokemonObj")) {
            pokemonObj = (Pokemon) bundle.getSerializable("pokemonObj");
        }

        bindViews();
        bindData();
    }


    private void bindViews() {
        pokemon_name_detail = findViewById(R.id.pokemon_name_detail);
        pokemon_image_selected = findViewById(R.id.pokemon_image_selected);
        text_pokemon_type_weak_agnst = findViewById(R.id.text_pokemon_type_weak_agnst);
        text_pokemon_type_evolution_cycle = findViewById(R.id.text_pokemon_type_evolution_cycle);
        rv_pokemon_type = findViewById(R.id.pokemon_type);
        rv_pokemon_type_weak_agnst = findViewById(R.id.pokemon_type_weak_agnst);
        rv_pokemon_type_evolution_cycle = findViewById(R.id.pokemon_type_evolution_cycle);
    }

    private void bindData() {
        pokemon_name_detail.setText(pokemonObj.getName());

        addPokemonImage();

        handlePokemonTypeAdapter();
        handlePokemonWeakAgnstAdapter();
        handlePokemonEvolutionCycleAdapter();
    }

    private void addPokemonImage() {
        picasso = ((PokemonGoApplication)getApplication()).getPicassoInstance();
        picasso.load(pokemonObj.getImg()).into(pokemon_image_selected);
    }

    private void handlePokemonEvolutionCycleAdapter() {
        List<String> evolutionCycle = new ArrayList<>();
        if(null != pokemonObj.getPrevEvolution() && pokemonObj.getPrevEvolution().size() > 0){
            for(PrevEvolution prevEvolution : pokemonObj.getPrevEvolution()){
                evolutionCycle.add(prevEvolution.getName());
            }
        }
        evolutionCycle.add(pokemonObj.getName());

        if(null != pokemonObj.getNextEvolution() && pokemonObj.getNextEvolution().size() > 0){
            for(NextEvolution nextEvolution : pokemonObj.getNextEvolution()){
                evolutionCycle.add(nextEvolution.getName());
            }
        }
        if( evolutionCycle.size() > 1 ) {
            text_pokemon_type_evolution_cycle.setVisibility(View.VISIBLE);
            PokemonTypeAdapter adapter = new PokemonTypeAdapter(this, evolutionCycle);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rv_pokemon_type_evolution_cycle.setLayoutManager(layoutManager);
            rv_pokemon_type_evolution_cycle.setAdapter(adapter);
        }
    }

    private void handlePokemonWeakAgnstAdapter() {
        if( null != pokemonObj.getWeaknesses() && pokemonObj.getWeaknesses().size() > 0 ) {
            text_pokemon_type_weak_agnst.setVisibility(View.VISIBLE);
            PokemonTypeAdapter adapter = new PokemonTypeAdapter(this, pokemonObj.getWeaknesses());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rv_pokemon_type_weak_agnst.setLayoutManager(layoutManager);
            rv_pokemon_type_weak_agnst.setAdapter(adapter);
        }
    }

    private void handlePokemonTypeAdapter() {
        PokemonTypeAdapter adapter = new PokemonTypeAdapter(this, pokemonObj.getType());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_pokemon_type.setLayoutManager(layoutManager);
        rv_pokemon_type.setAdapter(adapter);
    }

}
