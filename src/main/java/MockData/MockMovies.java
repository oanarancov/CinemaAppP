package MockData;

import Models.MovieModel;
import Models.ReservationState;

import java.util.ArrayList;

public class MockMovies {
    public static ArrayList<MovieModel> pendingMovies
            = new ArrayList<MovieModel>() {
        {
            add(new MovieModel("Movie1", "20", "Great one, you gonna love it", ReservationState.ACCEPTED));
            add(new MovieModel("Movie2", "19", "continut2", ReservationState.ACCEPTED));
            add(new MovieModel("Movie3", "20", "continut3", ReservationState.ACCEPTED));
            add(new MovieModel("Movie4", "21", "continut4", ReservationState.ACCEPTED));
            add(new MovieModel("Movie5", "22", "continut4", ReservationState.ACCEPTED));
            add(new MovieModel("Movie6", "19", "continut4", ReservationState.ACCEPTED));
            add(new MovieModel("Movie7", "20", "continut4", ReservationState.ACCEPTED));

        }
    };

    public static ArrayList<MovieModel> acceptedMovies
            = new ArrayList<MovieModel>() {
        {
            add(new MovieModel("Movie10", "20", "It was so funny tho", ReservationState.ACCEPTED));
            add(new MovieModel("Movie11", "19", "rofl", ReservationState.ACCEPTED));
            add(new MovieModel("Movie12", "22", "lmao", ReservationState.ACCEPTED));
            add(new MovieModel("Movie13", "19", "omg", ReservationState.ACCEPTED));
            add(new MovieModel("Movie14", "21", "clap clap", ReservationState.ACCEPTED));
            add(new MovieModel("Movie15", "21", "good one", ReservationState.ACCEPTED));
            add(new MovieModel("Movie16", "20", "almost died...laughing", ReservationState.ACCEPTED));
            add(new MovieModel("Movie16", "18", "fun", ReservationState.ACCEPTED));
            add(new MovieModel("Movie16", "19", "quite the ride", ReservationState.ACCEPTED));

        }
    };
}
