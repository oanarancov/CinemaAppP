package View;

import Models.MovieModel;
import Models.ReservationState;
import Services.DatabaseService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FirstPageAdmin {
    private static VBox CurrentLayout;
    private static VBox myMovieView, myContentView;
    private static ArrayList<MovieModel> myMovies = new ArrayList<MovieModel>();
    private static String CurrentUserName;

    static void display(String username) {

        Stage window = new Stage();
        window.setTitle("Pagina principala ADMIN " + username);
        CurrentLayout = new VBox();
        Scene scene = new Scene(CurrentLayout, 500, 700);
        window.setScene(scene);
        window.show();

        CurrentUserName=username;

        onCreate();
    }

    private static void onCreate() {
        //TODO initiez listele cu movies
        //myMovies.addAll(DatabaseService.getUserMovies(CurrentUserName));

        //setPending List
        myMovieView = new VBox();
        myMovieView.setSpacing(10);

        myContentView =new VBox();
        myContentView.setPrefWidth(500);

        ScrollPane myScroll;
        myScroll = new ScrollPane();
        myScroll.setContent(myMovieView);
        myScroll.setFitToWidth(true);

        Button addButton = new Button("Add");
        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                refreshList();
            }
        });

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(addButton,refreshButton);
        addButton.setAlignment(Pos.CENTER);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                AddNewMovieView.display(CurrentUserName);
            }
        });

        Label movieViewTitle = new Label("My movie list");
        movieViewTitle.setAlignment(Pos.CENTER);

        myMovieView.setAlignment(Pos.CENTER);
        CurrentLayout.setAlignment(Pos.CENTER);

        myMovieView.getChildren().addAll(movieViewTitle, myContentView);
        createMyMoviesList();
        buttonsHBox.setAlignment(Pos.CENTER);
        CurrentLayout.getChildren().addAll(buttonsHBox,myScroll);

    }
    private static void refreshList(){
        myContentView.getChildren().clear();
        myMovies.clear();
        myMovies.addAll(DatabaseService.getUserMovies(CurrentUserName));
        createMyMoviesList();
    }

    private static void createMyMoviesList() {
        myContentView.getChildren().clear();
        for (int i = 0; i< myMovies.size(); i++){
            myContentView.getChildren().add(createMyMovieCell(myMovies.get(i)));
        }
    }

    private static VBox createMyMovieCell(final MovieModel movie) {
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n";

        VBox cellParent = new VBox();
        cellParent.setPadding(new Insets(10, 10, 10, 10));
        cellParent.setStyle(cssLayout);
        cellParent.setSpacing(20);

        VBox detailBox = new VBox();
        detailBox.setPrefWidth(500);
        detailBox.setSpacing(5);

        Label titleLabel = new Label(movie.getPrice() + ":" + movie.getName());
        Label descLabel = new Label(movie.getDescription());

        String state="Pending";
        if(movie.getReservationState().equals(ReservationState.ACCEPTED))
        {
            state="Accepted";
        }
        if(movie.getReservationState().equals(ReservationState.DECLINED))
        {
            state="Declined";
        }
        Label movieStateLabel=new Label("Movie state : "+state);

        descLabel.setWrapText(true);
        descLabel.setPrefWidth(500);
        descLabel.setTextAlignment(TextAlignment.JUSTIFY);
        detailBox.getChildren().addAll(movieStateLabel, titleLabel, descLabel);

        HBox actionBox = new HBox();
        actionBox.minWidth(60);
        actionBox.setSpacing(5);

        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (movie.getReservationState() == ReservationState.ACCEPTED)      //daca amovie a fost deja acceptat, nu se admin modificari
                {
                    AlertBox.display("Warning", "Filmul a fost deja publicat!");
                } else {
                    EditMovieView.display(movie);
                }
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (movie.getReservationState() == ReservationState.ACCEPTED)      //daca movie a fost deja acceptat, nu se admin modificari
                {
                    AlertBox.display("Warning", "Filmul a fost deja publicat!");
                } else {
                    DatabaseService.deleteMovies(movie);
                    refreshList();

                }

            }
        });

        actionBox.getChildren().addAll(editButton, deleteButton);
        actionBox.setAlignment(Pos.CENTER);

        cellParent.getChildren().addAll(detailBox, actionBox);
        return cellParent;
    }
}
