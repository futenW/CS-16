class Student:
    """The student class models a student with a name, ID number,
    a graduation year, and a concentration.
    """
    def __init__(self, name, idNumber, concentration, graduationYear=2020):
        self._name = name
        self._idNumber = idNumber
        self._graduationYear = graduationYear
        self._concentration = concentration

    def set_concentration(self, concentration):
        self._concentration = concentration

    # Other accessors/mutators...
    def print_student_info(self):
        print("Student named " + self._name + " has ID number " + \
            str(self._idNumber) + ", is graduating in " + \
            str(self._graduationYear) + " and is studying " + \
            self._concentration + ".")

if __name__ == "__main__" :
    dara = Student("Dara", 1002354, "Physics")
    dara.set_concentration("Computer Science")
    dara.print_student_info()