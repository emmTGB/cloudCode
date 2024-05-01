import random

n = int(input())
sd = int(input())
random.seed(sd)

rs = 'BCEFGHJKMPQRTVWXY2346789'

for i in range(n):
    for j in range(5):
        for h in range(5):
            print(random.choice(rs), end = '')
        if j != 4:
            print('-', end = '')
    print()
