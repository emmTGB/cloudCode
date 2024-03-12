def hi_poet(*args):
    for i in range(len(args)):
        if i == 0:
            print(args[i], end="")
        else:
            print(" " + args[i], end="")