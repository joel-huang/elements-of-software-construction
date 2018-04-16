from random import randint

# define the range of ascii alphanums
ascii_alphanums = [(48,57),(65,90),(97,122)]
fuzz = ""

# generate chars
for i in range(0,32):
    # pick a random alphanumeric class from the ascii alphanums
    ascii_index = randint(0,2)
    # pick a random char from these alphanumeric classes and add it to the fuzzer
    ascii_char = randint(ascii_alphanums[ascii_index][0],ascii_alphanums[ascii_index][1])
    fuzz += chr(ascii_char)

print(fuzz)