n = int(input())

ans = 0
tmp1 = 1
tmp2 = 1

if n == 1:
    print(1)
elif n == 2:
    print(1)
else:
    for i in range(3, n+1):
        ans = tmp1 + tmp2
        tmp1 = tmp2
        tmp2 = ans
        print(ans)

print(ans)