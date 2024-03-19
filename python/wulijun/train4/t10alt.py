import math


def sqrt_binary(num,jingdu):
    low, high = 0, num + 0.25
    while True:
        x = (high + low) / 2
        if abs(x * x - num) <= jingdu:
            return x
        elif x * x - num < 0:
            low = x
        else:
            high = x

num = list(map(float,input().split(",")))
print('{:.8f}'.format(sqrt_binary(num[0],num[1])))
print('{:.8f}'.format(math.sqrt(num[0])))





