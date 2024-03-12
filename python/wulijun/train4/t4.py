def hi_gender(name, gender):
    if gender == "male":
        return "Hi " + name + ", nice to meet you!"
    elif gender == "female":
        return "Hi " + name + ", nice to meet you!"
    else:
        return "Hi " + name + ", nice to meet you!"

if __name__ == "__main__":
    print(hi_gender("John", "male"))
    print(hi_gender("Jane", "female"))
    print(hi_gender("Bob", "other"))