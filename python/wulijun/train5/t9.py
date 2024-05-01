s = input()
for i in s:
    if i >= 'a' and i <= 'z':
        x = ord(i) - ord('a')
        x += 3
        x %= 26
        c = chr(x + ord('a'))
        print(c, end='')
    elif i >= 'A' and i <= 'Z':
        x = ord(i) - ord('A')
        x += 5
        x %= 26
        c = chr(x + ord('A'))
        print(c, end='')
    else:
        print(i, end='')