package com.beltraoluis.intrachat.connection;

import android.util.Log;
import com.beltraoluis.intrachat.activity.MainActivity;
import com.beltraoluis.intrachat.connection.LineCode.NRZ;
import com.beltraoluis.intrachat.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpServer extends Thread {
    protected MainActivity control;
    protected int porta;
    protected boolean execute;
    protected ServerSocket server;

    public TcpServer(MainActivity control, int porta){
        this.control = control;
        this.porta = porta;
        execute = true;
        start();
    }

    public void run(){
        try {
            server = new ServerSocket(porta);
            Log.i("TCP","Servidor ouvindo a porta " + porta);
            while(execute) {
                Socket client = server.accept();
                String ipCliente = client.getInetAddress().getHostAddress();
                Log.i("TCP","Cliente conectado: " + ipCliente);
                ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
                String linha = (String) entrada.readObject();
                Log.i("TCP",linha);
                entrada.close();
                try {
                    sleep(200);
                } catch (InterruptedException ex) {
                }
                control.update(ipCliente, linha);
                client.close();
            }
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer(){
        try {
            server.close();
        } catch (IOException ex) {
        }
        execute = false;
        this.interrupt();
    }
}
