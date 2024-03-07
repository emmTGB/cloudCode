
a = float(input(""))
b = float(input(""))

ans1 = "{:.3f}".format(a + b)
ans2 = "{:.3f}".format(a - b)
ans3 = "{:.3f}".format(a * b)
ans4 = "{:.3f}".format(a / b)

print("{a} + {b} = {ans1}".format(a=a, b=b, ans1=ans1))
print("{a} - {b} = {ans2}".format(a=a, b=b, ans2=ans2))
print("{a} * {b} = {ans3}".format(a=a, b=b, ans3=ans3))
print("{a} / {b} = {ans4}".format(a=a, b=b, ans4=ans4))