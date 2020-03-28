package odinDemo.communicationServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static Integer currentTick = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket seso = new ServerSocket(3333);
        TickRunnable tr = new TickRunnable();
        Thread tt = new Thread(tr);
        tt.start();

        while (true)
        {
             System.out.println("Waiting for client ...");

            Socket socket = seso.accept();

            System.out.println("Client connected ...");

            Thread ts = new Thread ( new ServerRunnable(socket, tr));

            ts.start();
        }
    }
}
