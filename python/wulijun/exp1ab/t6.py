import math

a = eval(input())
b = eval(input())

r = ((a / 2)**2 + b**2)/(b * 2)

s = 2 * math.asin(a / (2 * r)) * r**2 / 2
t = r**2 * math.sin(2 * math.asin(a / (2 * r))) / 2

print("{:.2f}".format(s - t))