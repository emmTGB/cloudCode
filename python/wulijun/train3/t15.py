n = int(input())
flag = 0
for i in range(1, n + 1):
    if i % 3 == 2 and i % 5 == 3 and i % 7 == 2:
        print(i)
        flag = 1
if flag == 0:
    print("No solution")