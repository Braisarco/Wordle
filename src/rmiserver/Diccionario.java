package rmiserver;

import java.util.*;

public class Diccionario {

    private static HashMap<Character, LinkedList<Palabra>> diccionario = new HashMap<Character, LinkedList<Palabra>>();


    public void add_palabra(Palabra word, Character key) {
        if (diccionario.containsKey(key)){
            diccionario.get(key).add(word);
        }else{
            diccionario.put(key, new LinkedList<Palabra>());
            diccionario.get(key).add(word);
        }
    }

    public LinkedList get (Character key){
        return diccionario.get(key);
    }

    public Palabra seleccionar_palabra (){
     
        Random r = new Random();
        Random r2 = new Random();
        char key = (char)(r.nextInt(26) + 'A');
        int value = r2.nextInt(diccionario.get(key).size())+1;

        return diccionario.get(key).get(value);
    }

    public Palabra buscar_palabra(Palabra word){
        
        return word;

    }
}
 