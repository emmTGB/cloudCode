def isRoundable(n):
    s = "{:d}".format(n)
    i = len(s)
    for j in range(0, int(i / 2) + 1):
        if s[j] != s[i - j - 1]:
            return False
    return True

def isPrime(n):
    if n < 2:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True

if __name__ == '__main__':
    n = int(input())
    i = 0
    while n > 0:
        if isRoundable(i) and isPrime(i):
            print(i, end=" ")
            n -= 1
        i += 1