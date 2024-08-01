package experiments;

public enum DayOfWeek {
    SUNDAY("SUN"),
    MONDAY("MON"),
    TUESDAY("TUE"),
    WEDNESDAY("WED"),
    THURSDAY("THU"),
    FRIDAY("FRI"),
    SATURDAY("SAT");
    private final String name;

    DayOfWeek(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

}
