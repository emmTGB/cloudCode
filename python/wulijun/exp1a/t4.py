import math

n = int(input())

a = 1

for i in range(n):
    b = math.sqrt(1 - (a / 2)**2)
    c = 1 - b
    a = math.sqrt((a / 2)**2 + c**2)

print(f"分割{n}次，边数为{int(6 * math.pow(2, n)):d}，圆周率为{3 * math.pow(2, n) * a:.6f}")
print(f"math库中的圆周率常量值为{math.pi:.6f}")