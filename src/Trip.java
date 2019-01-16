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
        return ((Trip) o).getCountFreePlace() - this.getCountFreePlace() ;
    }

    public String toString() {
        String str = "pl: " + place1 + " " +
                "p2: " + place2 + " " +
                "p3: " + place3 + " " +
                "p4: " + place4 + "";

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


    public boolean equals(Trip trip) {
        int count = 0;

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


}
