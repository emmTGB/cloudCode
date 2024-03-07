fund = int(input())
year = int(input())
f = float(input())

ans = fund
for i in range(year):
    ans *= 1 + f

print("利息={:.2f}".format(ans - fund))