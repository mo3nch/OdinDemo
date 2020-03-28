package odinDemo.communication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.TickButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Future<String> future = new Communication().perform("Tick?");

                while(!future.isDone()) {
                    System.out.println("Waiting...");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    String result = future.get();

                    TextView tickView = (TextView) findViewById(R.id.TickView);
                    tickView.setText(result.substring(5));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

