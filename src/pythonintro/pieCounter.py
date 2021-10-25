#!/usr/bin/python3

# Write your pie counter class here!
class pieCounter:
    def __init__(self, filename):
        #self._filename = filename
        file = open(filename)
        self._pie_list = []
        for line in file:
            self._pie_list.append(line)
        file.close()
    
    def count_pies(self, taName):
        pieCount = 0
        for i in self._pie_list:
            info = i.split(', ')
            if info[0] == taName:
                pieCount += int(info[1])
        print(pieCount)
        return pieCount

def test_count_pies(pieCounter):
    assert pieCounter.count_pies("Doug Woos") == 232, "Test failed: Doug"

# Please put all other executable code here
if __name__ == '__main__':
    counter = pieCounter("pieCount.txt")
    test_count_pies(counter)
