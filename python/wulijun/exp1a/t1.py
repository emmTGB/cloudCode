s = input()
sl = s.split(',')
a = int(sl[0])
b = int(sl[1])

p = 13
if a > 3:
    p += (a - 3) * 2.3

if a > 15:
    p += (a - 15) * 0.5 * 2.3

p += b

print(f"{int(p):d}")