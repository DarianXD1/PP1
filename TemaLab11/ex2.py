import subprocess


if __name__ == '__main__':
    command = input('Dati comanda: ')
    process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    output = process.communicate()[0]
    print((str(output)[2:-3]))

