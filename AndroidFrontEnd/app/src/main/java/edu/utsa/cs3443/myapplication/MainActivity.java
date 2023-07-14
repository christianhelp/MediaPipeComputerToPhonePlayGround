package edu.utsa.cs3443.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Put this kind of just out here as our class has a function for it and this has worked fine in the past
    TextView getResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tie our button and textview to their respective IDs
        getResults = findViewById(R.id.GET_RESULTS);
        Button requestButton = findViewById(R.id.requestButton);
        // Inits out makerequest object
        // You will notice that the IP is hardcoded and looks a bit odd. This is our local machine's IP.
        // Using the normal 'http://127.0.0.1:5000' will not work as that refer to the emulator itself, use the ip 10.0.2.2 for the ip

        // If we want our phones to connect to the server, we need to change the ip to connect to a special port (check server's value of 0.0.0.0)
        MakeRequest makeRequest = new MakeRequest(this,"http://192.168.86.20:5000/");

        // Made a little inline function for our button's onclick function and made it a lambda for style points.
        // All this does is tell the user that a connection is being attempted and afterwards, the text above the button will update showing a result.
        // The .submit function simply sends the executor service our task that we want it to work with
        requestButton.setOnClickListener(view -> {
            makeRequest.getExecutorService().submit(makeRequest.getGetRequest());
            Toast t = Toast.makeText(view.getContext(),"Attempting to connect...",Toast.LENGTH_SHORT);
            t.show();
        });
    }
    // Function called by our makeRequest object after attempting a connection.
    public void setResult(String result){
        getResults.setText(getString(R.string.changed,result));
    }
}