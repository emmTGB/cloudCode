a = int(input())
ans = a

if a < 0:
    print("err")
else:
    a = a - 5000
    if a <= 0:
        print(0)
    elif a <= 3000:
        print(a * 0.03)
    elif a <= 12000:
        print(a * 0.1 - 210)
    elif a <= 25000:
        print(a * 0.2 - 1410)
    elif a <= 35000:
        print(a * 0.25 - 2660)
    elif a <= 55000:
        print(a * 0.3 -4410)
    elif a <= 80000:
        print(a * 0.35 - 7160)
    else:
        print(a * 0.45 - 15160)