#include <iostream>
using namespace std;

// 快速幂算法计算 (a^b) % m
unsigned int modExponentiation(unsigned int a, unsigned int b, unsigned int m) {
    unsigned int result = 1;
    a = a % m;  // a 与 m 取模，避免 a 很大
    while (b > 0) {
        if (b % 2 == 1) {  // 如果 b 是奇数
            result = (result * a) % m;
        }
        a = (a * a) % m;  // a 的平方
        b = b / 2;  // b 减半
    }
    return result;
}

// 求e在模m上的逆元
unsigned int modInverse(unsigned int e, unsigned int m) {
    if (__gcd(e, m) != 1) {
        cout << "Inverse doesn't exist" << endl;
        return 0;  // gcd 不为 1，表示逆元不存在
    }
    return modExponentiation(e, m - 2, m);  // 使用费马小定理计算逆元
}

int main() {
    unsigned int e, m;
    cout << "Enter e and m: ";
    cin >> e >> m;

    unsigned int d = modInverse(e, m);
    if (d != 0) {
        cout << "The modular inverse of " << e << " modulo " << m << " is " << d << endl;
    }

    return 0;
}
