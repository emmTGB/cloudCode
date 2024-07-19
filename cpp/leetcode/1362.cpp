#include<iostream>
#include<vector>
#include<cmath>
using namespace std;

class Solution {
public:
    vector<int> closestDivisors(int num) {
        vector<int> sol;
        int x, y;
        x = y = int(sqrt(num + 2));
        ++num;
        while (x > 1) {
            if (num % x == 0) {
                break;
            }
            --x;
        }
        ++num;
        while (y > 1) {
            if (num % y == 0) {
                break;
            }
            --y;
        }
        if ((num - 1) / x - x > num / y - y) {
            sol.push_back(y);
            sol.push_back(num / y);
        }
        else {
            sol.push_back(x);
            sol.push_back((num - 1) / x);
        }
        return sol;
    }
};
int main() {
    int num;
    cin >> num;
    vector<int> sol = Solution().closestDivisors(num);
    for (int i = 0; i < sol.size(); ++i) {
        cout << sol[i] << " ";
    }
    return 0;
}