year, month, day = map(int, input().split('/'))

dm = [31,28,31,30,31,30,31,31,30,31,30,31]
if year % 400 == 0 or (year % 4 == 0 and year % 100 !=0):
    dm[1] = 29
if month == 1:
    n = day
else:
    n = sum(dm[0:month - 1 ]) + day
print("%d年%d月%d日是%d年第%d天" % (year, month, day, year, n))