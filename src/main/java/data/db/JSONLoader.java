package data.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.lang.RuntimeException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import data.model.BoardModel;

public class JSONLoader {
    private static JSONLoader instance = null;

    private FileChooser chooser;

    // not strictly necessary for the file chooser to work
    private Stage parentStage;

    private String autoSaveFile;

    private List<BoardModel> boards;

    public static JSONLoader instance() {
        if (instance == null)
            instance = new JSONLoader();

        return instance;
    }

    private JSONLoader() {
        chooser = new FileChooser();
        chooser.setInitialFileName("*.json");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        parentStage = null;

    }

    public void setStage(Stage stage) {
        parentStage = stage;
    }

    public ArrayList<BoardModel> loadFile() {
        try {
            chooser.setTitle("Import JSON file");
            File file = chooser.showOpenDialog(parentStage);

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            StringBuilder json = new StringBuilder();
            while (scanner.hasNext())
                json.append(scanner.next());

            scanner.close();

            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<BoardModel>>() {
            }.getType();
            return gson.fromJson(json.toString(), collectionType);
        }
        // the order of the following two catch clauses is very important!
        // in the reverse order the application will not compile
        // TODO: implement a popup dialog to inform the user
        //System.out.println("JSON format invalid");
        catch (IOException | RuntimeException exception) {
            // TODO: rethink when this exception occurs and is caught
            //System.out.println("Could not load the file");
            return null;
        }//System.out.println("Could not access the file");
    }

    public void saveFile(List<BoardModel> boards) {
        try {
            chooser.setTitle("Export JSON file");
            File file = chooser.showSaveDialog(parentStage);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file), StandardCharsets.UTF_8));

            Gson gson = new Gson();
            String json = gson.toJson(boards);

            writer.write(json);

            writer.close();
        } catch (IOException exception) {
            // TODO: rethink when this exception occurs and is caught
            //System.out.println("Could not save the file");
        } catch (RuntimeException exception) {
            // TODO: rethink when this exception occurs and is caught
            //System.out.println("Could not access the file");
        }
    }

//    public void init(JsonData callback) {
//        String getCurrentDirectory = System.getProperty("user.dir");
//        File autoSaveFile = new File(getCurrentDirectory);
//        File directory = new File(autoSaveFile.getParent());
//        if (!autoSaveFile.exists()) {
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            try {
//                autoSaveFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            InputStreamReader isReader;
//            try {
//                isReader = new InputStreamReader(new FileInputStream(autoSaveFile), StandardCharsets.UTF_8);
//
//                JsonReader myReader = new JsonReader(isReader);
//                Gson gson = new Gson();
//                List<BoardModel> boardModels = gson.fromJson(myReader, BoardModel.class);
//                callback.onFound(boardModels);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
