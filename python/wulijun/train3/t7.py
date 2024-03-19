import math
a = int(input())
ans = 0

if a in range(-6, 0):
    ans = 5 - a
elif a in range(0, 3):
    ans = 1
    for i in range(1, a + 1):
        ans *= i
elif a in range(3, 7):
    ans = math.pow(a, a - 2)

print(int(ans))