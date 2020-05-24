package View;
import Models.MovieModel;
import Models.ReservationState;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AddReservationView {
    private static VBox CurrentLayout;
    private static String CurrentUser;
    private static Stage window;
    public static void display(String username){
        window = new Stage();
        window.setTitle("Reservation client " + username);
        CurrentUser=username;
        CurrentLayout = new VBox();
        Scene scene = new Scene(CurrentLayout, 400, 400);
        window.setScene(scene);
        window.show();

        onCreate();
    }

    private static void onCreate() {
        //TODO initiez listele cu movies
        Label titleLabel = new Label("Movie name:");
        final TextField titleTextField = new TextField();
        Label contentLabel = new Label("Choose day, time, seat: ");
        contentLabel.setMinSize(150,100);
        final TextArea contentTextArea = new TextArea();
        contentTextArea.setPrefSize(50, 50);
        Button saveMovieButton = new Button("Save");
        saveMovieButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (titleTextField.getText().isEmpty() || contentTextArea.getText().isEmpty()) {
                    AlertBox.display("Warning", "Please complete all fields");
                    return;
                }
                MovieModel movie = new MovieModel(CurrentUser, titleTextField.getText(), contentTextArea.getText(), ReservationState.PENDING);
                DatabaseService.addReservation(movie);
                window.close();

            }
        });

        contentTextArea.setPrefHeight(300);
        contentTextArea.setPrefWidth(300);
        contentTextArea.setWrapText(true);

        HBox titleHBox = new HBox();
        HBox contentHBox = new HBox();
        HBox buttonsBox = new HBox();
        titleHBox.getChildren().addAll(titleLabel,titleTextField);
        contentHBox.getChildren().addAll(contentLabel,contentTextArea);
        buttonsBox.getChildren().add(saveMovieButton);
        CurrentLayout.getChildren().addAll(titleHBox,contentHBox,buttonsBox);
        CurrentLayout.setPadding(new Insets(10,10,10,10));
        buttonsBox.setAlignment(Pos.CENTER);
        CurrentLayout.setSpacing(10);

    }

}
