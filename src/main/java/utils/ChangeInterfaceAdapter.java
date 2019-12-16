/**
 * code source: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
 */

package utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.lang.Class;
import data.log.Change;

public class ChangeInterfaceAdapter implements JsonSerializer<Change>, JsonDeserializer<Change>{

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public Change deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class klass = getObjectClass(className);

        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
    }

    public JsonElement serialize(Change jsonElement, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));

        return jsonObject;
    }

// Helper method to get the className of the object to be deserialized
    public Class getObjectClass(String className)
    {
        try
        {
            return Class.forName(className);
        }
        catch (ClassNotFoundException exception)
        {
            throw new JsonParseException(exception.getMessage());
        }
    }
}
