package com.beltraoluis.intrachat.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author beltraoluis
 */
public class TcpClient {
    public static Socket start() {
        try {
            Socket client = new Socket("127.0.0.1",5555);
            ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
            saida.flush();
            saida.writeObject("isso foi a menssagem");
            saida.close();
            return client;
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
