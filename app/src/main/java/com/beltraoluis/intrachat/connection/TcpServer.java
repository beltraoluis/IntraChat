package com.beltraoluis.intrachat.connection;

import android.util.Log;
import com.beltraoluis.intrachat.activity.MainActivity;
import com.beltraoluis.intrachat.connection.LineCode.NRZ;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpServer extends Thread {
    protected MainActivity control;
    protected int porta;
    protected boolean executar;
    protected ServerSocket servidor;

    public TcpServer(MainActivity control, int porta){
        this.control = control;
        this.porta = porta;
        executar = true;
        start();
    }

    public void run(){
        try {
            // Instancia o ServerSocket ouvindo a porta 5555
            servidor = new ServerSocket(porta);
            Log.i("TCP","Servidor ouvindo a porta " + porta);
            control.update("Servidor", "Servidor ouvindo a porta " + porta);
            while(executar) {
                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket cliente = servidor.accept();
                String ipCliente = cliente.getInetAddress().getHostAddress();
                System.out.println("Cliente conectado: " + ipCliente);
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                NRZ linha = (NRZ) entrada.readObject();
                String mensagem = linha.decode();
                entrada.close();
                try {
                    sleep(200);
                } catch (InterruptedException ex) {
                }
                control.update(ipCliente, mensagem);
                cliente.close();
            }
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parar(){
        try {
            servidor.close();
        } catch (IOException ex) {
        }
        executar = false;
        this.interrupt();
    }
}
