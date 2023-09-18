# top10 选择排序
import random


def selectionSort(a):
    n = len(a)
    for i in range(n - 1):
        mini = i
        for j in range(i + 1, n):
            if a[j] < a[mini]:
                mini = j
        a[i], a[mini] = a[mini], a[i]  # 将两个数进行交换


a = [4, 5, 3, 7, 8, 2, 23, 6, 1]
selectionSort(a)
print(a)


# top9 冒泡排序
def bubbleSort(a):
    n = len(a)
    for i in range(n - 1, -1, -1):  # range的结束点不能为0，否则长度为2的数组将会陷入死循环
        for j in range(0, i):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
    print(a)


a = [4, 5, 3, 7, 8, 2, 23, 6, 1]
bubbleSort(a)


#  top8 插入排序
def insertionSort(a):
    n = len(a)
    for i in range(1, n):
        for j in range(i - 1, -1, -1):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
            else:
                break
    print(a)


a = [4, 5, 3, 7, 8, 2, 23, 6, 1]
insertionSort(a)


# top7 归并排序
def merge(a, start, mid, end):
    tmp = []
    l = start
    r = mid + 1
    while l <= mid and r <= end:
        if a[l] <= a[r]:
            tmp.append(a[l])
            l += 1
        else:
            tmp.append(a[r])
            r += 1
    tmp.extend(a[l:mid + 1])
    tmp.extend(a[r:end + 1])
    for i in range(start, end + 1):
        a[i] = tmp[i - start]


def mergeSort(a, start, end):
    if start == end:
        return
    mid = (start + end) // 2
    mergeSort(a, start, mid)
    mergeSort(a, mid + 1, end)
    merge(a, start, mid, end)  # 后序递归


a = [4, 5, 3, 7, 8, 2, 23, 6, 1]
mergeSort(a, 0, 8)
print(a)


# top6 桶排序     此代码疑似有问题，只是拿来排序连号的
# selectionSort(a)
def bucketSort(a):
    minV = min(a)
    maxV = max(a)
    bucketNum = 3
    bucket = [[], [], []]
    perBucket = (maxV - minV + bucketNum) // bucketNum

    for tmp in a:
        bucketIndex = (tmp - minV) // perBucket
        bucket[bucketIndex].append(tmp)

    for i in range(bucketNum):
        selectionSort(bucket[i])

    index = 0
    for i in range(bucketNum):
        for n in bucket[i]:
            a[index] = n
            index += 1


a = [4, 5, 3, 7, 8, 2, 23, 6, 1]
bucketSort(a)
print(a)


# top5 计数排序 空间换时间 适用范围较窄,适用于小数字范围的可重复键值
def countingSort(a):
    n = len(a)
    cntlen = max(a) + 1
    cnt = [0] * cntlen
    for ele in a:
        cnt[ele] += 1
    n = 0
    for ind in range(0, cntlen):
        while cnt[ind] > 0:
            cnt[ind] -= 1
            a[n] = ind
            n += 1


a = [2, 3, 1, 3, 2, 5, 3, 2, 6, 4, 0, 2]
countingSort(a)
print(a)


# top4 基数排序 本质为桶排序的拓展 生成编号0-9的桶，按照个位数字进行划分放回后再进行十位数的划分以此类推
def radixSort(a):
    base = 1
    maxV = max(a)
    while base < maxV:
        bucket = []
        for idx in range(10):
            bucket.append([])
        for num in a:
            idx = num // base % 10
            bucket[idx].append(num)
        i = 0
        for idx in range(10):
            for ele in bucket[idx]:
                a[i] = ele
                i += 1
        base *= 10


# 迭代次数由数字位数决定


a = [1234, 342, 1112, 3333, 4231, 1999, 22, 1, 4, 2222]  # 若有复数，则需要对每个元素加上最小的数并在最后减回去
radixSort(a)
print(a)


# top3 快速排序 最坏情况逆/顺序O(n^2)
def quickSortPivot(a, start, end):
    pivot = start
    j = start + 1
    for i in range(start + 1, end + 1):
        if a[i] <= a[pivot]:
            a[i], a[j] = a[j], a[i]
            j += 1
    # 此段for对数组按照首位基准数进行分治，其中j指向的为比基准数大的分区左边界，若基准数最大则j会随着i遍历到end + 1，最后基准数位于end
    a[pivot], a[j - 1] = a[j - 1], a[pivot]
    pivot = j - 1
    return pivot


def quickSort(a, start, end):
    if start >= end:
        return
    pivot = quickSortPivot(a, start, end)
    quickSort(a, start, pivot - 1)
    quickSort(a, pivot + 1, end)


a = [1321, 3, 5, 2, -220, 222, 1023, 2.33]
quickSort(a, 0, len(a) - 1)
print(a)


# top2 随机快速排序
def randomQuickSortPivot(a, start, end):
    randIdx = random.randint(start, end)  # randint左右界都能取到
    a[start], a[randIdx] = a[randIdx], a[start]
    pivot = start
    j = start + 1
    for i in range(start + 1, end + 1):
        if a[i] <= a[pivot]:
            a[i], a[j] = a[j], a[i]
            j += 1
    # 此段for对数组按照首位基准数进行分治，其中j指向的为比基准数大的分区左边界，若基准数最大则j会随着i遍历到end + 1，最后基准数位于end
    a[pivot], a[j - 1] = a[j - 1], a[pivot]
    pivot = j - 1
    return pivot


def randomQuickSort(a, start, end):
    if start >= end:
        return
    pivot = randomQuickSortPivot(a, start, end)
    randomQuickSort(a, start, pivot - 1)
    randomQuickSort(a, pivot + 1, end)


a = [1321, 3, 5, 2, -220, 222, 1023, 2.33]
quickSort(a, 0, len(a) - 1)
print(a)


# top1 希尔排序O(n^1.5) 由于其跳跃交换的手段，稳定性一般
def shellSort(a):
    n = len(a)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            tmp = a[i]
            j = i
            while j >= gap:
                if tmp < a[j - gap]:
                    a[j] = a[j - gap]
                else:
                    break
                j -= gap
            a[j] = tmp
        gap = gap // 2


a = [8, 2, 4, 6, 3, 12, 5, 9]
shellSort(a)
print(a)


# top0 堆排序
def maxHeapify(heap, start, end):  # 大顶堆下沉操作
    son = start * 2
    while son <= end:
        if son + 1 <= end and heap[son] < heap[son + 1]:
            son += 1
        if heap[son] > heap[start]:
            heap[start], heap[son] = heap[son], heap[start]
            start, son = son, son * 2
        else:
            break


def heapSort(a):
    heap = [None] + a
    root = 1
    length = len(heap) - 1
    for i in range(length // 2, root - 1, -1):
        maxHeapify(heap, i, length)
    # 将原数组构建成一个堆
    for i in range(length, root, -1):
        heap[i], heap[root] = heap[root], heap[i]
        maxHeapify(heap, root, i - 1)
    # 反复排除最大堆顶并继续维持剩下堆
    return heap[root:]


a = [1111, 3, 2, 5, 76, 4, 55, 22, 123]
print(heapSort(a))
