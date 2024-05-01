t = input()
s = input()

n = 0
f = False
for i in s:
    if t == i:
        print(f"index = {n}")
        f = True
        break
    n += 1

if not f:
    print("Not Found")