s = input().split(',')
res = []
for n in s:
    if n not in res:
        res.append(n)
print(res)