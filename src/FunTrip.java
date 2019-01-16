import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FunTrip {
    // ПЕРЕИМЕНОВАТЬ ФУНКЦИЮ
    // Проверяем заполнена ли поездка
    public boolean canFinish(@NotNull Trip o1, @NotNull Trip o2) {
        // счетчик совместимости, если он равен 4, то они совеместимы
        int countComp = 0;

        if (o1.getCountFreePlace() + o2.getCountFreePlace() != 4)
            return false;

        if (o1.place1.equals("null") && !o2.place1.equals("null") || !o1.place1.equals("null") && o2.place1.equals("null"))
            countComp++;
        if (o1.place2.equals("null") && !o2.place2.equals("null") || !o1.place2.equals("null") && o2.place2.equals("null"))
            countComp++;
        if (o1.place3.equals("null") && !o2.place3.equals("null") || !o1.place3.equals("null") && o2.place3.equals("null"))
            countComp++;
        if (o1.place4.equals("null") && !o2.place4.equals("null") || !o1.place4.equals("null") && o2.place4.equals("null"))
            countComp++;

        if (countComp == 4)
            return true;
        else
            return false;
    }

    // Соединяет поездки
    public Trip merge(@NotNull Trip o1, @NotNull Trip o2) {
        Trip result = new Trip("null", "null", "null", "null");

        if (o1.place1.equals("null") && !o2.place1.equals("null"))
            result.place1 = o2.place1;
        if (!o1.place1.equals("null") && o2.place1.equals("null"))
            result.place1 = o1.place1;

        if (o1.place2.equals("null") && !o2.place2.equals("null"))
            result.place2 = o2.place2;
        if (!o1.place2.equals("null") && o2.place2.equals("null"))
            result.place2 = o1.place2;

        if (o1.place3.equals("null") && !o2.place3.equals("null"))
            result.place3 = o2.place3;
        if (!o1.place3.equals("null") && o2.place3.equals("null"))
            result.place3 = o1.place3;

        if (o1.place4.equals("null") && !o2.place4.equals("null"))
            result.place4 = o2.place4;
        if (!o1.place4.equals("null") && o2.place4.equals("null"))
            result.place4 = o1.place4;

        return result;
    }

    public boolean canMerge(@NotNull Trip o1, @NotNull Trip o2) {
        int counter = 0;

        if (o1.place1.equals("null") && !o2.place1.equals("null") ||
                (!o1.place1.equals("null") && o2.place1.equals("null")))
            counter++;

        if (o1.place2.equals("null") && !o2.place2.equals("null") ||
                (!o1.place2.equals("null") && o2.place2.equals("null")))
            counter++;

        if (o1.place3.equals("null") && !o2.place3.equals("null") ||
                (!o1.place3.equals("null") && o2.place4.equals("null")))
            counter++;

        if (o1.place4.equals("null") && !o2.place3.equals("null") ||
                (!o1.place4.equals("null") && o2.place4.equals("null")))
            counter++;

        if (counter != 0)
            return true;
        else
            return false;
    }


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
