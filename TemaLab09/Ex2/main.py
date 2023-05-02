class Observable:

    def __init__(self,observers):
        self.observers = []

    def attach(self,object):
        self.observers.append(object)

    def detach(self,object):
        self.observers.remove(object)

    def notifyAll(self):
        for observer in self.observers:
            observer.update()

class SelectProdusSTM(Observable):
    def __init__(self,observers):
        super().__init__(observers)
        self.select_product_state = SelectProduct(self)
        self.coca_cola_state =  CocaCola(self,5)
        self.pepsi_state = Pepsi(self,4)
        self.sprite_state = Sprite(self,5)
        self.current_state = self.select_product_state

    def choose_another_product(self):
        self.current_state = self.select_product_state
class State:
    pass
class SelectProduct(State):
    def __init__(self,state_machine: SelectProdusSTM):
        self.price = 0
        self.state_machine = state_machine

    def choose(self):
        print("1-CocaCola: 5 lei")
        print("2-Pepsi: 4 lei")
        print("3-Sprite: 5 lei")
        aleg = int(input("Alege o optiune: "))
        self.state_machine.notifyAll()
        if aleg == 1:
            self.state_machine.current_state = self.state_machine.coca_cola_state
        if aleg == 2:
            self.state_machine.current_state = self.state_machine.pepsi_state
        if aleg == 3:
            self.state_machine.current_state = self.state_machine.sprite_state

class CocaCola(State):
    def __init__(self, state_machine : SelectProdusSTM, price: int):
        self.state_machine = state_machine
        self.price = price

class Pepsi(State):
    def __init__(self, state_machine : SelectProdusSTM, price: int):
        self.state_machine = state_machine
        self.price = price
class Sprite(State):
    def __init__(self, state_machine : SelectProdusSTM, price: int):
        self.state_machine = state_machine
        self.price = price

class ChoiceObserver:
    def update(self):
        print("Actualizat")


class TakeMoneySTM:
    def __init__(self):
        self.wait_state = WaitingForClient(self)
        self.insert_money_state = InsertMoney(self)
        self.current_state = self.wait_state
        self.money = 0

    def add_money(self, valoare: int):
        self.money = valoare

    def update_amount_of_money(self, valoare: float):
        self.money = self.money + valoare

class WaitingForClient:
    def __init__(self,state_machine: TakeMoneySTM):
        self.state_machine= state_machine

    def client_arrived(self):
        print("0 - STOP")
        print("1 - 10 bani")
        print("2 - 50 bani")
        print("3 - 1 leu")
        print("4 - 5 lei")
        print("5 - 10 lei")
        print("Sold Total:" + str(self.state_machine.money))
        choice = int(input("Alege o optiune:"))
        if choice == 1:
            self.state_machine.current_state= self.state_machine.insert_money_state
            self.state_machine.insert_money_state.insert_10bani
        if choice == 2:
            self.state_machine.current_state= self.state_machine.insert_money_state
            self.state_machine.insert_money_state.insert_50bani
        if choice == 3:
            self.state_machine.current_state= self.state_machine.insert_money_state
            self.state_machine.insert_money_state.insert_1leu
        if choice == 4:
            self.state_machine.current_state= self.state_machine.insert_money_state
            self.state_machine.insert_money_state.insert_5lei
        if choice == 5:
            self.state_machine.current_state= self.state_machine.insert_money_state
            self.state_machine.insert_money_state.insert_10lei

class InsertMoney(State):
    def __init__(self, state_machine: TakeMoneySTM):
        self.state_machine = state_machine

    def insert_10bani(self):
        self.state_machine.update_amount_of_money(0.1)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()

    def insert_50bani(self):
        self.state_machine.update_amount_of_money(0.5)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()

    def insert_1leu(self):
        self.state_machine.update_amount_of_money(1)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()

    def insert_5lei(self):
        self.state_machine.update_amount_of_money(5)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()

    def insert_10lei(self):
        self.state_machine.update_amount_of_money(10)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()

    def insert_10bani(self):
        self.state_machine.update_amount_of_money(0.1)
        self.state_machine.current_state = self.state_machine.wait_state
        self.state_machine.wait_state.client_arrived()



class VendingMachineSTM:
    def __init__(self):
        obs = ChoiceObserver()
        self.take_money_stm = TakeMoneySTM()
        self.select_product_stm = SelectProdusSTM(obs)

    def proceed_to_checkout(self):
        ok = 1
        while ok ==1:
            self.select_product_stm.choose_another_product()
            self.select_product_stm.current_state.choose()
            ok = int(input("0- afara\n 1-alege o noua optiune"))
        print("Introduceti " + str(self.select_product_stm.current_state.price) + " lei")

        ok = 1
        while ok == 1:
            self.take_money_stm.current_state.client_arrived()
            if self.take_money_stm.money >= self.select_product_stm.current_state.price:
                ok = 0
            else:
                print("Fonduri insuficiente.")

        print("Ridicati produsul.")
        print("Ridicati restul: " + str(self.take_money_stm.money - self.select_product_stm.current_state.price))





# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    vending = VendingMachineSTM()
    vending.proceed_to_checkout()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
