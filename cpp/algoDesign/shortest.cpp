#include<bits/stdc++.h>
#define x first
#define y second
using namespace std;

typedef pair<double, double> pdd;

const int maxn = 400005;
pdd p[maxn];

inline double dis(pdd a, pdd b) {
    return sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
}

bool cmpx(pdd a, pdd b) {
    return a.x < b.x;
}

bool cmpy(int a, int b) {
    return p[a].y < p[b].y;
}

double solve(int l, int r) {  // 点下标在l~r之间的最近点距离
    if (r - l + 1 == 2)
        return dis(p[l], p[r]);
    if (r - l + 1 == 3)
        return min(min(dis(p[l], p[l + 1]), dis(p[l + 1], p[l + 2])), dis(p[l], p[l + 2]));
    int m = l + r >> 1;
    double dl = solve(l, m);  // 递归解决左侧问题
    double dr = solve(m + 1, r);  // 递归解决右侧问题
    double d = min(dl, dr);
    vector<int> temp;  // 保存一些可能更新最短距离的点
    for (int i = l; i <= r; i++)
        if (fabs(p[i].x - p[m].x) < d)
            temp.push_back(i);
    sort(temp.begin(), temp.end(), cmpy);  // 按照y大小进行排序
    // 逐个检查是否可以更新
    for (int i = 0; i < temp.size(); i++)
        for (int j = i + 1; j < temp.size() && p[temp[j]].y - p[temp[i]].y < d; j++)
            d = min(dis(p[temp[i]], p[temp[j]]), d);
    return d;
}

int main() {
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
        scanf("%lf%lf", &p[i].x, &p[i].y);
    sort(p + 1, p + 1 + n, cmpx);  // 按照x大小进行排序
    double ans = solve(1, n);
    printf("%.4f\n", ans);
    return 0;
}