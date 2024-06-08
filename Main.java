//IMPORTED PACKAGES
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    //CONSTANTS
    private static final NewItem[] NEW_ITEMS = {
            new NewItem("Alecs Ice Cream", "Alecs.jpg", 1),
            new NewItem("Fly By Jing", "Fly By Jing.jpg", 2),
            new NewItem("Kate's Real Food", "Kates.jpg", 8),
            new NewItem("Lightlife Foods", "Lightlife.jpg", 9),
            new NewItem("Paqui", "Paqui.jpg", 8),
            new NewItem("Rise", "Rise.jpg", 9),
    };

    //CLASS-LEVEL VARIABLES; DECLARE GUI COMPONENTS (STEP 1)
    private Label messageLabel;
    private ImageView itemImageView;
    private Button nextItemButton;
    private ProgressBar queueProgressBar;
    private int count = 0;
    private NewItem[] shuffledItems = Main.shuffleItems(NEW_ITEMS);

    private static NewItem[] shuffleItems(NewItem[] items){
        NewItem[] copy = items.clone();
        List<NewItem> list = Arrays.asList(copy);
        Collections.shuffle(list);
        list.toArray(copy);
        return copy;
    }

  //GUI methods
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //INSTANTIATE COMPONENTS (STEP 2)
        Label titleLabel = new Label();
        itemImageView = new ImageView();
        messageLabel = new Label();
        nextItemButton = new Button();
        queueProgressBar = new ProgressBar();
        //0.0 allows us to set the progress bar to 0%

        //SET COMPONENT PROPERTIES (STEP 3)
        titleLabel.setText("Item Maintenance Portal:");
        titleLabel.setFont(new Font(18.0));
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        NewItem tempItem = new NewItem();
        itemImageView.setImage(tempItem.getImage());
        itemImageView.setFitWidth(300);
        itemImageView.setPreserveRatio(true);

        messageLabel.setText("Click the button to see the next new item choice. The progress bar will indicate how far into the queue you are.");
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        nextItemButton.setText("Next New Item");

        queueProgressBar.setProgress(0.0);


        //SETUP LAYOUT (STEP 4)
        VBox mainLayout = new VBox();

        mainLayout.getChildren().addAll(titleLabel, itemImageView, messageLabel, nextItemButton, queueProgressBar);
        mainLayout.setAlignment(Pos.CENTER);

        //ADD EVENT HANDLING (STEP 6)
        nextItemButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   //int itemNum = (int) (Math.random() * NEW_ITEMS.length); (random 0-3)
                   //System.out.println("Click next button, index:" + itemNum); (checking next button is working)
                   if(count == NEW_ITEMS.length) {
                       //end game condition
                       queueProgressBar.setStyle("-fx-accent: red");
                       nextItemButton.setDisable(true);
                       messageLabel.setText("New Item Presentation over! All queue items have been setup!");
                       itemImageView.setImage(new NewItem().getImage());
                       // ^anonymous object
                   } else {
                       NewItem nextItem = shuffledItems[count];
                       count ++;
                       //System.out.println("Something happened #" + count);

                       //change GUI based on next item 
                       queueProgressBar.setProgress(count/(double)NEW_ITEMS.length);
                       itemImageView.setImage(nextItem.getImage());
                       messageLabel.setText(nextItem.getItemName());
                   }
               }
           }
        );

        //SETUP SCENE AND SHOW (STEP 5)
        Scene scene = new Scene(mainLayout, 600, 600); //350,500
        primaryStage.setScene(scene);
        primaryStage.setTitle("Item Maintenance Queue");
        primaryStage.show();
    }

}
