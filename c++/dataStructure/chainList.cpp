#include<iostream>
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

    int printAll(){
        ChainNode<T>* tmp = head;
        while(tmp != NULL){
            cout<<tmp->getData()<<"\t";
            tmp = tmp->getNext();
        }
        return 1;
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

        return 1;
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

int main(){
    ChainList<int> list1;
    for(int i = 0; i < 10; i++){
        list1.doInsert(i);
    }
    list1.doInsert(3, 233);
    list1.doDelete(4);
    cout<<(*list1.getHead()).getData()<<endl;
    cout<<(*list1.getRear()).getData()<<endl;
    list1.printAll();
    list1.doReverse(list1.getHead());
    list1.printAll();
}