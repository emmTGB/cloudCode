#include <iostream>
#include<cstring>
using namespace std;

typedef struct tagVertex {
    int row;//行
    int col;//列
    int info;//图片标号
}Vertex;
typedef struct tagFlag {
    bool bTime;//是否有及时进度条
    bool bProp;//是否有道具按钮
    bool bScore;//积分显示
}FLAG;
typedef struct tagScore {
    int nMode;//游戏模式，1休闲模式，2关卡模式
    int nGrade;//积分
    int nLevel;//积分等级
    std::string strName;//玩家姓名

}SCORE, RankArrry;

//bool GameModel::IsLink(int anMap[][4], Vertex v1, Vertex v2)
//{
//    m_nVexNum = 0;
//    PushVertex(v1);
//    // TODO: 在此处添加实现代码.
//    if (anMap[v1.row][v1.col] ==
//        anMap[v2.row][v2.col]) {
//        if (v2.row == v1.row) {//在一条直线上，行号相同
//            if (LinkInRow(anMap, v1, v2)) {
//                PushVertex(v2);
//                return true;
//            }
//        }
//        if (v2.col == v1.col) {//在一条直线上，列号相同
//            if (LinkInCol(anMap, v1, v2)) {
//                PushVertex(v2);
//                return true;
//            }
//        }
//        if (OneCornerLink(anMap, v1, v2)) {//两条线连接
//            PushVertex(v2);
//            return true;
//        }
//        if (ThreeLink(anMap, v1, v2)) {//三条直线连接即找四周联通的点能否两条线连接到终点。
//            PushVertex(v2);
//            return true;
//        }
//    }
//    PopVertex();
//    return false;
//}

bool GameModel::IsLink(CGraph& g, Vertex v1, Vertex v2)
{
    m_LogicVexNum = 0;
    m_nCorner = 0;
    for (int i = 0; i < MAX_VERTEX_NUM;i++) {
        m_anPath[i] = -1;
    }
    PushVertex(v1.row * MAX_COL + v1.col);
    if (SerchPath(g, v1.row * MAX_COL + v1.col, v2.row * MAX_COL + v2.col))
        return true;
    return false;

}

bool GameModel::SearchValidPath(CGraph& g)
{
    int nVexnum = g.GetVexnum();
    int m_first;
    Vertex v1, v2;
    for (int i = 0; i < nVexnum; i++) {
        if ((m_first = g.GetVertex(i)) != BLANK) {//查找下一个
            for (int j = i + 1; j < nVexnum;j++) {
                if (g.GetVertex(j) == m_first) {
                    v1.row = i / MAX_COL;
                    v1.col = i % MAX_COL;
                    v2.row = j / MAX_COL;
                    v2.col = j % MAX_COL;
                    if (IsLink(g, v1, v2)) {
                        return true;
                    }
                }
            }
        }
    }
    return false;

}