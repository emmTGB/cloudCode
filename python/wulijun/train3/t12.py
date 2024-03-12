for i in range(3):
    usr = input()
    pas = input()
    if (usr == "admin" or usr == "administrator") and pas == "12345":
        print("Welcome")
        break
    else:
        print("Wrong username or password")