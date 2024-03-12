n = int(input())
ans = 1.0
p1 = 1.0
p2 = 1.0
e = 1

for i in range(n):
    ans += e * i / p2
    p1, p2 = p2, p1 + p2
    e = -e
print("{:.6f}".format(ans))