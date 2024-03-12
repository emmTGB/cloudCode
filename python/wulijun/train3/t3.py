import math
a = eval(input())
b = eval(input())
c = eval(input())
p = (a + b + c) / 2
arr = [a, b, c]
arr.sort()
if arr[0] + arr[1] > arr[2] and arr[0] > 0:
    print("YES")
    print("{:.2f}".format(math.sqrt(p * (p - a) * (p - b) * (p - c))))
else:
    print("NO")