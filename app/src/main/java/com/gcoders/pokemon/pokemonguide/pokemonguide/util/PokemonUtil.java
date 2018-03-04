package com.gcoders.pokemon.pokemonguide.pokemonguide.util;

import android.content.Context;
import android.content.res.Configuration;


import com.gcoders.pokemon.pokemonguide.pokemonguide.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by kondareddygudipati on 2/24/18.
 */

public class PokemonUtil {
    public static String getStringFromFile(Context context){

        InputStream inputStream = context.getResources().openRawResource(R.raw.pokemon_completelist);

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));

            int numOfChars;
            while((numOfChars = reader.read(buffer)) != -1){
                writer.write(buffer, 0, numOfChars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
