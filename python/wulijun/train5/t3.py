def isPrime(n):
    if n == 1:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True

def goldbach(n):
    if n % 2 != 0 or n < 4:
        print("Data error!")
        return
    for i in range(2, int(n / 2) + 1):
        if isPrime(i) and isPrime(n - i):
            print("{:d}={:d}+{:d}".format(n, i, n - i))

if __name__ == "__main__":
    n = int(input())
    goldbach(n)