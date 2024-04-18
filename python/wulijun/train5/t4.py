def trans(s, f):
    i = len(s)
    if f > 0:
        if s[i - 1] == '$':
            return "{:.2f}¥".format(eval(s[:i - 1]) * f)
        elif s[i - 1] == '¥':
            return "{:.2f}$".format(eval(s[:i - 1]) / f)

    return "Data error!";

if __name__ == '__main__':
    s = input()
    f = float(input())
    print(trans(s, f))