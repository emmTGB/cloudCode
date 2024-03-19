import math
def sqrt(x:float, e:float = 1e-10):
    if x < 0:
        raise ValueError('x must be >= 0')
    if x == 0:
        return 0
    t = x
    while True:
        t = (t + x / t) / 2
        if abs(t * t - x) < e:
            return t

a, b = map(eval, input().split(','))
print(sqrt(a, b))
print(math.sqrt(a))