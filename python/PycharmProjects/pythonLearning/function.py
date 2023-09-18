def sayHi():
    print("hello")


sayHi()


def fun1(a):
    print(f"111:{a}")


fun1("asd")

square = lambda x: x * x  # 匿名函数的定义

print(square(2))


def sumOf(a):  # 递归函数
    if a <= 0:
        return 0
    return a + sumOf(a - 1)


print(sumOf(4))
print(sumOf(100))
