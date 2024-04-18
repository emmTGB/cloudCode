#include<iostream>
#include<stdlib.h>
#include"tourism.h"

using namespace std;

int main() {
    while (true) {
        cout << "1. 初始化地图" << endl;
        cout << "2. 获取景点信息" << endl;
        cout << "3. 旅行规划" << endl;
        cout << "4. 查找最短路径" << endl;
        cout << "5. 电路铺设规划" << endl;
        cout << "6. 退出" << endl;
        int choice;
        cin >> choice;

        switch (choice) {
        case 1:
            createGraph();
            break;
        case 2:
            getSpotInfo();
            break;
        case 3:
            travelPath();
            break;
        case 4:
            findShortPath();
            break;
        case 5:
            designPath();
            break;
        case 6:
            exit(0);
            break;
        default:
            break;
        }
    }
}
