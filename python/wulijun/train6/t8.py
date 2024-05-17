def jose(n, k):
    l = list(range(1, n + 1))
    ind = 0
    while l:
        tmp = l.pop(0)
        ind += 1
        if ind == k:
            ind = 0
            continue
        l.append(tmp)
        if len(l) == k - 1:
            print(sorted(l))
            break

a, b = map(int, input().split())
if b < 2 or b > a:
    print('Data Error!')
else:
    jose(a, b)