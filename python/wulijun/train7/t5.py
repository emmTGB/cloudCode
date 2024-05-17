def average(array):
    # your code goes here
    return round(sum(set(arr)) / len(set(arr)),3)
 
 
 
if __name__ == '__main__':
    arr = list(map(int, input().split()))
    result = average(arr)
    print(result)