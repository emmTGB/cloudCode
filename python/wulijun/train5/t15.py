a = int(input())
n = int(input())

if not(a < 10 and a > 0):
    print("data error")
    exit()

if n < 0:
    print("data error")
    exit()

add = a
num = 0
for i in range(n):
    num += add
    add *= 10
    add += a

print(num)