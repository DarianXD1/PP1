import asyncio
import queue


async def sum(name, que):
    sum = 0;
    num = que.get()
    print(f"Task {name}: Calculeaza suma pentru {num}")
    for i in range(1, num + 1):
        await asyncio.sleep(1)
        sum += i
    print(f"Task {name}: result = {sum}")


async def main():
    q = queue.Queue()
    q.put(10)
    q.put(8)
    q.put(12)
    q.put(4)
    await asyncio.gather(
        sum("1", q),
        sum("2", q),
        sum("3", q),
        sum("4", q)
    )


if __name__ == '__main__':
    asyncio.run(main())


