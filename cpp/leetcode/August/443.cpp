#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    int compress(vector<char>& cs) {
        string s = "";
        cs.push_back('\n');
        int cnt = 1;
        char pre = '\0';
        for (char c : cs) {
            if (c != pre) {
                if (cnt > 1) {
                    s += to_string(cnt);
                }
                s += c;
                pre = c;
                cnt = 1;
            }
            else {
                cnt++;
            }
        }
        cs.clear();
        s.erase(s.end() - 1);
        for (char c : s) {
            cs.push_back(c);
        }

        return s.length();
    }
};

// 原地压缩 空间O(1)
class BetterSolution {
public:
    int compress(vector<char>& chars) {
        int n = chars.size();
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {  // 当前字符为右边界
                chars[write++] = chars[read];  // 将字符类型写入结果数组
                int num = read - left + 1;  // 起点与终点作差得到连续重复字符长度
                if (num > 1) {
                    int anchor = write;  // 在字符后一位设置锚点，作为个位的指针
                    while (num > 0) {
                        chars[write++] = num % 10 + '0';
                        num /= 10;
                    }
                    reverse(&chars[anchor], &chars[write]);  // 反转数字使其正序
                }
                left = read + 1;
            }
        }
        return write;
    }
};

