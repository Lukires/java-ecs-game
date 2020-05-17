package ddu.game.util;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSON {
    public static final JSONParser parser = new JSONParser();


    public static JSONObject getJson(String path) {
        try {
            return (JSONObject) parser.parse((new FileReader(path)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
