def f(x):
    if 'kg' in x[1]:
        res = float(x[1][:-2])
    if 't' in x[1]:
        res = float(x[1][:-1]) * 1000
    return res

l = []
while s := input().split():
    l.append(s)
l.sort(key=f)
print(l)