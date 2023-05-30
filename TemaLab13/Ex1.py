# importa modulul 'seq' din pachetul 'functional' si modulul 'datetime'
from functional import seq
import datetime

# definirea clasei Person cu patru atribute: firstName, lastName, dateOfBirth, emailAddress
class Person:
    def __init__(self, firstName, lastName, dateOfBirth, emailAddress):
        self.firstName = firstName
        self.lastName = lastName
        self.dateOfBirth = dateOfBirth
        self.emailAddress = emailAddress

    # definirea metodei speciale __repr__ care returnează reprezentarea string a obiectului Person
    def __repr__(self):
        return self.firstName + " " + self.lastName + " " + str(self.dateOfBirth)

if __name__ == '__main__':
    # crearea unei liste de obiecte Person
    persons = [Person("John", "Doe", datetime.datetime(1960, 11, 3), "jdoe@example.com"),
               Person("Ellen", "Smith", datetime.datetime(1992, 5, 13), "ellensmith@example.com"),
               Person("Jane", "White", datetime.datetime(1986, 2, 1), "janewhite@example.com"),
               Person("Bill", "Jackson", datetime.datetime(1999, 11, 6), "bjackson@example.com"),
               Person("John", "Smith", datetime.datetime(1975, 7, 14), "johnsmith@example.com"),
               Person("Jack", "Williams", datetime.datetime(2010, 5, 28), "")]

    # sortare persoane după vârstă și afișarea celui mai tânăr și a celui mai în vârstă
    sorted_persons = seq(persons).sorted(lambda x: x.dateOfBirth)
    print(sorted_persons[-1]) # cel mai tanar
    print(sorted_persons[0]) # cel mai in varsta

    # afișarea persoanelor cu vârsta sub 18 ani
    print(seq(persons).filter(lambda x: datetime.date.today().year - x.dateOfBirth.year < 18))

    # afișarea listei de adrese de email
    print(seq(persons).map(lambda x: x.emailAddress))

    # crearea unui dictionar cu numele complet al persoanei si adresa de email
    map_name_email = dict(seq(persons).map(lambda x: (x.firstName + " " + x.lastName, x.emailAddress)))
    print(map_name_email)

    # crearea unui dictionar cu adresa de email si persoana
    map_email_person = dict(seq(persons).map(lambda x: (x.emailAddress, x)))
    print(map_email_person)

    # gruparea persoanelor după luna nașterii
    print(seq(persons).group_by(lambda x: x.dateOfBirth.month))

    # partiționarea listei în două: persoane născute înainte de 1980 și persoane născute după 1980
    print(seq(persons).partition(lambda x: x.dateOfBirth.year < 1980))

    # afișarea numelor distincte
    print(seq(persons).map(lambda x: x.firstName).distinct())

    # calcularea vârstei medii
    print(seq(persons).average(lambda x: datetime.date.today().year - x.dateOfBirth.year))

    # numărarea persoanelor cu numele de familie 'Smith'
    print(seq(persons).count(lambda x: x.lastName == "Smith"))

    # căutarea unei persoane cu numele 'John', dacă există
    print(next(iter(seq(persons).filter(lambda x: x.firstName == "John")), None))

    # căutarea unei persoane cu numele 'Thomas', dacă există, altfel afișează un mesaj
    print(next(iter(seq(persons).filter(lambda x: x.firstName == "Thomas")), "No one named Thomas was found."))

    # verificarea dacă există persoane fără adresa de email
    print(seq(persons).filter(lambda x: x.emailAddress == ""))

