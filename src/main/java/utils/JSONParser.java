package utils;

// import error classes
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonParseException;
import java.lang.RuntimeException;

// classes for JSON parsing
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
import java.util.ArrayList;
import model.BoardModel;

public class JSONParser{

    public static List<BoardModel> fromJson(String json)
    {
        try
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type collectionType = new TypeToken<ArrayList<BoardModel>>(){}.getType();
            ArrayList<BoardModel> newBoards = gson.fromJson(json, collectionType);

            return newBoards;
        }
        catch(JsonSyntaxException exception)  // could also be handled by catching RuntimeException
        {
            System.out.println("The file is not in valid JSON format");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        catch(JsonParseException exception)   // could also be handled by catching RuntimeException
        {
            System.out.println("A parsing error occurred");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }
        /*
        // alternative way of catching all exceptions
        // left here for information purposes
        catch(RuntimeException exception)
        {
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<BoardModel>();
        }*/
    }

    public static String toJson(List<BoardModel> boards)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(boards);

        return json;
    }
}
