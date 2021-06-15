package sample;

import java.io.*;

import java.util.Date;
import java.util.PriorityQueue;

public class News implements Serializable {
    static PriorityQueue<News> News = new PriorityQueue<News>(){};
    static private final File file = new File("News.ser");
    public Date publishDate;
    public String content;

    public static PriorityQueue<News> loadNews(){
        try{
            FileInputStream finstr = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(finstr);
            PriorityQueue<News> out = (PriorityQueue<News>)in.readObject();
            in.close();
            return out;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void saveNews(){
        try{
            FileOutputStream foutstr = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(foutstr);
            out.writeObject(News);
            out.flush();
            out.close();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
