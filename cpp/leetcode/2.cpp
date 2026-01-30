#include<bits/stdc++.h>
using namespace std;

bool great(char c, char h) {
    auto pri = [&](char cc) -> long long {
        if (cc == '+' || cc == '-') return 1;
        if (cc == '*' || cc == '/') return 2;
        if (cc == '%' || cc == '^') return 3;
        return 0;
        };
    return pri(c) > pri(h);
}

int main() {
    stack<long long> ns;
    stack<char> ss;
    auto calc = [&]() -> void {
        long long res = 0;
        long long r = ns.top(); ns.pop();
        long long l = ns.top(); ns.pop();
        switch (ss.top())
        {
        case '+':
            res = l + r;
            break;
        case '-':
            res = l - r;
            break;
        case '*':
            res = l * r;
            break;
        case '/':
            res = l / r;
            break;
        case '%':
            res = l % r;
            break;
        case '^':
            res = pow(l, r);
            break;
        default:
            break;
        }
        ss.pop();
        ns.push(res);
        };
    string line;
    getline(cin, line);
    line.erase(remove(line.begin(), line.end(), ' '), line.end());
    bool pren = false;
    for (char c : line) {
        if (c >= '0' && c <= '9') {
            if (pren) {
                long long tmp = ns.top(); ns.pop();
                ns.push(tmp * 10 + (c - '0'));
            }
            else {
                ns.push(c - '0');
                pren = true;
            }
        }
        else {
            pren = false;
            while (!ss.empty() && !great(c, ss.top()) && !ns.empty()) {
                calc();
            }
            ss.push(c);
        }
    }
    while (!ss.empty() && !ns.empty()) calc();
    if (ns.empty()) cout << 0;
    else cout << ns.top();
}