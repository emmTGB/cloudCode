def isYear(s):
    n = len(s) - 3
    for i in range(n):
        if s[i: i + 4].isdigit():
            return s[i: i + 4]
    return ""

s = input()
a = s.split(" ")
print("姓名：" + a[1])
print("班级：" + a[2])
print("出生：" + isYear(a[4]) + "年")