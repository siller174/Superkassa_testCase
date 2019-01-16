import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

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
        try (FileWriter writer = new FileWriter("output.json", false)) {
            writer.write("[\n");
            for (int i = 0; i < trips.size() - 1; i++) {
                writer.write(trips.get(i).toString() + ",\n");
            }
            writer.write(trips.get(trips.size() - 1) + "\n]");

            writer.flush();
        } catch (Exception e) {
            System.out.println("Error in ListTrip - saveListTrip: " + e);
        }
    }

    public ArrayList<Trip> removeDublicate(ArrayList<Trip> trips){
        ArrayList<Trip> result = new ArrayList<>();

        if (trips.size() == 1)
            return trips;

        if (trips.size() == 2)
        {
            if (trips.get(0).equals(trips.get(1)))
                trips.remove(1);
            return trips;
        }
        result.add(trips.get(0));
        for (int i = 1; i < trips.size() - 1; i++) {
            for (int j = 2; j < trips.size(); j++) {
                if (trips.get(i).equals(trips.get(j))) {
                  break;
                }


            }
        }
        return result;

    }
}
