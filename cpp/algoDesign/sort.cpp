#include<iostream>
using namespace std;

template<class T>
int quickSortPivot(T* arr, int start, int end){
    int pivot = start;
    int j = pivot + 1;
    for(int i = j; i <= end; i++){
        if(arr[i] < arr[pivot]){
            swap(arr[i], arr[j]);
            j++;
        }
    }
    swap(arr[pivot], arr[j - 1]);
    pivot = j - 1;
    return pivot;
}

template<class T>
int quickSort(T* arr, int start, int end){
    if(start >= end){
        return 0;
    }
    int pivot = quickSortPivot<T>(arr, start, end);
    quickSort<T>(arr, start, pivot - 1);
    quickSort<T>(arr, pivot + 1, end);
    return 1;
}

template<class T>
void maxHeap(T* heap, int start, int end){
    int son = start * 2 + 1;
    while(son < end){
        if(son + 1 < end && heap[son] < heap[son + 1]){
            son++;
        }
        if(heap[start] < heap[son]){
            swap(heap[start], heap[son]);
            start = son;
            son = son * 2 + 1;
        }else{
            return;
        }
    }
}

template<class T>
int heapSort(T* heap, int length){
    int root = 0;
    for(int i = length / 2 - 1; i >= root; i--){
        maxHeap(heap, i, length);
    }
    for(int i = length - 1; i > root; i--){
        swap(heap[root], heap[i]);
        maxHeap(heap, root, i);
    }
    return 0;
}

int main(){
    int a[16] = {
        1,22,31,433,114,514,1919,810,2004,227,51,60,17,82,90,11110
    };
    quickSort<int>(a, 0, 15);
    for(int i = 0; i < 16; i++){
        cout<<a[i]<<"\t";
    }
}