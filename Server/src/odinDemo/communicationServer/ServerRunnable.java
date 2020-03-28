package odinDemo.communicationServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerRunnable implements Runnable {
    TickRunnable tickRunnable = null;
    Socket socket;
    BufferedWriter bw;
    BufferedReader br;

    ServerRunnable(Socket _socket, TickRunnable _tickRunnable)
    {
        this.socket = _socket;
        this.tickRunnable = _tickRunnable;

        try {
            bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

             br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;

        while (true)
        {
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("input = " + input);

            if (input == null || input.equals("quit"))
                break;

            if (input.equals("Tick?"))
            {
                System.out.println("A Client asked for the tick");

                try {
                    message = "Tick:" + tickRunnable.getTick();
                    System.out.println("Trying to send: " + message);

                    bw.write(message + "\n");
                    bw.flush();

                    System.out.println("Sending successful!");

                } catch (Exception e) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}
