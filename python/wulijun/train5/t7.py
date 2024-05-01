n = int(input())

def isFive(x):
    s = 0
    while x > 0:
        s += x % 10
        x //= 10
    return s == 5

for i in range(n + 1):
    if isFive(i):
        print(i, end=' ')