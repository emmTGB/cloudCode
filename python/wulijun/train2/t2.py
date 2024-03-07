import math

a = float(input())
b = float(input())

ans = (-b + math.sqrt(2 * a * math.sin(math.pi / 3) * math.cos(math.pi / 3))) / (2 * a)
print("{:.2f}".format(ans))