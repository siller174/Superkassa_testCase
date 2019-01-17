import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class Trip implements Comparable {
    public String place1;
    public String place2;
    public String place3;
    public String place4;

    Trip() {
    }

    public Trip(String place1, String place2, String place3, String place4) {
        this.place1 = place1;
        this.place2 = place2;
        this.place3 = place3;
        this.place4 = place4;
    }

    // Используется для сортировки массива по возрастанию ячеек
    @Override
    public int compareTo(@NotNull Object o) {
        return ((Trip) o).getCountFreePlace() - this.getCountFreePlace();
    }

    @Override
    public String toString() {
        String str = "[\"" + place1 + "\", "
                + "\"" + place2 + "\", "
                + "\"" + place3 + "\", "
                + "\"" + place4 + "\"]";

        return str;
    }

    @Override
    public boolean equals(Object o) {
        int count = 0;
        Trip trip = (Trip) o;

        if (this.place1.equals(trip.place1))
            count++;
        if (this.place2.equals(trip.place2))
            count++;
        if (this.place3.equals(trip.place3))
            count++;
        if (this.place4.equals(trip.place4))
            count++;


        if (count == 4)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place1, place2, place3, place4);
    }

    public int getCountFreePlace() {
        int count = 0;

        if (place1.equals("null"))
            count++;
        if (place2.equals("null"))
            count++;
        if (place3.equals("null"))
            count++;
        if (place4.equals("null"))
            count++;

        return count;
    }

}
