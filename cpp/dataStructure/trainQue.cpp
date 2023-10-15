#include<iostream>
#include<vector>
using namespace std;

class ArrayQueue{
private:
	int* nums;
	int front;
	int queSize;
	int queCapacity;
public:
	ArrayQueue(int capacity){
		//
		nums = new int[capacity];
		queCapacity = capacity;
		front = 0;
		queSize = 0;
	}
	~ArrayQueue(){
		delete[] nums;
	}
	
	// 获取队列的容量
	int capacity(){
		return queCapacity;
	}
	// 获取队列的长度
	int size(){
		return queSize;
	}
	// 判断队列是否为空
	bool empty(){
		return size() == 0;
	}
	
	// 入队
	void push(int num){
		if(queSize == queCapacity){
			cout<<"队列已满"<<endl;
			return;
		}
        if(num > nums[front + queSize - 1]){
            // 计算队尾指针, 指向队尾索引 + 1
            // 通过取余操作, 实现 rear 越界时回归到数组头部
            int rear = (front + queSize);
            // 将 num 添加至队尾
            nums[rear] = num;
            queSize++;
            return;
        }
        for(int i = 0; i < queSize; i++){
            if(num < nums[(front + i)]){
                nums[(front + i)] = num;
                return;
            }
        }
	}
	// 出队
	void pop(){
		int num = peek();
		// 对手指针向后移动一位, 并实现越界时的回归
		front = (front + 1) % queCapacity;
		queSize--;
	}
	// 访问队首元素
	int peek(){
		if(empty()){
			throw out_of_range("队列为空");
		}
		return nums[front];
	}

	// 将数组转化为 vector 并返回
	vector<int> toVector(){
		// 仅转换有效长度范围内的列表元素
		vector<int> arr(queSize);
		for(int i = 0, j = front; i < queSize; i++, j++){
            arr[i] = nums[j % queCapacity];
		}
		return arr;
	}
};

int main(){
    ArrayQueue trainQue(1000001);
    int n;
    cin>>n;
    for(int i = 0; i < n; i++){
        int tmp;
        cin>>tmp;
        trainQue.push(tmp);
    }
    cout<<trainQue.size();
}