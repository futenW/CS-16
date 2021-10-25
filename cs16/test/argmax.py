import random

for exponent in range(2, 16):
    avg = 0
    for numTrials in range(0, 1000):
        n = 2 ** exponent
        B = [0] * n

        for count in range(0, n):
            i = random.randint(0, n-1)
            j = random.randint(0, n-1)
            if B[i] < B[j]:
                B[i] += 1
            else:
                B[j] += 1

        largest = 0
        for index in B:
            if index > largest:
                largest = index
        avg += largest
    avg /= 1000
    print("n = 2^" + str(exponent) + " || avg: " + str(avg))