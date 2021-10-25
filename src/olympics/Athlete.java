package olympics;
/**
 * This class represents an athlete who has a name and a race time.
 */
public class Athlete {

    private String _name;
    private double _time;

    public Athlete(String name, double time) {
        _name = name;
        _time = time;
    }

    public double getTime() {
        return _time;
    }

    public String getName() {
        return _name;
    }

}
