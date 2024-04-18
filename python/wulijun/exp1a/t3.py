t = float(input())

i = 1.0
j = 1
res = 0

while 1 / i > t:
    res += 1 / i * j
    i += 2
    j = -j

res *= 4

print(f"{res:.8f}")