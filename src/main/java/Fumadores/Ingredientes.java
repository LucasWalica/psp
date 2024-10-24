package Fumadores;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredientes {

    private ArrayList<String> ingredientes = new ArrayList<>(Arrays.asList("tabaco", "papel", "pipa"));
    public ArrayList<String> ingredientesNoTocable = new ArrayList<>(Arrays.asList("tabaco", "papel", "pipa"));


    Ingredientes(){};

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public String getRandomIngrediente(){
        String ing = ingredientes.get((int) (Math.random()*this.ingredientes.size()));
        ingredientes.remove(ing);
        return ing;
    }

    public void initializeIngredientes(){
        this.ingredientes = new ArrayList<>(Arrays.asList("tabaco", "papel", "pipa"));
    }
}