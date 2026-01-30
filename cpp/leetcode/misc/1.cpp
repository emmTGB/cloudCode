#include <iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    vector<int> v;
    while (a) {
        v.push_back(a % 10);
        a /= 10;
    }
    sort(v.begin(), v.end());
    int sum = 0;
    for (int i = 0; i < b; ++i) {
        sum += v[i];
    }
    int p = 10;
    for (int i = b; i < v.size(); ++i) {
        sum += p * v[i];
        p *= 10;
    }
    cout<< sum;
}
// 64 位输出请用 printf("%lld")