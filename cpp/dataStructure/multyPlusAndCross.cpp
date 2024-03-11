#include<iostream>
using namespace std;
#define multiList ChainList<pair<int, int>>
#define multiNode ChainNode<pair<int,int>>

template<class T>
class ChainNode{
private:
    T nodeData;
    ChainNode<T>* next;
public:
    ChainNode(const T &nodeData){
        this->nodeData = nodeData;
        next = NULL;
    }
    int setNext(ChainNode<T>* nextNode){
        next = nextNode;
        return 1;
    }
    void setData(const T &data){
        nodeData = data;
    }
    ChainNode<T>* getNext(){
        return next;
    }
    T& getData(){
        return nodeData;
    }
};

template<class T>
class ChainList{
private:
    ChainNode<T>* head,* rear;
    int length;
public:
    ChainList<T>(){
        head = NULL;
        rear = NULL;
        length = 0;
    }

    ChainNode<T>* getHead(){
        return head;
    }
    ChainNode<T>* getRear(){
        return rear;
    }
    int getLength(){
        return length;
    }

    virtual ChainNode<T>* getNode(int i){
        if(i < 0 || i >= length){
            return NULL;
        }else{
            ChainNode<T>* ret = head;
            while(i > 0){
                ret = ret->getNext();
                i--;
            }
            return ret;
        }
    }
    virtual int doInsert(int i,const T &data){
        if(i > length){
            return 0;
        }

        ChainNode<T>* tmp = head, * node = new ChainNode<T>(data);
        if(i == 0){
            node->setNext(head);
            head = node;
            if(i == length){
                rear = node;
            }
        }else if(i == length){
            rear->setNext(node);
            rear = node;
        }else{
            while(i > 1){
                tmp = tmp->getNext();
                i--;
            }
            node->setNext(tmp->getNext());
            tmp->setNext(node);
            if(tmp->getNext() == NULL){
                rear = tmp;
            }
        }
        length++;
        return 1;
    }
    virtual int doInsert(ChainNode<T>* pre, const T &data){
        ChainNode<T>* node = new ChainNode<T>(data);
        node->setNext(pre->getNext());
        pre->setNext(node);
        if(!node->getNext()) rear = node;
        length++;
        return 1;
    }
    virtual int doInsert(const T &data){
        ChainNode<T>* node = new ChainNode<T>(data);
        if(head == NULL){
            head = node;
            rear = node;
        }else{
            rear->setNext(node);
            rear = node;
        }
        length++;
        return 1;
    }
    virtual int doDelete(int i){
        if(length == 0 || i >= length || i < 0){
            return 0;
        }
        ChainNode<T>* tmp1 = head, * tmp2;
        if(i == 0){
            head = head->getNext();
            delete tmp1;
        }
        while(i > 1){
            tmp1 = tmp1->getNext();
            i--;
        }
        tmp2 = tmp1->getNext();
        tmp1->setNext(tmp2->getNext());
        if(tmp1->getNext() == NULL){
            rear = tmp1;
        }
        length--;

        return 1;
    }

    void printElems(){
        ChainNode<T>* tmp = head;
        bool flag = false;
        while(tmp){
            if(printp(tmp->getData())){
                flag = true;
            }
            if(flag && tmp->getNext() && tmp->getNext()->getData().first){
                cout<<" ";
            }
            tmp = tmp->getNext();
        }
        if(!flag){
            cout<<0<<" "<<0;
        }
        cout<<endl;
    }

    ChainNode<T>* doReverse(ChainNode<T>* head){
        if(head == NULL){
            return NULL;
        }
        if(head->getNext() == NULL){
            this->head = head;
            return head;
        }
        doReverse(head->getNext())->setNext(head);
        head->setNext(NULL);
        return head;
    }

    bool printp(pair<int, int> p){
        if(p.first){
            cout<<p.first<<" "<<p.second;
            return true;
        }
        return false;
    }
};

void doMultyDerive(ChainList<int>* l){
    for(int i = 0; i < l->getLength() - 1; i += 2){
        if(!l->getNode(i + 1)->getData()){
            l->doDelete(i);
            l->doDelete(i);
            if(!l->getLength() || !l->getNode(i)){
                return;
            }
        }else{
            l->getNode(i)->setData(l->getNode(i)->getData() * l->getNode(i + 1)->getData());
            l->getNode(i + 1)->setData(l->getNode(i + 1)->getData() - 1);
        }
    }
}

void doMultyCross(multiList* l1, multiList* l2, multiList* ret){
    multiNode* pivot1,* pivot2,* pivotR;
    pivot1 = l1->getHead();
    while(pivot1){
        pivot2 = l2->getHead();
        while(pivot2){
            int par, expo;
            pivotR = ret->getHead();
            par = pivot1->getData().first * pivot2->getData().first;
            expo = pivot1->getData().second + pivot2->getData().second;

            if(pivotR){
                if(pivotR->getData().second > expo){
                    while(pivotR->getNext()){
                        if(pivotR->getNext()->getData().second > expo){
                            pivotR = pivotR->getNext();
                        }else if(pivotR->getNext()->getData().second == expo){
                            pivotR->getNext()->getData().first += par;
                            break;
                        }else{
                            ret->doInsert(pivotR, make_pair(par, expo));
                            break;
                        }
                    }
                    if(!pivotR->getNext()) ret->doInsert(pivotR, make_pair(par, expo));
                }else if(pivotR && pivotR->getData().second == expo){
                    pivotR->getData().first += par;
                }else{
                    ret->doInsert(0, make_pair(par, expo));
                }
            }else{
                ret->doInsert(0, make_pair(par, expo));
            }
            pivot2 = pivot2->getNext();
        }
        pivot1 = pivot1->getNext();
    }
}

void doMultyPlus(multiList* l1, multiList* l2, multiList* ret){
    multiNode* pivot,* pivotR;
    pivot = l1->getHead();
    while(pivot){
        int par, expo;
        pivotR = ret->getHead();
        par = pivot->getData().first;
        expo = pivot->getData().second;

        if(pivotR){
            if(pivotR->getData().second > expo){
                while(pivotR->getNext()){
                    if(pivotR->getNext()->getData().second > expo){
                        pivotR = pivotR->getNext();
                    }else if(pivotR->getNext()->getData().second == expo){
                        pivotR->getNext()->getData().first += par;
                        break;
                    }else{
                        ret->doInsert(pivotR, make_pair(par, expo));
                        break;
                    }
                }
                if(!pivotR->getNext()) ret->doInsert(pivotR, make_pair(par, expo));
            }else if(pivotR && pivotR->getData().second == expo){
                pivotR->getData().first += par;
            }else{
                ret->doInsert(0, make_pair(par, expo));
            }
        }else{
            ret->doInsert(0, make_pair(par, expo));
        }
        pivot = pivot->getNext();
    }
    pivot = l2->getHead();
    while(pivot){
        int par, expo;
        pivotR = ret->getHead();
        par = pivot->getData().first;
        expo = pivot->getData().second;

        if(pivotR){
            if(pivotR->getData().second > expo){
                while(pivotR->getNext()){
                    if(pivotR->getNext()->getData().second > expo){
                        pivotR = pivotR->getNext();
                    }else if(pivotR->getNext()->getData().second == expo){
                        pivotR->getNext()->getData().first += par;
                        break;
                    }else{
                        ret->doInsert(pivotR, make_pair(par, expo));
                        break;
                    }
                }
                if(!pivotR->getNext()) ret->doInsert(pivotR, make_pair(par, expo));
            }else if(pivotR && pivotR->getData().second == expo){
                pivotR->getData().first += par;
            }else{
                ret->doInsert(0, make_pair(par, expo));
            }
        }else{
            ret->doInsert(0, make_pair(par, expo));
        }
        pivot = pivot->getNext();
    }
}

int main(){
    ChainList<pair<int, int>>* list[2];
    int n[2];
    for(int t = 0; t < 2; t++){
        cin>>n[t];
        list[t] = new ChainList<pair<int, int>>;
        for(int i = 0; i < n[t]; i++){
            int par, expo;
            cin>>par>>expo;
            list[t]->doInsert(make_pair(par, expo));
        }
    }
    multiList* cross = new multiList();
    doMultyCross(list[0], list[1], cross);
    cross->printElems();
    multiList* plus = new multiList();
    doMultyPlus(list[0], list[1], plus);
    plus->printElems();
    // SqlList<int>* list[2];
    // int n[2];
    // for(int t = 0; t < 2; t++){
    //     cin>>n[t];
    //     list[t] = new SqlList<int>(n[t] * 2);
    //     for(int i = 0; i < 2 * n[t]; i++){
    //         int num;
    //         cin>>num;
    //         list[t]->doInsert(num);
    //     }
    // }
    // SqlList<int>* plus = new SqlList<int>(2 * (n[0] + n[1]));
    // SqlList<int>* cross = new SqlList<int>(2 * (n[0] * n[1]) + 1);
    // doMultyCross(list[0], list[1], cross);
    // doMultyPlus(list[0], list[1], plus);
    // cross->printElems();
    // plus->printElems();
    
    // ChainList<int>* l = new ChainList<int>();
    // int m;
    // while(cin>>m){
    //     l->doInsert(m);
    // }
    // // l->doInsert(0);
    // doMultyDerive(l);
    // l->printElems();
}
