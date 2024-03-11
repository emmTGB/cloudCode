a = int(input())
ans = 0.0

if a < 0:
    print("err")
elif a >= 0:
    ans += a * 0.03
    if a - 3000 > 0:
        ans += (a - 3000)

