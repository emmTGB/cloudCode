a = eval(input())
b = eval(input())
c = eval(input())

arr = [a, b, c]
arr.sort()

if arr[0]**2 + arr[1]**2 == arr[2]**2 and arr[0] > 0:
    print("YES")
else:
    print("NO")