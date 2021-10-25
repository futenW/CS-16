package ranking;
/**
 * This class represents a pole vaulter, who has a name and country it represents,
 * along with their best and worst height and their rank.
 */
public class Vaulter {

    private double _bestHeight;
    private double _worstHeight;
    private String _name;
    private String _nationality;
    private int _rank;

    public Vaulter(String name, String nationality) {
        _name = name;
        _nationality = nationality;
    }

    public void setBestHeight(double height) {
        _bestHeight = height;
    }

    public void setWorstHeight(double height) {
        _worstHeight = height;
    }

    public double getBestHeight() {
        return _bestHeight;
    }

    public double getWorstHeight() {
        return _worstHeight;
    }

    public String getNationality() {
        return _nationality;
    }

    public String getName() {
        return _name;
    }

    public void setRank(int rank) {
        _rank = rank;
    }

    public int getRank() {
        return _rank;
    }

}