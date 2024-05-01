s = input()
y = s[6:10]
m = s[10:12]
d = s[12:14]
f = int(s[16])

print("出生：",end = '')
print(f"{y}年{m}月{d}日")

print("性别：",end='')
if f % 2 == 0:
    print("女")
else:
    print("男")
