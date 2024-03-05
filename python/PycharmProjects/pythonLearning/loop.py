numberList = [10, 20, 30, 40, 50, 60]

for a in numberList:  # 遍历numberList中的元素
    print(a)

for b in numberList[1:4]:  # 利用切片遍历numberList中的部分元素
    print(b)

dic = {"a": 1, "n": 2, "d": 3}
for key in dic:  # 遍历dic键值
    print(key)
for key in dic.keys():  # 遍历dic的键值
    print(dic[key])  # 输出对应值

total = 0
count = 0
for a in numberList:
    total += a  # 累加
    count += 1  # 计数

print(f"count:{count} total:{total}")  # print(f"***{a}***")会将打括号内作为变量传入
print(numberList)

count = 0
while count < 3:
    print(numberList[count])
    if numberList == 20:
        break
    count += 1

