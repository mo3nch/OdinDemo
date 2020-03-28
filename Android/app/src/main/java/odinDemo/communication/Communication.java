package odinDemo.communication;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Communication {
    private ExecutorService executor
            = Executors.newSingleThreadExecutor();


    Future<String> perform(final String request)
    {
        return executor.submit(() -> {

            Socket socket = null;
            String reply = null;
            try {
                socket = new Socket("192.168.1.11", 3333);

                System.out.println("Socket connected");

                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                System.out.println("Trying to send: " + request);
                bw.write(request + "\n");
                bw.flush();

                reply = br.readLine();
                System.out.println("Received: " + reply);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return reply;
        });
    }

}
