#include"tourism.h"
#include"graph.h"
#include<iostream>
#include<cstring>

using namespace std;

TourismGraph tg;

void createGraph() {
    tg.init();

    int num;
    FILE* inVex = fopen("vex.txt", "r");
    fscanf(inVex, "%d", &num);
    for (int i = 0; i < num; i++) {
        Vex vex;
        fscanf(inVex, "%d", &(vex.num));
        fscanf(inVex, "%s", vex.name);
        fscanf(inVex, "%s", vex.desc);

        tg.insertVex(vex);
    }

    fclose(inVex);


    FILE* inEgde = fopen("edge.txt", "r");
    while (!feof(inEgde)) {
        Edge edge;
        fscanf(inEgde, "%d %d %d", &(edge.vex1), &(edge.vex2), &(edge.weight));
        tg.insertEdge(edge);
    }

    fclose(inEgde);

    return;
}

void getSpotInfo() {
    int vexNum = tg.getVexNum();
    if (vexNum == 0) {
        cout << "No data!" << endl;
        return;
    }
    for (int i = 0; i < vexNum; i++) {
        cout << tg.getVex(i).num << "-" << tg.getVex(i).name << endl;
    }
    int num;
    cin >> num;
    if (num >= vexNum) {
        cout << "No data!" << endl;
        return;
    }
    Vex vex = tg.getVex(num);
    cout << vex.desc << endl;

    Edge aEdge[100];
    int edgeSum = tg.findEdge(num, aEdge);
    for (int i = 0; i < edgeSum; i++) {
        if (aEdge[i].weight != 0x7fff) {
            cout << tg.getVex(aEdge[i].vex1).name << "->" << tg.getVex(aEdge[i].vex2).name << endl;
        }
    }
}

void travelPath() {
    int vexNum = tg.getVexNum();
    if (vexNum == 0) {
        cout << "No data!" << endl;
        return;
    }
    for (int i = 0; i < vexNum; i++) {
        cout << tg.getVex(i).num << "-" << tg.getVex(i).name << endl;
    }
    int num;
    cin >> num;
    if (num < 0 || num >= vexNum) {
        cout << "No data!" << endl;
        return;
    }

    PathList pList = (PathList)malloc(sizeof(Path));
    PathList head = pList;

    tg.dfsTraversal(num, pList);

    pList = head;
    pList = pList->next;
    int i = 1;
    while (pList != NULL) {
        cout << i << "th path:" << endl;
        for (int j = 0; j < vexNum; j++) {
            cout << tg.getVex(pList->pVex[j]).name << " ";
        }
        cout << endl;
        pList = pList->next;
        i++;
    }
    free(pList);
    pList = NULL;
    head = NULL;

    return;
}

void findShortPath() {
    int vexNum = tg.getVexNum();
    if (vexNum == 0) {
        cout << "No data!" << endl;
        return;
    }
    int nStart, nEnd;
    cin >> nStart >> nEnd;
    if (nStart > nEnd || nStart < 0 || nStart >= vexNum || nEnd < 0 || nEnd >= vexNum) {
        cout << "No data!" << endl;
        return;
    }
    if (nStart == nEnd) {
        cout << 0 << endl;
        return;
    }

    Edge aPath[0x7fff];
    int num = tg.findShortPath(nStart, nEnd, aPath);
    int path = 0;
    for (int i = num - 1; i >= 0; i--) {
        cout << tg.getVex(aPath[i].vex1).name
            << "->" << tg.getVex(aPath[i].vex2).name << endl;
        path += aPath[i].weight;
    }
    cout << path << endl;
}

void designPath() {
    int vexNum = tg.getVexNum();
    if (vexNum == 0) {
        cout << "No data!" << endl;
        return;
    }
    int n = vexNum - 1;
    int nStart;
    cin >> nStart;
    if (nStart != 0 && nStart != n) {
        cout << "No data!" << endl;
        return;
    }

    Edge aEdge[20];
    int num = tg.findMinTree(nStart, aEdge);
    int path = 0;

    for (int i = 0; i < num; i++) {
        cout << tg.getVex(aEdge[i].vex1).name << "->" << tg.getVex(aEdge[i].vex2).name
            << " " << aEdge[i].weight << endl;
        path += aEdge[i].weight;
    }
    cout << "total:" << path << endl;

    return;
}
