#include<iostream>
#include"graph.h"
#include"tourism.h"

using namespace std;

int main() {
    while (true) {
        cout << "1. create graph" << endl;
        cout << "2. get spot info" << endl;
        cout << "3. travel path" << endl;
        cout << "4. find shortest path" << endl;
        cout << "5. design path" << endl;
        cout << "6. exit" << endl;
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
