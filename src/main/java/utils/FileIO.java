package utils;

// file handling
import java.io.File;
import javafx.stage.FileChooser;

// file parsing and writing
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

// import error classes
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import data.model.BoardModel;
import data.db.JSONParser;

public class FileIO{
    private static FileIO instance = null;

    private FileChooser chooser;
    private Stage parentStage;
    private File sessionFile;

    public static FileIO instance()
    {
        if(instance == null)
            instance = new FileIO();

        return instance;
    }

    private FileIO()
    {
        parentStage = null;

        // initialise the file chooser
        chooser = new FileChooser();
        chooser.setInitialFileName("*.json");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
            );

        // initialise the session file
        sessionFile = new File("program_files/session.json");
          //System.out.println("Session file created in: " + sessionFile.getAbsolutePath());
        try
        {
            sessionFile.createNewFile();  // will create a new file if one doesn't already exist
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void init(Stage stage)
    {
        parentStage = stage;
    }

    public List<BoardModel> loadFromJson()
    {
        chooser.setTitle("Import JSON file");
        File file = chooser.showOpenDialog(parentStage);

        return loadFile(file);
    }

    public List<BoardModel> loadSession()
    {
        return loadFile(sessionFile);
    }

    private List<BoardModel> loadFile(File file)
    {
        try(Scanner scanner = new Scanner(file))
        {
            scanner.useDelimiter("\n");

            String json = "";
            while(scanner.hasNext())
                json += scanner.next();

            List<BoardModel> newBoards = JSONParser.fromJson(json);

            return newBoards;
        }
        catch(NullPointerException exception)
        {
            System.out.println("No file selected");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        catch(FileNotFoundException exception)  // could also be handled by catching IOException
        {
            System.out.println("The specified file was not found");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        catch(IllegalStateException exception)  // could also be handled by catching RuntimeException
        {
            System.out.println("The scanner is closed");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        catch(NoSuchElementException exception) // could also be handled by catching RuntimeException
        {
            System.out.println("No more tokens are available");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        /*
        // alternative way of catching all exceptions
        // left here for information purposes
        catch(IOException exception)
        {
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        catch(RuntimeException exception)
        {
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        */
    }

    public void saveToJson(List<BoardModel> boards)
    {
        chooser.setTitle("Export JSON file");
        File file = chooser.showSaveDialog(parentStage);

        saveFile(file, boards);
    }

    public void saveSession(List<BoardModel> boards)
    {
        saveFile(sessionFile, boards);
    }

    private void saveFile(File file, List<BoardModel> boards)
    {
        try(BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(
                                new FileOutputStream(file), "utf-8")))
        {
            String json = JSONParser.toJson(boards);

            writer.write(json);
        }
        catch(NullPointerException exception)
        {
            System.out.println("No file selected");
            exception.printStackTrace();
        }
        catch(UnsupportedEncodingException exception)  // could also be handled by catching IOException
        {
            System.out.println("The specified file encoding is invalid");
            exception.printStackTrace();
        }
        catch(FileNotFoundException exception)  // could also be handled by catching IOException
        {
            System.out.println("The specified file was not found");
            exception.printStackTrace();
        }
        catch(IOException exception)
        {
            System.out.println("Could not save the file");
            exception.printStackTrace();
        }
    }
}
