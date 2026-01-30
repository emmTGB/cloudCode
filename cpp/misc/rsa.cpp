#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <stdexcept>

class BigInt {
private:
    std::vector<int> digits;  // 存储数字的各位
    bool is_negative;         // 标记负数

public:
    BigInt() {
        digits.push_back(0);
        is_negative = false;
    }

    // 构造函数：从字符串初始化
    BigInt(const std::string& str) {
        is_negative = false;
        if (str[0] == '-') {
            is_negative = true;
            digits = from_string(str.substr(1));  // 去掉负号
        }
        else {
            digits = from_string(str);
        }
    }

    // 转换字符串为BigInt
    std::vector<int> from_string(const std::string& str) {
        std::vector<int> result;
        for (int i = str.size() - 1; i >= 0; --i) {
            result.push_back(str[i] - '0');
        }
        return result;
    }

    // 转换BigInt为字符串
    std::string to_string() const {
        std::string result = is_negative ? "-" : "";
        for (int i = digits.size() - 1; i >= 0; --i) {
            result.push_back(digits[i] + '0');
        }
        return result.empty() ? "0" : result;
    }

    // 比较函数：a > b 返回1，a == b 返回0，a < b 返回-1
    int compare(const std::vector<int>& a, const std::vector<int>& b) const {
        if (a.size() > b.size()) return 1;
        if (a.size() < b.size()) return -1;
        for (int i = a.size() - 1; i >= 0; --i) {
            if (a[i] > b[i]) return 1;
            if (a[i] < b[i]) return -1;
        }
        return 0;
    }

    // 加法运算
    BigInt operator+(const BigInt& other) const {
        if (is_negative == other.is_negative) {
            std::vector<int> result = add_digits(digits, other.digits);
            return BigInt(result, is_negative);
        }
        else {
            if (compare(digits, other.digits) >= 0) {
                std::vector<int> result = subtract_digits(digits, other.digits);
                return BigInt(result, is_negative);
            }
            else {
                std::vector<int> result = subtract_digits(other.digits, digits);
                return BigInt(result, other.is_negative);
            }
        }
    }

    // 加法实现：两个数字相加
    std::vector<int> add_digits(const std::vector<int>& a, const std::vector<int>& b) const {
        std::vector<int> result;
        int carry = 0;
        size_t n = std::max(a.size(), b.size());
        for (size_t i = 0; i < n || carry != 0; ++i) {
            int sum = carry;
            if (i < a.size()) sum += a[i];
            if (i < b.size()) sum += b[i];
            result.push_back(sum % 10);
            carry = sum / 10;
        }
        return result;
    }

    // 减法运算
    BigInt operator-(const BigInt& other) const {
        if (is_negative == other.is_negative) {
            if (compare(digits, other.digits) >= 0) {
                std::vector<int> result = subtract_digits(digits, other.digits);
                return BigInt(result, is_negative);
            }
            else {
                std::vector<int> result = subtract_digits(other.digits, digits);
                return BigInt(result, !is_negative);
            }
        }
        else {
            std::vector<int> result = add_digits(digits, other.digits);
            return BigInt(result, is_negative);
        }
    }

    // 减法实现：a - b
    std::vector<int> subtract_digits(const std::vector<int>& a, const std::vector<int>& b) const {
        std::vector<int> result;
        int borrow = 0;
        for (size_t i = 0; i < a.size(); ++i) {
            int diff = a[i] - borrow;
            if (i < b.size()) diff -= b[i];
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            }
            else {
                borrow = 0;
            }
            result.push_back(diff);
        }
        while (result.size() > 1 && result.back() == 0) {
            result.pop_back();
        }
        return result;
    }

    // 乘法运算
    BigInt operator*(const BigInt& other) const {
        std::vector<int> result = multiply_digits(digits, other.digits);
        bool result_is_negative = (is_negative != other.is_negative);
        return BigInt(result, result_is_negative);
    }

    // 乘法实现：a * b
    std::vector<int> multiply_digits(const std::vector<int>& a, const std::vector<int>& b) const {
        std::vector<int> result(a.size() + b.size(), 0);
        for (size_t i = 0; i < a.size(); ++i) {
            int carry = 0;
            for (size_t j = 0; j < b.size() || carry != 0; ++j) {
                int mul = result[i + j] + a[i] * (j < b.size() ? b[j] : 0) + carry;
                result[i + j] = mul % 10;
                carry = mul / 10;
            }
        }
        while (result.size() > 1 && result.back() == 0) {
            result.pop_back();
        }
        return result;
    }

    // 除法运算
    BigInt operator/(const BigInt& other) const {
        if (other == BigInt("0")) {
            throw std::invalid_argument("Division by zero");
        }
        std::vector<int> result = divide_digits(digits, other.digits);
        bool result_is_negative = (is_negative != other.is_negative);
        return BigInt(result, result_is_negative);
    }

    // 除法实现：a / b
    std::vector<int> divide_digits(const std::vector<int>& a, const std::vector<int>& b) const {
        std::vector<int> result;
        std::vector<int> remainder;
        for (size_t i = a.size(); i-- > 0;) {
            remainder.insert(remainder.begin(), a[i]);
            int quotient = 0;
            while (compare_digits(remainder, b) >= 0) {
                remainder = subtract_digits(remainder, b);
                ++quotient;
            }
            result.push_back(quotient);
        }
        std::reverse(result.begin(), result.end());
        return result;
    }

    // 比较两个数字的大小
    int compare_digits(const std::vector<int>& a, const std::vector<int>& b) const {
        if (a.size() > b.size()) return 1;
        if (a.size() < b.size()) return -1;
        for (size_t i = 0; i < a.size(); ++i) {
            if (a[i] > b[i]) return 1;
            if (a[i] < b[i]) return -1;
        }
        return 0;
    }

    // 取模运算
    BigInt operator%(const BigInt& other) const {
        BigInt quotient = *this / other;
        BigInt product = quotient * other;
        return *this - product;
    }

    // 比较运算符
    bool operator==(const BigInt& other) const {
        return is_negative == other.is_negative && digits == other.digits;
    }

    bool operator!=(const BigInt& other) const {
        return !(*this == other);
    }

    bool operator<(const BigInt& other) const {
        if (is_negative != other.is_negative) return is_negative;
        if (compare(digits, other.digits) < 0) return !is_negative;
        if (compare(digits, other.digits) > 0) return is_negative;
        return false;
    }

    bool operator>(const BigInt& other) const {
        return !(*this < other) && *this != other;
    }

    bool operator<=(const BigInt& other) const {
        return *this < other || *this == other;
    }

    bool operator>=(const BigInt& other) const {
        return *this > other || *this == other;
    }

    BigInt modPow(BigInt exponent, const BigInt& modulus) const {
        BigInt res = BigInt("1");
        BigInt base = *this % modulus;
        while (!(exponent.digits.size() == 1 && exponent.digits[0] == 0)) {
            if (exponent.digits[0] % 2 == 1) {
                res = (res * base) % modulus;
            }
            exponent = exponent / BigInt("2");
            base = (base * base) % modulus;
        }
        return res;
    }

    BigInt e_gcd(BigInt a, BigInt b, BigInt& x, BigInt& y) {
        if (b == BigInt("0")) {
            x = BigInt("1");
            y = BigInt("0");
            return a;
        }
        BigInt x1, y1;
        BigInt gcd = e_gcd(b, a % b, x1, y1);
        x = y1;
        y = x1 - (a / b) * y1;
        return gcd;
    }

    BigInt modInverse(BigInt modulus) {
        BigInt x, y;
        BigInt gcd = e_gcd(*this, modulus, x, y);
        if (gcd != BigInt("1")) {
            throw std::invalid_argument("Modular inverse does not exist");
        }
        return (x + modulus) % modulus;
    }

    // 输出BigInt
    friend std::ostream& operator<<(std::ostream& os, const BigInt& bigint) {
        os << bigint.to_string();
        return os;
    }

private:
    // 构造函数：从内部数字和符号初始化
    BigInt(const std::vector<int>& digits, bool is_negative)
        : digits(digits), is_negative(is_negative) {
    }
};

int main() {
    BigInt p1("3"), q1("11");
    BigInt n1 = p1 * q1;
    BigInt m1("20");
    BigInt e1("7");
    BigInt d1 = e1.modInverse(m1);
    BigInt message1("6");
    BigInt encrypted1 = message1.modPow(e1, n1);
    BigInt decrypted1 = encrypted1.modPow(d1, n1);

    std::cout << "Data 1:\n";
    std::cout << "私钥d: " << d1 << std::endl;
    std::cout << "密文: " << encrypted1 << std::endl;
    std::cout << "解密: " << decrypted1 << std::endl;

    BigInt p2("16979"), q2("27901");
    BigInt n2 = p2 * q2;
    BigInt m2 = (p2 - BigInt("1")) * (q2 - BigInt("1"));
    BigInt e2("39839");
    BigInt d2 = e2.modInverse(m2);
    BigInt message2("10000039");
    BigInt encrypted2 = message2.modPow(e2, n2);
    BigInt decrypted2 = encrypted2.modPow(d2, n2);

    std::cout << "Data 1:\n";
    std::cout << "私钥d: " << d2 << std::endl;
    std::cout << "密文: " << encrypted2 << std::endl;
    std::cout << "解密: " << decrypted2 << std::endl;

    return 0;
}
