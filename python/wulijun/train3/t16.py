a = float(input()) * 0.3
b = float(input())
c = float(input()) / 100
d = (float(input()) / 100 + 1) ** 2

ans = 0.0
while ans < a:
    ans += b * c
    b *= d