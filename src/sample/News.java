package sample;

import java.io.*;

import java.util.Date;
import java.util.PriorityQueue;

public class News implements Serializable, Comparable<News> {
    static PriorityQueue<News> News = new PriorityQueue<News>(){};

    public Date publishDate;
    public String content;

    //define file news is saved to
    static private final File file = new File("News.ser");

    public static void loadNews(){
        //check news file exists; if not, create it.
        if(file.exists()){
            //boilerplate stream read
            try {
                FileInputStream finstr = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(finstr);
                PriorityQueue<News> out = (PriorityQueue<News>) in.readObject();
                in.close();
                News = out;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            saveNews();
        }
    }

    public static void saveNews(){
        //boilerplate stream write
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

    public static void addNews(News n){
        News.add(n);
    }

    public News(Date publishDate, String content) {
        this.publishDate = publishDate;
        this.content = content;
    }

    //comparator wrapping inverted date comparator; java heaps are minheaps, maxheap is desired.
    @Override
    public int compareTo(sample.News o) {
        return -publishDate.compareTo(o.publishDate);
    }
}
