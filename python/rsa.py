import random
from math import gcd
import sys

sys.setrecursionlimit(100000)

def is_prime(n, k=10):
    """
    使用 Miller-Rabin 素性测试验证 n 是否为素数。
    参数:
        n: 待测试的整数。
        k: 测试轮数，默认 10 次。
    返回:
        True: 如果 n 是素数（可能是素数）。
        False: 如果 n 不是素数。
    """
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0:
        return False

    # 分解 n - 1 为 d * 2^r
    r, d = 0, n - 1
    while d % 2 == 0:
        d //= 2
        r += 1

    # 进行 k 次测试
    for _ in range(k):
        a = random.randint(2, n - 2)
        x = pow(a, d, n)  # a^d % n
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                break
        else:
            return False
    return True

def generate_large_prime(bits):
    """
    生成指定位长的大素数。
    参数:
        bits: 需要生成素数的位长。
    返回:
        生成的位长为 bits 的大素数。
    """
    while True:
        # 生成一个随机的 bits 位整数
        candidate = random.getrandbits(bits)
        # 确保高位和低位是 1，以保证是指定位长，并排除偶数
        candidate |= (1 << (bits - 1)) | 1
        if is_prime(candidate):
            return int(candidate)
        
def is_coprime(a, b):
    return gcd(a, b) == 1

def generate_public_key(p, q):
    # Step 1: 计算 n
    n = p * q

    # Step 2: 计算 φ(n)
    phi_n = (p - 1) * (q - 1)

    # Step 3: 选择 e
    e = 65537  # 通常选择的值
    if not is_coprime(e, phi_n):  # 如果不互质，则选择其他值
        for candidate in range(3, phi_n, 2):  # 从 3 开始尝试奇数
            if is_coprime(candidate, phi_n):
                e = candidate
                break

    return e

def e_gcd(a, b):
    if b == 0:
        return a, 1, 0
    else:
        d, x, y = e_gcd(b, a % b)
        return d, y, x - y * (a // b)


def mod_inv(a, m):
    d, x, y = e_gcd(a, m)
    if d != 1:
        return None
    else:
        return (x % m + m) % m
    

def mod_pow(a, b, m):
    if b == 0:
        return 1
    elif b % 2 == 0:
        return (mod_pow(a, b // 2, m) ** 2) % m
    else:
        return (a * mod_pow(a, b - 1, m)) % m
    
def rsa(p, q, e, m):
    n = p * q
    phi = (p - 1) * (q - 1)
    c = mod_pow(m, e, n)
    d = mod_inv(e, phi)
    print('明文:', m)
    print('密文:', c)
    print('私钥:', d)
    c1 = mod_pow(c, d, n)
    print('解密:', c1)



if(__name__ == '__main__'):
    rsa(
        3,
        11,
        7,
        6,
    )

    print()

    rsa(
        16979,
        27901,
        39839,
        10000039,
    )

    print()


    p = generate_large_prime(720)
    q = generate_large_prime(512)
    e = generate_public_key(p, q)
    m = 96981056087953677071299301082723239242940209497313930045515953739119660401673463693139347065937208998669840227616817342387891063244934946912540836948841874007774863366545369977752838529240230031121526306855268978775982710563791980882623583485888635878450811865316984603870728078298325474843277533549140449023
    rsa(
        p,
        q,
        e,
        m,
    )