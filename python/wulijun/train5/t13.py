import random

s = input()
sa = s.split(',')
n = int(sa[0])
sd = int(sa[1])

random.seed(sd)
ss = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!"#$%&\\()*+,-.'
for i in range(n):
    print(random.choice(ss), end = '')