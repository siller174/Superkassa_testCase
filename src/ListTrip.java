import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ListTrip {
    public ArrayList<Trip> createListTrip() {
        ArrayList<Trip> trips = new ArrayList<>();
        String jsonText = null;
        JSONArray jsonArrays = null;

        try {
            // считываем файл
            jsonText = IOUtils.toString(new FileInputStream(new File("input.json")));
            jsonText = jsonText.substring(jsonText.indexOf("["));

            jsonArrays = new JSONArray(jsonText);

            // Проходим по массиву массиво Json и переносим все в объекты
            for (int i = 0; i < jsonArrays.length(); i++) {
                JSONArray jsonArray = jsonArrays.getJSONArray(i);
                Trip temp = new Trip(jsonArray.get(0).toString(), jsonArray.get(1).toString(), jsonArray.get(2).toString(), jsonArray.get(3).toString());

                // System.out.println(temp.toString() + "\n----------");

                trips.add(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //  сортируем массив в порядке возрастания свободных ячеек
        trips.sort(Trip::compareTo);

        return trips;
    }

    public void saveListTrip(ArrayList<Trip> trips) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(trips);
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
