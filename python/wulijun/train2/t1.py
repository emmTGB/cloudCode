import math

a = float(input())
b = float(input())
c = float(input())

cir = a + b + c
s = (a + b + c) / 2
area = math.sqrt(s*(s-a)*(s-b)*(s-c))
print("周长="+"{:.2f}".format(cir))
print("面积="+"{:.2f}".format(area))