#include<iostream>
using namespace std;

template<class T>
struct Node{
    T data;
    Node<T>* prior,* next;
    Node(T& data):data(data), prior(NULL), next(NULL){}
};

template<class T>
class DoublyLinkedList{
private:
    Node<T>* head,* rear;
    int length;
public:
    DoublyLinkedList(){
        head = NULL;
        rear = NULL;
        length = 0;
    }

    bool isEmpty(){
        return length == 0;
    }

    Node<T>* getNode(int idx){
        if(idx < 0 || idx >= length){
            return NULL;
        }else{
            Node<T>* ret = head;
            for(int i = 0; i < idx; i++){
                ret = ret->next;
            }
            return ret;
        }
    }

    int locateNode(T data){
        if(isEmpty()){
            return -1;
        }else {
            int idx = -1;
            Node<T>* it = head;
            for(int i = 0; i < length; i++){
                if(it->data == data){
                    idx = i;
                    return idx;
                }
                it = it->next;
            }
            return -1;
        }
    }

    void doInsert(T& data){
        if(head == NULL){
            head = new Node<T>(data);
            rear = head;
            length++;
            return;
        }
        Node<T>* tmp = new Node<T>(data);
        tmp->prior = rear;
        rear->next = tmp;
        rear = tmp;
        length++;
    }
    void doInsert(T& data, int idx){
        if(idx < 0 || idx > length){
            return;
        }else if(idx == length){
            doInsert(data);
        }else{
            Node<T>* tmp = new Node<T>(data);
            if(idx == 0){
                tmp->next = head;
                head->prior = tmp;
                head = tmp;
            }else{
                Node<T>* pivot = getNode(idx);
                pivot->prior->next = tmp;
                tmp->prior = pivot->prior;
                tmp->next = pivot;
                pivot->prior = tmp;
            }
        }
        length++;
    }
};

int main(){
    DoublyLinkedList<int> list;
    for(int i = 0; i < 10; i++){
        list.doInsert(i);
    }
    int i = 233;
    list.doInsert(i, 5);
    cout<<"hello"<<list.locateNode(i)<<endl;
}