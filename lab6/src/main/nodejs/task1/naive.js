const Fork = function () {
    this.state = 0;
    return this;
};

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function binary_exponential_backoff(awaited, delay) {
    await sleep(delay);
    if (awaited() === false) {
        await binary_exponential_backoff(awaited, Math.floor(Math.random() * 2 * delay));
    }
}

Fork.prototype.acquire = async function() {
    await binary_exponential_backoff(() => !this.state, 1);
    this.state = 1;
};

Fork.prototype.release = function() {
    this.state = 0;
};

const Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    return this;
};

Philosopher.prototype.startNaive = async function (count) {
    const forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    const startTime = new Date().getTime();

    while (count--) {
        // console.log(`${id} is thinking`)
        await forks[f1].acquire();
        await forks[f2].acquire();
        // console.log(`${id} is eating`);
        forks[f1].release();
        forks[f2].release();
    }
    console.log(`${id}: ${new Date().getTime() - startTime} ms`);
};

const N = 5;
const forks = [];
const philosophers = [];
let i;
for (i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (i = 0; i < N; i++) {
    philosophers[i].startNaive(10);
}