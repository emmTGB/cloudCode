n = int(input())

if n % 4 == 0:
    if n % 100 == 0 and n % 400 != 0:
            print(365)
    else:
        print(366)
else:
    print(365)