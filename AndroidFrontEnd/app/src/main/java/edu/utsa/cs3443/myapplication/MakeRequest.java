package edu.utsa.cs3443.myapplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MakeRequest{

    //Has the ability to run our 'Runnable' tasks
    private ExecutorService executorService;
    //A single task created for the purpose of making get request
    private Runnable getRequest;
    //Needed for editing our view itself
    private MainActivity mainActivity;
    private String URL;
    //Constructor which takes an instance of our mainactivity and a URL to navigate to
    public MakeRequest(MainActivity mainActivity,String URL)  {
        this.URL = URL;
        this.mainActivity = mainActivity;
        //Init out single thread for our executorserive, but can use a thread pool if need be
        this.setExecutorService(Executors.newSingleThreadExecutor());
        this.setGetRequest();
    }

    public void setGetRequest(){
        this.getRequest = () -> {
            String result;
            //Surrounded as we need to be able to catch any errors that would go arry with making the rquest itself
            try{
                //Constructs the URL itself and will throw an exception of the
                java.net.URL url = new URL(URL);
                //Creates connection
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //Specifies what kind of method this will be
                con.setRequestMethod("GET");
                //Checks if connection went with an HTTP_OK (200) or not.
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
                   BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                   String input;
                   StringBuilder content = new StringBuilder();
                   while ((input = bf.readLine()) != null){
                       content.append(input);
                   }
                   bf.close();
                   result = String.valueOf(content);
                }
                else{
                    result = "FAILED:(";
                    System.out.println("NO!");
                }
                //This has to be switched back to main thread as view elements can only be changed by main thread!
                mainActivity.runOnUiThread(()->{
                    mainActivity.setResult(result);
                });
            }
            //In case any sort of hell breaks loose, this will be our harness
            catch(Exception e){
                mainActivity.setResult("ERROR");
                e.printStackTrace();
            }
        };
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Runnable getGetRequest() {
        return getRequest;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
