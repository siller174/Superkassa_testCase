
import org.jetbrains.annotations.NotNull;


/*

    Да, я знаю, что ифы ниже просто ужасны, но при переносе их в фунции код бы увеличился на 40 строчек


 */

public class FunTrip {

    // Проверяет готова ли будет поездка после объединения двух других
    public boolean canFinish(@NotNull Trip o1, @NotNull Trip o2) {
        // счетчик совместимости, если он равен 4, то поездки готовы
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

        // Выбираем из какой поездки взять ячейку
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


    // проверка на возможность объединения двух поездок, если counter = 4, то возможно
    public boolean canMerge(@NotNull Trip o1, @NotNull Trip o2) {
        int counter = 0;

        if (o1.place1.equals("null") && !o2.place1.equals("null") ||
                (!o1.place1.equals("null") && o2.place1.equals("null")) ||
                (o1.place1.equals("null") && o2.place1.equals("null")))
            counter++;


        if (o1.place2.equals("null") && !o2.place2.equals("null") ||
                (!o1.place2.equals("null") && o2.place2.equals("null")) ||
                (o1.place2.equals("null") && o2.place2.equals("null")))
            counter++;


        if (o1.place3.equals("null") && !o2.place3.equals("null") ||
                (!o1.place3.equals("null") && o2.place3.equals("null")) ||
                (o1.place3.equals("null") && o2.place3.equals("null")))
            counter++;


        if (o1.place4.equals("null") && !o2.place3.equals("null") ||
                (!o1.place4.equals("null") && o2.place4.equals("null")) ||
                (o1.place4.equals("null") && o2.place4.equals("null")))
            counter++;


        if (counter == 4)
            return true;
        else
            return false;
    }


}
