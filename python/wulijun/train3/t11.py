for i in range(1, 16):
    for j in range(1, 31):
        if i + j != 100 and 5 * i + 3 * j + (100 - i - j) / 3.0 == 100:
            print(i, j, 100 - i - j)