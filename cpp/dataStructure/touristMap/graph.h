#ifndef GRAGH_H
#define GRAGH_H

struct Vex
{
    int num;
    char name[20];
    char desc[1024];
};

struct Edge
{
    int vex1, vex2;
    int weight;
};

typedef struct Path
{
    int pVex[20];
    Path* next;
}*PathList;

class TourismGraph
{
private:
    int adjMatrix[20][20];
    Vex aVexs[20];
    int vexNum;

public:
    void init();
    bool insertVex(Vex vex);
    bool insertEdge(Edge edge);
    Vex getVex(int vexN);
    int findEdge(int vexN, Edge aEdge[]);
    void dfs(int vexN, bool beVisited[], int& nIndex, PathList& pList);
    void dfsTraversal(int vexN, PathList& pList);
    int findShortPath(int vexNStart, int vexNEnd, Edge aPath[]);
    int findMinTree(int nStart, Edge aEdge[]);

    int getVexNum();
};

#endif