import math

a = float(input())
b = float(input())
c = float(input())

ans = (-b + math.sqrt(b*b - 4*a*c))/(2 * a)
print("{:.2f}".format(ans))