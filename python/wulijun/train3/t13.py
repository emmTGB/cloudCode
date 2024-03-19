h, f = map(int, input().split(' '))
r = int((f - 2 * h) / 2)
if r < 0 or r > h or r * 2 + 2 * h != f:
    print("Data Error!")
else:
    print("有{:d}只鸡，{:d}只兔".format(h - r, r))