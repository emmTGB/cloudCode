import math

n = int(input())

p = 0

for i in range(n):
    p += math.factorial(4 * i) * (1103 + 26390 * i) / (math.factorial(i) ** 4 * 396 ** (4 * i))

print(1 / (p * math.sqrt(8) / 9801))