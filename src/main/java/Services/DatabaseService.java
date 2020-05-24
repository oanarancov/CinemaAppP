package Services;

import Models.MovieModel;
import View.AlertBox;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class DatabaseService {
    private static String moviesFile ="movies.json";
    private static String reservationsFile = "reservations.json";

    public static void addMovie(MovieModel movie){


        try  {
            ArrayList<MovieModel>
                    allMovies= getAllMovies();

            FileWriter file = new FileWriter(moviesFile);

            if(allMovies==null) {

                allMovies=new ArrayList<MovieModel>();
            }
            allMovies.add(movie);
            String movieJson=new Gson().toJson(allMovies);
            file.write(movieJson);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addReservation(MovieModel movie){


        try  {
            ArrayList<MovieModel>
                    allMovies= getAllMovies();

            FileWriter file = new FileWriter(reservationsFile);

            if(allMovies==null) {

                allMovies=new ArrayList<MovieModel>();
            }
            allMovies.add(movie);
            String movieJson=new Gson().toJson(allMovies);
            file.write(movieJson);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<MovieModel> getAllMovies(){
        Gson gson = new Gson();

        try{
            FileReader reader = new FileReader(moviesFile);
            ArrayList<MovieModel> allMovies = gson.fromJson(reader,new TypeToken<ArrayList<MovieModel>>() {}.getType());
            return allMovies;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static ArrayList<MovieModel> getUserMovies(String userName){
        Gson gson = new Gson();
        ArrayList<MovieModel> userMovies= new ArrayList<>();
        try{
            FileReader reader = new FileReader(moviesFile);
            ArrayList<MovieModel> allMovies = gson.fromJson(reader,new TypeToken<ArrayList<MovieModel>>() {}.getType());
            for(int i=0;i<allMovies.size();i++)
            {
                if(allMovies.get(i).getPrice().equals(userName))
                {
                    userMovies.add(allMovies.get(i));
                }
            }
            return userMovies;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static void editMovie(MovieModel newMovie)
    {
        ArrayList<MovieModel> allMovies= getAllMovies();
        for(int i=0;i<allMovies.size();i++)
        {
            if(allMovies.get(i).getKey().equals(newMovie.getKey()))
            {
                allMovies.get(i).setName(newMovie.getName());
                allMovies.get(i).setDescription(newMovie.getDescription());
            }
        }

        try{
            FileWriter file = new FileWriter(moviesFile);
            String allMoviesJson=new Gson().toJson(allMovies);
            file.write(allMoviesJson);
            file.flush();
            AlertBox.display("Notificare","Filmul cu numele "+newMovie.getName() + " a fost editat!");
        }
        catch (Exception e)
        {
            System.out.println("Edit failed");
        }
    }

    public static void deleteMovies(MovieModel movieToDelete){
        ArrayList<MovieModel> allMovies= getAllMovies();
        for(int i=0;i<allMovies.size();i++)
        {
            if(allMovies.get(i).getKey().equals(movieToDelete.getKey()))
            {
                allMovies.remove(i);
            }
        }

        try{
            FileWriter file = new FileWriter(moviesFile);
            String allMoviesJson=new Gson().toJson(allMovies);
            file.write(allMoviesJson);
            file.flush();
            AlertBox.display("Notificare","Filmul cu numele "+movieToDelete.getName() + " a fost sters!");
        }
        catch (Exception e)
        {
            System.out.println("Delete failed");
        }
    }

}
