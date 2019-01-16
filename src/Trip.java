import org.jetbrains.annotations.NotNull;

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

    @Override
    public int compareTo(@NotNull Object o) {
        return ((Trip) o).getCountFreePlace() - this.getCountFreePlace();
        //   return this.getCountFreePlace() - ((Trip) o).getCountFreePlace();
    }

    public String toString() {
        String str = "[" + place1 + ", "
                + place2 + ", "
                + place3 + ", "
                + place4 + "]";

        return str;
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
