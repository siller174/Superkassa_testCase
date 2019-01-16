import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
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
        // Добавляем, да бы не сохранять повторы
        ArrayList<Trip> alreadyPrint = new ArrayList<>();

        // Создаем массив массивов JSON
        JSONArray listTrip = new JSONArray();

        JSONArray temp;
        try {
            // Добавляем первый элемент в Json массив
            temp = new JSONArray(trips.get(0).toString());
            listTrip.put(temp);
            // Отмечаем объект, что мы его добавили, чтобы избавиться от повторов
            alreadyPrint.add(trips.get(0));

            for (int i = 1; i < trips.size(); i++) {
                temp = new JSONArray(trips.get(i).toString());

                //Проверяем сохраняли ли в файл мы уже схожий объект
                if (!alreadyPrint.contains(trips.get(i)))
                    listTrip.put(temp);
            }

            FileWriter fileWriter = new FileWriter("output.json");
            fileWriter.write(listTrip.toString());
            fileWriter.flush();
            fileWriter.close();


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
