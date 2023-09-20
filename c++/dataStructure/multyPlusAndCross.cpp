#include<iostream>
#include<map>
#include<unordered_map>
using namespace std;

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
    T getData(){
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
        }
        if(i == length){
            rear->setNext(node);
            rear = node;
        }
        while(i > 1){
            tmp = tmp->getNext();
            i--;
        }
        node->setNext(tmp->getNext());
        tmp->setNext(node);
        if(tmp->getNext() == NULL){
            rear = tmp;
        }
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
        if(length == 0 || i >= length){
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
        delete tmp2;
        if(tmp1->getNext() == NULL){
            rear = tmp1;
        }
        length--;

        return 1;
    }

    void printElems(){
        ChainNode<T>* tmp = head;
        if(!tmp || !tmp->getNext()){
            cout<<0<<" "<<0;
        }
        while(tmp){
            cout<<tmp->getData();
            if(tmp->getNext()){
                cout<<" ";
            }
            tmp = tmp->getNext();
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
};


template <class T>
class SqlList{
protected:
    T* elem;
    int length;
    int maxSize;
public:
    SqlList(int maxSize){
        this->maxSize = maxSize;
        elem = new T[maxSize + 1];
        length = 0;
    }

    bool isEmpty(){
        return length == 0;
    }
    T getElem(int i){
        return elem[i];
    }
    int locateElem(T &elem){
        int ret = -1;
        for(int i = 0; i < length; i++){
            if(this->elem[i] == elem){
                ret = i;
                break;
            }
        }
        return ret;
    }
    int getLength(){
        return length;
    }
    T* getStart(){
        return elem;
    }
    T* getEnd(){
        if(length){
            return &elem[length];
        }else{
            return NULL;
        }
    }
    int getMaxSize(){return maxSize;}

    virtual int doInsert(int i, T e){
        if(i >= 0 && i <= length && length <= maxSize){
            if(i == length){
                elem[i] = e;
            }else{
                for(int j = length; j > i; j--){
                    elem[j] = elem[j - 1];
                }
                elem[i] = e;
            }
            length++;
            return 1;
        }else{
            return 0;
        }
    }
    virtual int doDelete(int i, T &e){
        if(i >= 0 && i < length){
            e = elem[i];
            length--;
            for(int j = i; j < length; j++){
                elem[j] = elem[j + 1];
            }
            return 1;
        }else{
            return 0;
        }
    }
    virtual int doInsert(T e){
        if(length <= maxSize){
            elem[length] = e;
            length++;
            return 1;
        }
        return 0;
    }
    int printElems(){
        if(length > 0){
            for(int i = 0; i < length; i++){
                cout<<elem[i];
                if(i != length - 1){
                    cout<<" ";
                }
            }
            cout<<endl;
            return 1;
        }
        return 0;
    }
    int doReverse();

    template<class T1>
    friend SqlList<T1> operator+(SqlList<T1> &, SqlList<T1> &);
};

template<class T>
SqlList<T> operator+(SqlList<T> &al, SqlList<T> &bl){
    unordered_map<T, int> map;
    if(al.getLength() == 0 || bl.getLength() == 0){
        return al.getLength() ? al : bl;
    }else{
        for(int i = 0; i < al.getLength(); i++){
            map.insert(pair<T, int>(al.getElem(i), 1));
        }
        for(int j = 0; j < bl.getLength(); j++){
            map.insert(pair<T, int>(bl.getElem(j), 1));
        }
        SqlList<T> cl(2 * (al.getLength() + bl.getLength()));
        for(auto it = map.begin(); it != map.end(); it++){
            cl.doInsert(it->first);
        }
        return cl;
    }
}

template<class T>
int SqlList<T>::doReverse(){
    int start = 0, end = length - 1;
    int ret = 0;
    while(start < end){
        swap(elem[start], elem [end]);
        start++;
        end--;
        ret = 1;
    }
    return ret;
}

void doMultyCross(SqlList<int>* l1, SqlList<int>* l2, SqlList<int>* ret){
    map<int, int, greater<int>> m;
    for(int i = 0; i < l1->getMaxSize(); i += 2){
        for(int j = 0; j < l2->getMaxSize(); j += 2){
            int pow = l1->getElem(i + 1) + l2->getElem(j + 1);
            int par = l1->getElem(i) * l2->getElem(j);
            if(auto it = m.find(pow) != m.end()){
                m[pow] += par;
            }else{
                m.insert(pair<int, int>(pow, par));
            }
        }
    }
    bool flag = true;
    for(auto it : m){
        if(it.second){
            ret->doInsert(it.second);
            ret->doInsert(it.first);
            flag = false;
        }
    }
    if(flag){
        ret->doInsert(0);
        ret->doInsert(0);
    }
}

void doMultyPlus(SqlList<int>* l1, SqlList<int>* l2, SqlList<int>* ret){
    map<int, int, greater<int>> m;
    for(int i = 0; i < l1->getMaxSize(); i += 2){
        m.insert(pair<int, int>(l1->getElem(i + 1), l1->getElem(i)));
    }
    for(int i = 0; i < l2->getMaxSize(); i += 2){
        if(auto it = m.find(l2->getElem(i + 1)) != m.end()){
            m[l2->getElem(i + 1)] += l2->getElem(i);
        }else{
            m.insert(pair<int, int>(l2->getElem(i + 1), l2->getElem(i)));
        }
    }
    bool flag = true;
    for(auto it : m){
        if(it.second){
            ret->doInsert(it.second);
            ret->doInsert(it.first);
            flag = false;
        }
    }
    if(flag){
        ret->doInsert(0);
        ret->doInsert(0);
    }
}

void doMultyDerive(ChainList<int>* l){
    for(int i = 0; i < l->getLength() - 1; i += 2){
        if(!l->getNode(i + 1)->getData()){
            l->doDelete(i);
            l->doDelete(i);
            if(!l->getNode(i)){
                return;
            }
        }
        l->getNode(i)->setData(l->getNode(i)->getData() * l->getNode(i + 1)->getData());
        l->getNode(i + 1)->setData(l->getNode(i + 1)->getData() - 1);
    }
}

int main(){
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
    
    ChainList<int>* l = new ChainList<int>();
    // int m;
    // while(cin>>m){
    //     l->doInsert(m);
    // }
    l->doInsert(0);
    doMultyDerive(l);
    l->printElems();
}
