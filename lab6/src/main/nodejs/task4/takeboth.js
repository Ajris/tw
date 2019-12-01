// Teoria Współbieżnośi, implementacja problemu 5 filozofów w node.js
// Opis problemu: http://en.wikipedia.org/wiki/Dining_philosophers_problem
//   https://pl.wikipedia.org/wiki/Problem_ucztuj%C4%85cych_filozof%C3%B3w
// 1. Dokończ implementację funkcji podnoszenia widelca (Fork.acquire).
// 2. Zaimplementuj "naiwny" algorytm (każdy filozof podnosi najpierw lewy, potem
//    prawy widelec, itd.).
// 3. Zaimplementuj rozwiązanie asymetryczne: filozofowie z nieparzystym numerem
//    najpierw podnoszą widelec lewy, z parzystym -- prawy.
// 4. Zaimplementuj rozwiązanie z kelnerem (według polskiej wersji strony)
// 5. Zaimplementuj rozwiążanie z jednoczesnym podnoszeniem widelców:
//    filozof albo podnosi jednocześnie oba widelce, albo żadnego.
// 6. Uruchom eksperymenty dla różnej liczby filozofów i dla każdego wariantu
//    implementacji zmierz średni czas oczekiwania każdego filozofa na dostęp
//    do widelców. Wyniki przedstaw na wykresach.
var async = require("async");

var Fork = function() {
    this.state = 0;
    return this;
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function binary_exponential_backoff(awaited, delay) {
    await sleep(delay);
    if (awaited() == false) {
        await binary_exponential_backoff(awaited, Math.floor(Math.random() * 2 * delay));
    }
}

Fork.prototype.acquire = async function() {
    await binary_exponential_backoff(() => !this.state, 1);
    this.state = 1;

}

Fork.prototype.release = function() {
    this.state = 0;
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

Philosopher.prototype.acquireBoth = async function() {
    var f1 = this.forks[this.f1],
        f2 = this.forks[this.f2];
    await binary_exponential_backoff(() => f1.state == 0 && f2.state == 0, 1);
    f1.state = f2.state = 1;
}

Philosopher.prototype.startPickingBoth = async function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id

    var startTime = new Date().getTime();

    while (count--) {
        // console.log(`${id} is thinking`)
        await this.acquireBoth();
        // console.log(`${id} is eating`);
        forks[f1].release();
        forks[f2].release();
    }
    console.log(`${id}: ${new Date().getTime() - startTime} ms`);
}

var N = 5;
var forks = [];
var philosophers = []
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startPickingBoth(10);
}