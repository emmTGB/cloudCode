#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int kthGrammar(int n, int k) {
        return n == 1 ? 0 : (!(k % 2)) ^ kthGrammar(n - 1, (k + 1) / 2);
    }
};

int main() {
    Solution s;
    cout << s.kthGrammar(4, 6);
    cout << s.kthGrammar(2, 2);
}