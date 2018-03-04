package com.gcoders.pokemon.pokemonguide.pokemonguide;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.gcoders.pokemon.pokemonguide.pokemonguide.model.Pokemon;
import com.gcoders.pokemon.pokemonguide.pokemonguide.model.PokemonList;
import com.gcoders.pokemon.pokemonguide.pokemonguide.storage.DataStore;
import com.gcoders.pokemon.pokemonguide.pokemonguide.util.PokemonUtil;
import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

/**
 * Created by kondareddygudipati on 2/24/18.
 */

public class PokemonGoApplication extends Application {
    private static OkHttpClient okHttpClient;
    private static Picasso picassoInstance;
    private static List<Pokemon> pokemonList;


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        getOkhttpInstance();
        getPicassoInstance();
        initPokemonData();

    }

    public OkHttpClient getOkhttpInstance() {

        if (okHttpClient == null) {
            okHttpClient = initOkHttpClient();
        }
        return okHttpClient;

    }

    public Picasso getPicassoInstance() {
        if (picassoInstance == null) {
            picassoInstance = initPicassoInstance();
            Picasso.setSingletonInstance(picassoInstance);
        }
        return picassoInstance;
    }

    public List<Pokemon> getPokemonList() {
        if (pokemonList == null) {
            pokemonList = initPokemonData();
        }
        return pokemonList;
    }


    private OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectionSpecs(getUnAuthUnEncryptedSpec())
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(createHttpClientCache())
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    private Cache createHttpClientCache() {
        int cacheSize = 10 * 1024 * 1024;
        File cacheDir = new File("CacheResponse.tmp");
        return new Cache(cacheDir, cacheSize);
    }

    private ConnectionSpec getHttpsConnectionSpecInstance() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();

    }

    private List<ConnectionSpec> getUnAuthUnEncryptedSpec() {
        return Arrays.asList(
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS,
                ConnectionSpec.CLEARTEXT);
    }

    private Picasso initPicassoInstance() {
        OkHttpClient.Builder okhttpclient = okHttpClient.newBuilder();
        return new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okhttpclient.build()))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("Picasso", "error " + uri + " - " + exception);
                    }
                })
                .build();
    }

    private List<Pokemon> initPokemonData() {
        String data = PokemonUtil.getStringFromFile(this);

        Gson gson = new Gson();
        PokemonList pokemonListObj = gson.fromJson(data, PokemonList.class);
        if (null != pokemonListObj && null != pokemonListObj.getPokemon() && pokemonListObj.getPokemon().size() > 0) {
            pokemonList = pokemonListObj.getPokemon();
            DataStore.getInstance().setInfo("POKEMON_LIST_FROM_JSON", pokemonListObj.getPokemon());
        }
        return pokemonList;
    }
}
