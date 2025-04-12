import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E_PracticeHW {

    public static void main(String[] args) throws ParseException {
        E_PracticeHW pokemon = new E_PracticeHW();
    }

    public E_PracticeHW() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/pikachu/"); /** Your API's URL goes here */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // turn your string into a JSON object using a parser
        JSONParser parser = new JSONParser();
        JSONObject pikachu = (JSONObject) parser.parse(jsonString);
        System.out.println("Pikachu's JSON: " + pikachu);

        /* TODO : print out the name of each ability that Pikachu has */
        // feel free to paste this URL into your browser to explore the JSON: https://pokeapi.co/api/v2/pokemon/pikachu/
        JSONArray abilities = (JSONArray) pikachu.get("abilities");
        System.out.println("Pikachu Abilities: ");
        int i = pikachu.size();
        for (int n = 0; n<i; n++){
            JSONObject jsonObject = (JSONObject) abilities.get(n); //get the right amount of objects from the array
            JSONObject pikachuAbilities = (JSONObject) jsonObject.get("jsonObject");
            String name = (String) jsonObject.get("name");
            System.out.println(pikachuAbilities);
        }

        JSONArray heldItems = (JSONArray) pikachu.get("held–items");
        System.out.println("Pikachu held items: ");
        int j = pikachu.size();
        for (int n = 0; n<j; n++){
            JSONObject pikachuHeldItems = (JSONObject) heldItems.get(n);
            System.out.println(pikachuHeldItems);
        }
        JSONArray moves = (JSONArray) pikachu.get("moves");
        System.out.println("Pikachu moves: ");
        for (int n = 0; n<pikachu.size(); n++){
            JSONObject movesPiece = (JSONObject) moves.get(n);
            JSONObject pikachuMoves = (JSONObject) movesPiece.get("move");
            String name = (String) pikachuMoves.get("name");
            System.out.println(name);
        }

    }
}