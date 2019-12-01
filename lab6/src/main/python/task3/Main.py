import asyncio
import random


async def think(phi_id: int):
    print(f'task1.Philosopher {phi_id} is thinking')
    await asyncio.sleep(random.randint(0, 3))


async def eat(phi_id: int):
    print(f'task1.Philosopher {phi_id} is eating')
    await asyncio.sleep(random.randint(0, 3))


async def dining_philosopher(phi_id: int, left: asyncio.Lock, right: asyncio.Lock):
    while True:
        await think(phi_id)
        async with left, right:
            await eat(phi_id)


async def dine(n: int):
    forks = [asyncio.Lock() for _ in range(n)]
    philosophers = [dining_philosopher(i, forks[i-1], forks[i]) for i in range(n)]
    await asyncio.gather(*philosophers)


if __name__ == '__main__':
    asyncio.run(dine(5))