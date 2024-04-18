a = int(input())

r = 2

for i in range(a, 0, -1):
    f = True
    for j in range(2, i):
        if i % j == 0:
            f = False
            break
    if f:
        r = i
        break

print(r)
