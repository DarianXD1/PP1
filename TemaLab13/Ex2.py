from more_itertools import map_reduce
from functional import seq


def mapare(text):
    return list(seq(text).map(lambda x: (x[0], x)))


def reducere(text):
    return list(seq(text).map(lambda x: x[1]).group_by(lambda x: x[0]))


if __name__ == '__main__':
    text = ["bcd", "ade", "bcdef", "abcd", "cde", "efg"]
    firstChar = map_reduce(text, lambda x: x[0])
    print(sorted(firstChar.items()))

    print(sorted(reducere(mapare(text))))

