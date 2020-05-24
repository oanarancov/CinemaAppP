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


public class EditMovieView {
    private static VBox CurrentLayout;
    private static Stage window;
    private static MovieModel CurrentMovie;
    public static void display(MovieModel movieModel){
        window = new Stage();
        window.setTitle("Fereastra Editare ADMIN " + movieModel.getPrice());
        CurrentMovie = movieModel;
        CurrentLayout = new VBox();
        Scene scene = new Scene(CurrentLayout, 400, 400);
        window.setScene(scene);
        window.show();

        onCreate();
    }

    private static void onCreate() {
        //TODO initiez listele cu movies
        Label titleLabel = new Label("Title:");
        final TextField titleTextField = new TextField();
        titleTextField.setText(CurrentMovie.getName());

        Label contentLabel = new Label("Continut:");
        final TextArea contentTextArea = new TextArea();
        contentTextArea.setText(CurrentMovie.getDescription());

        Button saveMovieButton = new Button("Salveaza modificarile");
        saveMovieButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (titleTextField.getText().isEmpty() || contentTextArea.getText().isEmpty()) {
                    AlertBox.display("Warning", "Please complete all fields");
                    return;
                }
                CurrentMovie.setName(titleTextField.getText());
                CurrentMovie.setDescription(contentTextArea.getText());
                CurrentMovie.setReservationState(ReservationState.PENDING);

                DatabaseService.editMovie(CurrentMovie);

                window.close();

            }
        });

        contentTextArea.setPrefHeight(300);
        contentTextArea.setPrefWidth(300);
        contentTextArea.setWrapText(true);

        HBox titleHBox = new HBox();
        HBox contentHBox = new HBox();
        HBox buttonBox = new HBox();
        titleHBox.getChildren().addAll(titleLabel,titleTextField);
        contentHBox.getChildren().addAll(contentLabel,contentTextArea);
        buttonBox.getChildren().add(saveMovieButton);
        CurrentLayout.getChildren().addAll(titleHBox,contentHBox,buttonBox);
        CurrentLayout.setPadding(new Insets(10,10,10,10));
        buttonBox.setAlignment(Pos.CENTER);
        CurrentLayout.setSpacing(10);

    }

}
