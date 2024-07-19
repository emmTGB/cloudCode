ls = input().split()
ls.sort()
print(ls)
for i in range(1, len(ls)):
    if ls[i] != ls[i - 1] and i % 2 == 1:
        print(ls[i-1])
        break

import random
print(random.choice(ls))

dic = {}
for a in ls:
    dic[a] = dic.get(a, 0) + 1
print(dic)