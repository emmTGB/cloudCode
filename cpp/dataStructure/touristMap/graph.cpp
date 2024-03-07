#include"graph.h"
#include<cstring>
#include<iostream>

using namespace std;

void TourismGraph::init() {
    vexNum = 0;
    int i;
    for (i = 0; i < 20; i++) {
        aVexs[i].num = -1;
        strcpy(aVexs[i].name, "");
        strcpy(aVexs[i].desc, "");
        for (int j = 0; j < 20; j++) {
            adjMatrix[i][j] = 0;
        }
    }
}

bool TourismGraph::insertVex(Vex vex) {
    if (vexNum == 0x7fff) {
        return false;
    }
    aVexs[vexNum++] = vex;
    return true;
}

bool TourismGraph::insertEdge(Edge edge) {
    if (edge.vex1 >= vexNum || edge.vex2 >= vexNum
        || edge.vex1 < 0 || edge.vex2 < 0) {
        return false;
    }
    adjMatrix[edge.vex1][edge.vex2] = edge.weight;
    adjMatrix[edge.vex2][edge.vex1] = edge.weight;
    return true;
}

Vex TourismGraph::getVex(int vexN) {
    if (vexN < 0 || vexN >= vexNum) {
        return aVexs[0];
    }
    return aVexs[vexN];
}

int TourismGraph::findEdge(int vexN, Edge aEdge[]) {
    int k = 0;
    for (int i = 0; i < vexNum; i++) {
        if (adjMatrix[vexN][i] != 0) {
            aEdge[k++] = { vexN, i, adjMatrix[vexN][i] };
        }
    }
    return k;
}

void TourismGraph::dfs(int vexN, bool beVisited[], int& nIndex, PathList& pList) {
    beVisited[vexN] = true;
    pList->pVex[nIndex++] = vexN;
    int tVexNum = 0;
    for (int i = 0; i < vexNum; i++) {
        if (beVisited[i]) tVexNum++;
    }
    if (tVexNum == vexNum) {
        pList->next = (PathList)malloc(sizeof(PathList));
        for (int i = 0; i < vexNum; i++) {
            pList->next->pVex[i] = pList->pVex[i];
        }
        pList = pList->next;
        pList->next = NULL;
    }
    else {
        for (int i = 0; i < vexNum; i++) {
            if (!beVisited[i] && adjMatrix[vexN][i] > 0 && adjMatrix[vexN][i] != 0x7fff) {
                dfs(i, beVisited, nIndex, pList);
                beVisited[i] = false;
                nIndex--;
            }
        }
    }
}

void TourismGraph::dfsTraversal(int vexN, PathList& pList) {
    bool beVisited[0x7fff] = { false };
    int nIndex = 0;
    dfs(vexN, beVisited, nIndex, pList);
}


// Dijstra
int TourismGraph::findShortPath(int vexNStart, int vexNEnd, Edge aPath[]) {
    int flag[20], pre[20];
    int dist[20], k;

    for (int i = 0; i < vexNum; i++) {
        flag[i] = 0;
        pre[i] = 1;
        if (adjMatrix[vexNStart][i] > 0 || i == vexNStart) {
            dist[i] = adjMatrix[vexNStart][i];
            pre[i] = vexNStart;
        }
        else {
            dist[i] = 0x7fff;
        }
    }

    flag[vexNStart] = 1;
    int min;
    for (int i = 0; i < vexNum; i++) {
        min = 0x7fff;
        for (int j = 0; j < vexNum; j++) {
            if (flag[j] == 0 && dist[j] < min) {
                min = dist[j];
                k = j;
            }
        }

        flag[k] = 1;
        if (k == vexNEnd)
            break;
        for (int j = 0; j < vexNum; j++) {
            int tmp;
            if (adjMatrix[k][j] == 0) {
                tmp = 0x7fff;
            }
            else {
                tmp = adjMatrix[k][j] + min;
            }
            if (flag[j] == 0 && tmp < dist[j]) {
                dist[j] = tmp;
                pre[j] = k;
            }
        }
    }

    int num = 0;
    int i = vexNEnd;
    while (i != vexNStart) {
        aPath[num++] = { i, pre[i], adjMatrix[pre[i]][i] };
        i = pre[i];
    }
    return num;
}

int TourismGraph::findMinTree(int vexNStart, Edge aEdge[]) {
    int flag[20], closest[20];
    int lowcost[20];
    for (int i = 0; i < vexNum; i++) {
        closest[i] = -1;
        flag[i] = 0;
        if (adjMatrix[vexNStart][i] > 0 || i == vexNStart) {
            lowcost[i] = adjMatrix[vexNStart][i];
            closest[i] = vexNStart;
        }
        else {
            lowcost[i] = 0x7fff;
        }
    }
    flag[vexNStart] = 1;

    int num = 0;
    int min, k;
    for (int i = 1; i < vexNum; i++) {
        min = 0x7fff;
        for (int j = 0; j < vexNum; j++) {
            if (flag[j] == 0 && lowcost[j] < min) {
                min = lowcost[j];
                k = j;
            }
        }
        for (int j = 0; j < vexNum; j++) {
            if (flag[j] == 0 && adjMatrix[k][j] < lowcost[j] && adjMatrix[k][j] != 0) {
                lowcost[j] = adjMatrix[k][j];
                closest[j] = k;
            }
        }
        flag[closest[k]] = 1;
        aEdge[num].vex1 = closest[k];
        aEdge[num].vex2 = k;
        aEdge[num].weight = adjMatrix[closest[k]][k];
        num++;
    }

    return num;
}

int TourismGraph::getVexNum() {
    return vexNum;
}
