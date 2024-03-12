t = eval(input())
a = t
b = 1.0
bt = 0.0
s = 1.0
while a - b - s > 0:
    a -= b + s
    bt += b
    b *= 2
    s /= 2
bt += b * a / (b + s)
print(round(bt, 1), round(t - bt, 1))