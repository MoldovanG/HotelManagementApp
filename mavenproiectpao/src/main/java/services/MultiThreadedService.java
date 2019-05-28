package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Multi Threaded service to be used to read a number of given files
public class MultiThreadedService extends Thread {
    private List<String> paths;
    private List<String>result=new ArrayList<>();
    public MultiThreadedService(List<String> paths) {
        super();
        this.paths = paths;
    }

    @Override
    public void run() {
        List<Thread> references = new ArrayList<>();
        for (String path : paths){
            Thread th = new Thread(new ReadFile(path));
            references.add(th);
            th.start();
        }
        for (Thread th : references) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    class ReadFile implements Runnable{
        String path= null;
        public ReadFile(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            try(BufferedReader rd = Files.newBufferedReader(Paths.get(path))){
                String line = rd.readLine();
                while (line != null){
                    result.add(line);
                    line = rd.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
