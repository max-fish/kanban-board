package utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;
import java.lang.RuntimeException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Scanner;
import java.util.ArrayList;
import model.Board;

public class JSONLoader{
    private static JSONLoader instance = null;

    private FileChooser chooser;

    // not strictly necessary for the file chooser to work
    private Stage parentStage;

    public static JSONLoader instance()
    {
        if(instance == null)
            instance = new JSONLoader();

        return instance;
    }

    private JSONLoader()
    {
        chooser = new FileChooser();
        chooser.setTitle("Import JSON file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
            );
        parentStage = null;
    }

    public void setStage(Stage stage)
    {
        parentStage = stage;
    }

    public ArrayList<Board> loadFile()
    {
        try
        {
            File file = chooser.showOpenDialog(parentStage);

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            String json = "";
            while(scanner.hasNext())
                json += scanner.next();

            scanner.close();

            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Board>>(){}.getType();
            ArrayList<Board> newBoards = gson.fromJson(json, collectionType);
            return newBoards;
        }
        catch(IOException exception)
        {
            System.out.println("Could not load the file");
            return null;
        }
        // the order of the following two catch clauses is very important!
        // in the reverse order the application will not compile
        catch(JsonSyntaxException exception)
        {
            // TODO: implement a popup dialog to inform the user
            System.out.println("JSON format invalid");
            return null;
        }
        catch(RuntimeException exception)
        {
            System.out.println("Could not access the file");
            return null;
        }
    }
}
