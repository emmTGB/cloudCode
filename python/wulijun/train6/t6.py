s = input()
lw = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
lx = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2']
res = s[:6]
y = int(s[6:8])
if y >= 5:
    res += '19'
else:
    res += '20'
res += s[6:]
S = 0
for i in range(len(res)):
    S += int(res[i]) * lw[i]
res += lx[S % 11]
print(res)