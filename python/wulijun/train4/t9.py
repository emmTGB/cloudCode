import math
def self_div(n):
    if n == 0 or n == 1:
        return False
    if n == 2:
        return True
    for i in range(2, math.sqrt(n) + 1):
        if n % i == 0:
            return False
    return True