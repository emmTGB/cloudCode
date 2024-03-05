print(1 and 1)

# tuple 元组 只读不可修改的线性数组 每个元素的数据类型可以不同 可以使用索引定位数据元素
tup = ("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
print("Friday" in tup)
print("aaa" in tup)
# list 列表 一种可以增删改查的线性数组
lis = ['q', 'w', 'e', 'r', 't']
print(lis[3])
print(lis[1:3])  # 切片操作 取下标左开右闭区间的连续元素 若一侧缺失则取到头/尾
print(lis[:4])
lis.append('y')  # 追加，在列表尾部追加元素
lis.insert(2, 'kevin')   # 插入，将第二个参数插入到下表为第一个参数的位置并进行维护
lis.pop(3)  # 删除 删除参数位置的元素，无参数则删除最后一个元素
# dict 字典
dic = {"coffee": 1, "superCoffee": 2}
print(dic["superCoffee"])  # 调用字典通过key得到value
dic.pop("superCoffee")  # 删除key为参数的键值对
print(dic)
