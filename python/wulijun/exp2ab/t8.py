a = int(input())
b = int(input())
c = int(input())

print(f"{a:02}:{b:02}:{c:02}")

print("距离午夜还剩余{:d}秒".format(86400-a*3600-b*60-c))