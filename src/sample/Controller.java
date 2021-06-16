package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

public class Controller {
    public ListView newsList;
    public Label addNewsErrorText;
    public TextArea newsInput;

    @FXML
    public void initialize(){
        News.loadNews();
        updateList();
    }

    public void updateList(){
        News[] Newsarr = News.News.toArray(new News[0]);
        newsList.getItems().clear();
        for(News n : Newsarr){
            newsList.getItems().add(n.content);
        }
    }

    public void onPublishClick(ActionEvent actionEvent) {
        Date d = new Date(); //Date initialises to now.
        if(newsInput.getText().isBlank()){
            addNewsErrorText.setText("Please add content to your news");
            return;
        }
        News.addNews(new News(d, newsInput.getText()));
        News.saveNews();
        updateList();
    }
}
