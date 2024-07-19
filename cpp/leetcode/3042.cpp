#include<iostream>
#include<vector>
using namespace std;


class Solution {
public:
    bool isPrefixAndSuffix(string& x, string& str) {
        int l = x.length();
        int L = str.length();
        if (l > L) return false;
        return x == str.substr(0, l) && x == str.substr(L - l, l);
    }

    int countPrefixSuffixPairs(vector<string>& words) {
        int l = words.size();
        int c = 0;
        for (int i = 0; i < l - 1; ++i) {
            for (int j = i + 1; j < l; ++j) {
                if (isPrefixAndSuffix(words[i], words[j])) c++;
            }
        }
        return c;
    }
};
int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<string> words(n);
        for (int i = 0; i < n; ++i) cin >> words[i];
        Solution s;
        cout << s.countPrefixSuffixPairs(words) << endl;
    }
    return 0;
}