import math
a = eval(input())
b = eval(input())
c = input()

if c == "nan":
    print(math.floor((a + b) * 1.08 / 2.0))
elif c == "nv":
    print(math.floor((a * 0.923 + b) / 2.0))
else:
    print('no')