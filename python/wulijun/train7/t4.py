n = int(input())
dic = set(input().split())
for i in range(n):
    s = input().split()
    if s[0] == 'add':
        dic.add(s[1])
    elif s[0] == 'del':
        dic.discard(s[1])
    elif s[0] == 'print':
        print(sorted(list(dic)))
    elif s[0] == 'update':
        dic.update(set(s[1:]))
    elif s[0] == 'clear':
        dic.clear()