s = input()

for i in s:
    if i >= 'a' and i <= 'z':
        print(i.upper(), end='')
    elif i >= 'A' and i <= 'Z':
        print(i.lower(), end='')
    else:
        print(i, end='')
