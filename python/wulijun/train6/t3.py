l = []
n = input().split()
for i in n:
    if i == '0':
        break
    l.append(int(i))


n = input().split()
for i in n:
    if i == '0':
        break
    l.append(int(i))


l.sort(reverse=True)
print(l)