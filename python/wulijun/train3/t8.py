a = int(input())

if a >= 0 and a <= 100:
    if a < 60:
        print('E')
    elif a < 70:
        print('D')
    elif a < 80:
        print('C')
    elif a < 90:
        print('B')
    else:
        print('A')
else:
    print('data error!')