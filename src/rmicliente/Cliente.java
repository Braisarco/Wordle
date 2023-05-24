package rmicliente;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

import rmiinterface.*;

public class Cliente {
    public static void main(String[] args) {
        
    try {
        WordleInterface obj = (WordleInterface) Naming.lookup("//localhost:1099/Manolo");

        int num_intentos = 0;
        boolean gg = false;
        System.out.println("_ -> La letra no esta \n * -> La letra no esta en esa posicion \n . -> La letra es correcta");
        Scanner buf = new Scanner(System.in);

        while (num_intentos<6){
            String intento;
            intento = buf.nextLine().toUpperCase();

            if(intento.length()!=5){
                System.out.println("La palabra debe tener 5 letras");
            }else{
                System.out.println(obj.check_try(intento));
                if (obj.check_try(intento).equals(".....")) {
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
        
        
        } catch(MalformedURLException e){
            System.out.println("URL mala");
        }catch (RemoteException e) {
            System.out.println("Host not reacheable");
        }catch (NotBoundException e){
            System.out.println("Not found, register name");
        }
    }
}
