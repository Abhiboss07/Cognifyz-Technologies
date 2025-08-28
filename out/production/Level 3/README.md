## Concurrent Bank (Task 2)

A small Java program demonstrating race conditions and thread-safety strategies using a simple bank account.

### What it shows
- Unsynchronized access causes data races and incorrect balances
- `synchronized` methods ensure mutual exclusion
- `ReentrantLock` provides explicit locking with try/finally patterns

### Files
- `ConcurrentBank.java` — runs three simulations against different `BankAccount` implementations:
  - `UnsynchronizedBankAccount`
  - `SynchronizedBankAccount`
  - `LockedBankAccount` (uses `ReentrantLock`)

Each simulation submits 100 tasks to a fixed thread pool of 10 workers. Every task performs:
- `deposit(100)` then `withdraw(50)`

Expected net change per task = 50; across 100 tasks → expected final balance = 5000.

### Requirements
- Java 8+ (JDK) on your PATH

### Compile
From this directory:
```bash
javac ConcurrentBank.java
```

### Run
```bash
java ConcurrentBank
```

You will see three sections run in order:
1) Unsynchronized
2) Synchronized
3) ReentrantLock

Each section prints:
- `Final Balance: <number>`
- `Expected Balance: 5000`

### Interpreting results
- Unsynchronized: Final balance will often be incorrect (less than 5000) due to race conditions
- Synchronized: Final balance should be 5000
- ReentrantLock: Final balance should be 5000

### Notes
- The short `Thread.sleep(5)` calls amplify timing windows to make races more likely in the unsynchronized case.
- `ExecutorService` is used to run tasks concurrently; the program awaits termination before reporting the final balance.


