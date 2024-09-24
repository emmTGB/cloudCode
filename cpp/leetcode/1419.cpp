#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int n = 0, ans = 0;
        for (auto cc : croakOfFrogs) {
            switch (cc) {
            case 'c':
                n++;
                ans = max(ans, n);
                c++;
                break;
            case 'r':
                r++;
                break;
            case 'o':
                o++;
                break;
            case 'a':
                a++;
                break;
            case 'k':
                k++;
                n--;
                break;
            }
            if (c < r || r < o || o < a || a < k) {
                return -1;
            }
        }
        if (!(c == r && r == o && o == a && a == k))
            return -1;
        return ans;
    }
};

int main() {
    Solution s;
    cout << s.minNumberOfFrogs("crocakcroraoakk");
}