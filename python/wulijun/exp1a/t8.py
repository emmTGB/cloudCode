a = int(input())

for i in range(2, a + 1):
    f = True
    for j in range(2, i):
        if i % j == 0:
            f = False
            break
    if f:
        print(i, end=' ')

