from random import randint

strings = ['no_bugs','SUTD','0123456789','a']

def mutation(inp):

    # check if length > 1
    if (len(inp) < 2):
        print("can't swap if only 1 char")
        return

    # get a random position within inp length, ignore the last char
    position = randint(0,len(inp)-2)

    # slice into two strings at the position
    # change the left's last char to the right's first char
    # change the right's first char to the left's last char
    left = inp[:position] + inp[position+1]
    right = inp[position] + inp[position+2:]
    print(left+right)

for string in strings:
    mutation(string)