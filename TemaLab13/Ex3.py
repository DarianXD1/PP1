from functional import seq


def chunks(lista, dimensiune):
    return (lista[i:i + dimensiune] for i in range(0, len(lista), dimensiune))


if __name__ == '__main__':
    lista = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]
    print(seq(list(chunks(list(seq(lista).filter(lambda x: x > 5)), 2))).map(lambda x: x[0] * x[1]).reduce(
        lambda x, y: x + y))

