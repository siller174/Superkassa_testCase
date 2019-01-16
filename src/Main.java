import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        FunTrip funTrip = new FunTrip();

        // Считываем Json файл
        ArrayList<Trip> trips;
        ArrayList<Trip> result = new ArrayList<>();

        // элементы, которые необходимо удалять из общего списка поездок
        ArrayList<Trip> toremove = new ArrayList<>();

        // элементы, которые необходимо добавить в общий список
        ArrayList<Trip> toadd = new ArrayList<>();

        // переменная для временного хранения
        Trip mergeTripTemp = null;

        trips = funTrip.createListTrip();
        if (trips == null)
            return;

        System.out.println("BEFORE trips :\n");
        printTreeSet(trips);


        while (trips.size() > 1) {
            for (Trip trip : trips) {

                //Если у поездки 4 свободные ячейки, то мы можем бесконечно их начать перебирать
                if (trip.getCountFreePlace() == 4) {
                    toremove.add(trip);
                }

                // Если у поездки 3 свободные ячейки...
                if (trip.getCountFreePlace() == 3) {
                    toremove.add(trip);
                    for (Trip temp : trips) {
                        if (temp.equals(trip))
                            continue;
                        // Первое  мы ищем поздки, где есть 3 свободные ячейки ...
                        if (temp.getCountFreePlace() == 3) {
                            // .. соединяем ее с первоночальной поездкой...
                            if (funTrip.canMerge(temp, trip)) {
                                mergeTripTemp = funTrip.merge(temp, trip);
                                // ... если после объединения ячеек, что-то изменилось, то добавляем новую поездку в общий список поездок
                                if (!trip.equals(mergeTripTemp)) {
                                    toadd.add(mergeTripTemp);
                                }
                            }
                        }
                    }
                    break;
                }

                // Если у поездки 2 свободные ячейки...
                if (trip.getCountFreePlace() == 2) {
                    toremove.add(trip);

                    for (Trip temp : trips) {
                        // Затем ищем поездки, где тоже есть 2 свободные ячейки ...
                        if (temp.getCountFreePlace() == 2) {
                            // ... если такая находится, то соединяем их и добавляем в общий список
                            if (funTrip.canFinish(trip, temp))
                                result.add(funTrip.merge(temp, trip));
                        }
                    }
                    break;
                }

                // Ищем поездку в которой есть только 1 свободное место
                if (trip.getCountFreePlace() == 1) {
                    // Удаляем этот объект т.к. в составлении комбинаций он более не пригодится
                    toremove.add(trip);
                    // Проходимся по массиву в поисках подходящего объекта
                    for (Trip temp : trips) {
                        if (funTrip.canFinish(temp, trip)) {
                            result.add(funTrip.merge(temp, trip));
                        }
                    }
                    break;
                }

                // Если поездка полоная, то переносим ее в результат, т.к. с ней уже ничего не сделать
                if (trip.getCountFreePlace() == 0) {
                    // Удаляем этот объект т.к. в составлении комбинаций он более не пригодится
                    toremove.add(trip);

                    result.add(trip);
                    break;
                }

            }

            // добавляем новые элементы в общий список поездок
            if (toadd.size() != 0) {
                for (Trip temp : toadd)
                    trips.add(temp);

                toadd.clear();
            }

            // Удаляем лишние элементы из общего массива поездок
            if (toremove.size() != 0) {
                for (Trip temp : toremove)
                    trips.remove(temp);

                toremove.clear();

                if (trips.size() < 1)
                    break;
            }

        }

        System.out.println("\n_________" + "AFTER trips :\n");
        printTreeSet(trips);

        System.out.println("\n________________" + "Result :\n");
        printTreeSet(result);

        //JsonConverter.readJson("input.json");
    }

    public static void printTreeSet(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            System.out.println(trip.toString());
        }
    }

}
