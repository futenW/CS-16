package pythonintro;

public class Student {

    private String _name;
    private int _idNumber;
    private String _concentration;
    private int _graduationYear;

    /* Student contructor which takes in a graduation year */
    public Student(String name, int idNumber, String concentration, int graduationYear) {
        _name = name;
        _idNumber = idNumber;
        _graduationYear = graduationYear;
        _concentration = concentration;
    }

    /* Student overloading! Second contructor which has a default graduation year of 2020, in case only three arguments are given. */
    public Student(String name, int idNumber, String concentration) {
        _name = name;
        _idNumber = idNumber;
        _graduationYear = 2020;
        _concentration = concentration;
    }

    public void setConcentration(String concentration) {
        _concentration = concentration;
    }

    public void printStudentInfo() {
//        System.out.println("Student named " + _name + " has ID number " + _idNumber.toString() + " is graduating in " _graduationYear.toString() + " and is studying " + _concentration + ".")
    }
}
