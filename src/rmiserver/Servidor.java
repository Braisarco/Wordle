package rmiserver;

import java.io.*;
import java.util.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiinterface.WordleInterface;

public class Servidor implements WordleInterface{

    private static Palabra winner_word;
    private static Diccionario diccionario;

    public Servidor() throws IOException{
        String s1;
        String s2;

        Servidor.diccionario = new Diccionario();
 
        // Cargamos el buffer con el contenido del archivo    
        BufferedReader br = new BufferedReader (new FileReader ("Diccionario.txt"));
       
        // Leemos la primera linea
        s1 = br.readLine();
 
        int numTokens = 0;
        StringTokenizer st = new StringTokenizer (s1);
 
        // bucle por todas las palabras
        while (st.hasMoreTokens())
        {
            s2 = st.nextToken();
            numTokens++;
            diccionario.add_palabra(new Palabra(s2, numTokens), s2.charAt(0));
        }
    }

    public static void main (String[] args) throws java.io.IOException
    {
        //start server
        Timer temporizador = new Timer();
        Servidor server = new Servidor();
        try{
            Remote stub = UnicastRemoteObject.exportObject(server, 0);
            Naming.bind("//localhost:1099/Manolo", stub);
            System.out.println("Tamoh bien");

        }catch(MalformedURLException e){
            System.out.println("URL mala");

        }catch(RemoteException e) {
            System.out.println("Host not reacheable");

        }catch(AlreadyBoundException e){
            System.out.println("Not found, register name");
        }
        server.setWinnerWord(server.diccionario.seleccionar_palabra());

        /*Game
        int num_intentos = 0;
        boolean gg = false;
        System.out.println("_ -> La letra no esta \n * -> La letra no esta en esa posicion \n . -> La letra es correcta");
        Palabra objetivo = diccionario.seleccionar_palabra();
        System.out.println(objetivo);

        while (num_intentos<6){
            String intento;
            Scanner buf = new Scanner(System.in);
            intento = buf.nextLine().toUpperCase();

            if(intento.length()!=5){
                System.out.println("La palabra debe tener 5 letras");
            }else{
                System.out.println(objetivo.comparar_palabras(new Palabra(intento,1)));
                if (objetivo.comparar_palabras(new Palabra(intento,1)).equals(".....")) {
                    System.out.println("Correcto");
                    gg = true;
                    num_intentos = 6;
                } else {
                    num_intentos++;
                }  
            }  
        }  
        if(!gg){
            System.out.println("Perdestes");
        }
        */  
    }

    /*
    public Palabra get_game_word(){
        return Servidor.winner_word;
    }
    */

    public String check_try(String word){
        Palabra check = new Palabra(word,0);
        return winner_word.comparar_palabras(check);
    }

    public void setWinnerWord (Palabra word){
        Servidor.winner_word = word;
    }
}