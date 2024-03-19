a = int(input())
ans = a
res = 0

if a < 0:
    print("err")
else:
    a = a - 5000
    if a <= 0:
        res = 0
    elif a <= 3000:
        res = a * 0.03
    elif a <= 12000:
        res = a * 0.1 - 210
    elif a <= 25000:
        res = a * 0.2 - 1410
    elif a <= 35000:
        res = a * 0.25 - 2660
    elif a <= 55000:
        res = a * 0.3 -4410
    elif a <= 80000:
        res = a * 0.35 - 7160
    else:
        res = a * 0.45 - 15160

    print("应缴税款{:.2f}元，实发工资{:.2f}元".format(res, ans - res))