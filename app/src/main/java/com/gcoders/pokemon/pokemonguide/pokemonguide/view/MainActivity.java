package com.gcoders.pokemon.pokemonguide.pokemonguide.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gcoders.pokemon.pokemonguide.pokemonguide.PokemonGoApplication;
import com.gcoders.pokemon.pokemonguide.pokemonguide.R;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.Pokemon;
import com.gcoders.pokemon.pokemonguide.pokemonguide.storage.DataStore;
import com.gcoders.pokemon.pokemonguide.pokemonguide.util.PokemonUtil;
import com.gcoders.pokemon.pokemonguide.pokemonguide.view.adapter.PokemonListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recycler_view_pokemons;
    private TextView pokemon_category;
    private PokemonListAdapter adapter;
    private List<Pokemon> pokemonList;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pokemon_category = findViewById(R.id.pokemon_category);

        picasso = ((PokemonGoApplication) getApplication()).getPicassoInstance();

        initData();
        initAdapter();
    }

    private void initData() {
        pokemonList = ((PokemonGoApplication) getApplication()).getPokemonList();
    }

    private void initAdapter() {

        recycler_view_pokemons = findViewById(R.id.recycler_view_pokemons);

        adapter = new PokemonListAdapter(this, getPokemonList(), picasso, new PokemonListAdapter.ButtonClick() {
            @Override
            public void onButtonClick(Pokemon pokemon) {
                navigateToPokemonDetailPage(pokemon);
            }
        });
        /*if (PokemonUtil.isTablet(this)) {
            if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
                recycler_view_pokemons.setLayoutManager(new StaggeredGridLayoutManager(6, RecyclerView.VERTICAL));
            } else {
                recycler_view_pokemons.setLayoutManager(new StaggeredGridLayoutManager(10, RecyclerView.VERTICAL));
            }
        } else {
            if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
                recycler_view_pokemons.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
            } else {
                recycler_view_pokemons.setLayoutManager(new StaggeredGridLayoutManager(6, RecyclerView.VERTICAL));
            }
        }*/
        if (PokemonUtil.isTablet(this)) {
            if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
                recycler_view_pokemons.setLayoutManager(new GridLayoutManager(this, 6, RecyclerView.VERTICAL, false));
            } else {
                recycler_view_pokemons.setLayoutManager(new GridLayoutManager(this, 10, RecyclerView.VERTICAL, false));
            }
        } else {
            if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
                recycler_view_pokemons.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
            } else {
                recycler_view_pokemons.setLayoutManager(new GridLayoutManager(this, 6, RecyclerView.VERTICAL, false));
            }
        }
        recycler_view_pokemons.setHasFixedSize(true);
        recycler_view_pokemons.setDrawingCacheEnabled(true);
        recycler_view_pokemons.setAdapter(adapter);
    }


    private void navigateToPokemonDetailPage(Pokemon pokemonObj) {
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemonObj", pokemonObj);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private List<Pokemon> getPokemonList() {
        if (pokemonList == null) {
            pokemonList = (List<Pokemon>) DataStore.getInstance().getInfo("POKEMON_LIST_FROM_JSON");
            if (pokemonList == null) {
                return new ArrayList<>();
            }
        }
        return pokemonList;
    }

    private void navigateToAboutAppPage() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutapp) {
            navigateToAboutAppPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        handleNavigationDrawerClickEvents(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleNavigationDrawerClickEvents(int id) {

        switch (id) {
            case R.id.nav_All:
                updatePokemonList("All");
                break;

            case R.id.nav_Normal:
                updatePokemonList("Normal");
                break;
            case R.id.nav_Fighting:
                updatePokemonList("Fighting");
                break;
            case R.id.nav_Flying:
                updatePokemonList("Flying");
                break;
            case R.id.nav_Poison:
                updatePokemonList("Poison");
                break;
            case R.id.nav_Ground:
                updatePokemonList("Ground");
                break;
            case R.id.nav_Rock:
                updatePokemonList("Rock");
                break;

            case R.id.nav_Bug:
                updatePokemonList("Bug");
                break;
            case R.id.nav_Ghost:
                updatePokemonList("Ghost");
                break;
            case R.id.nav_Steel:
                updatePokemonList("Steel");
                break;
            case R.id.nav_Fire:
                updatePokemonList("Fire");
                break;
            case R.id.nav_Grass:
                updatePokemonList("Grass");
                break;
            case R.id.nav_Water:
                updatePokemonList("Water");
                break;

            case R.id.nav_Electric:
                updatePokemonList("Electric");
                break;
            case R.id.nav_Psychic:
                updatePokemonList("Psychic");
                break;
            case R.id.nav_Ice:
                updatePokemonList("Ice");
                break;
            case R.id.nav_Dragon:
                updatePokemonList("Dragon");
                break;
            case R.id.nav_Dark:
                updatePokemonList("Dark");
                break;
            case R.id.nav_Fairy:
                updatePokemonList("Fairy");
                break;
            default:
                updatePokemonList("All");

        }

    }

    private void updatePokemonList(String type) {
        pokemon_category.setText(type + " Pokemon");

        if (type.equalsIgnoreCase("All")) {
            adapter.updateList(getPokemonList());
            return;
        }

        List<Pokemon> pokemonList = getPokemonList();
        if (null != pokemonList && pokemonList.size() > 0) {
            List<Pokemon> newPokemonList = new ArrayList<>();
            for (Pokemon pokemon : pokemonList) {
                if (null != pokemon.getType() && pokemon.getType().size() > 0) {
                    if (pokemon.getType().contains(type)) {
                        newPokemonList.add(pokemon);
                    }
                }
            }
            if (newPokemonList.size() > 0) {
                adapter.updateList(newPokemonList);
            }
        }
    }

}
