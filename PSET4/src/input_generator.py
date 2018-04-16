from random import randint

class Digit:
    def __init__(self, number):
        self.number = str(number)

class Integer(Digit):
    def __init__(self, digit):
        self.digit = digit

    def append(x,y):
        return str(x) + str(y)

class Factor(Integer):
    def __init__(self, integer):
        self.integer = integer

    def negative(self,factor):
        return "-" + str(factor)

    def parentheses(self,factor):
        return "(" + str(factor) + ")"

    def decimal_point(self,before,after):
        return str(before) + "." + str(after)

class Term(Factor):
    def __init__(self, factor):
        self.factor = factor

    def multiply(self,term,factor):
        a = str(term)
        b = str(factor)
        return a + "*" + b

    def divide(self,term,factor):
        a = str(term)
        b = str(factor)
        return a + "/" + b

class Expression(Term):
    def __init__(self, term):
        self.term = term

    def add(self,exp,term):
        a = str(exp)
        b = str(term)
        return a + "+" + b

    def subtract(self,exp,term):
        a = str(exp)
        b = str(term)
        return a + "-" + b

classes = {
    0: Expression,
    1: Term,
    2: Factor,
    3: Integer,
    4: Digit
}

operations = {
    0: Expression.add,
    1: Expression.subtract,
    2: Term.multiply,
    3: Term.divide,
    4: Factor.negative,
    5: Factor.parentheses,
    6: Factor.decimal_point,
    7: Integer.append
}


one_more = 1

while (one_more):

    new_class = randint(0,4)
    new_operation = randint(0,7)

    e = classes[new_class]
    print(e)


    one_more = randint(0,1)