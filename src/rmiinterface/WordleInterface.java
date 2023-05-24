package rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WordleInterface extends Remote{

    //public Palabra get_game_word() throws RemoteException;

    public String check_try(String word)throws RemoteException;
}
