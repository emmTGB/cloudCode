def pow(x:float, n:int):
    if n == 0:
        return 1
    else:
        for i in range(n-1):
            x = x*x
        return x