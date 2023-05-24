package rmiserver;

public class Palabra {
    private String word;
    private int id;

    public Palabra(String palabra, int num){
        this.word = palabra;
        this.id = num;
    }

    public void modificarPalabra (String palabra, int num){
        this.word = palabra;
        this.id = num;
    }

    public boolean equals (Palabra word_to_compare){
        if(this.word.equals(word_to_compare.getWord())){
            return true;
        }
        return false;
    }

    public String comparar_palabras(Palabra word_to_compare){
        char resultado[] = {'_','_','_','_','_'};
        String palabra_comparar = word_to_compare.getWord();

        for(int i=0; i<5; i++){
            if(this.word.charAt(i)==palabra_comparar.charAt(i)){
                resultado[i]= '.';
            }else {
                for (int j = 0; j < 5; j++) {
                    if(palabra_comparar.charAt(i)==this.word.charAt(j)){
                        resultado[i] = '*';
                    }
                }
            }
        }
        String toReturn = new String(resultado);
        return toReturn;
    }

    public String getWord(){
        return this.word;
    }

    public int getId(){
        return this.id;
    }

    public void setWord(String palabra){
        this.word = palabra;
    }

    public void setId(int num){
        this.id = num;
    }

    public String toString(){
        return word;
    }
}
