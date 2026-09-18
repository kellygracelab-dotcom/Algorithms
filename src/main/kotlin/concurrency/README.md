# Coroutines and races

Five topics, one approach each. Order matters: each one builds on the previous.

Ground rule: **read the question and answer it out loud first, then run the test and look at the code.**
The test is a check, not an explanation.

Run the whole set:

```bash
./gradlew test --tests "concurrency.*"
```

---

## 1. A race over shared state — `RaceCounter.kt`

**Question.** Eight coroutines each increment a shared counter twenty thousand times. Why isn't
the final result a hundred and sixty thousand, if no coroutine ever lost an increment?

The test prints the real number — see how far off it lands.

**What to get.** `value = value + 1` isn't one operation, it's three: read, add, write.
Another thread can slip in between the read and the write, and its own addition gets
overwritten.

**Three ways to fix it, and when to use which:**

- **an atomic type** — when the state is simple, a single number or reference;
- **Mutex** — when several related fields need to be protected together;
- **confine it to one thread** — when the state shouldn't be shared at all.

**On an interview they'll ask:** how `Mutex` differs from `synchronized`. Short version: `Mutex`
suspends the coroutine, `synchronized` blocks the thread. The second one, inside a coroutine,
defeats the whole point.

---

## 2. Structured concurrency — `StructuredConcurrency.kt`

**Question.** What happens to sibling coroutines if one of them crashes?

**What to get.** A scope isn't just "where coroutines live" — it's a **guarantee**: it won't
complete until all its children have. Two consequences follow — the parent waits for its
children, and one child failing cancels the rest.

`supervisorScope` changes the second rule: children become independent, one failing doesn't
touch its siblings. But someone still has to handle the exception, or it flies upward —
the test shows this through the handler.

**Separately, about `async`:** two `async` calls in a row run the work **concurrently**, while
two direct calls run sequentially. The test prints both durations, and the difference is visible.

---

## 3. Cancellation — `Cancellation.kt`

The most common interview topic, and the most common bug in real code.

**Question.** What's wrong with `try { ... } catch (e: Exception) { log(e) }` inside a coroutine?

**What to get.** Cancellation in coroutines is an exception. Catch "all exceptions" and you
catch cancellation too — the coroutine keeps running after it was asked to die. Cancellation
has to be rethrown.

**Second.** Cancellation only works at suspension points. A loop that just counts and never
suspends has nothing to cancel it at — it'll run to completion. Fixed by checking
`ensureActive()` inside the loop. In the tests these are two neighboring functions, one line
apart.

**Third.** Cleanup code in `finally` won't run after cancellation if it suspends itself — the
coroutine is already cancelled. That's what `NonCancellable` is for.

---

## 4. Dispatchers and blocking — `DispatchersAndBlocking.kt`

**Question.** Does `suspend` mean "will run in the background"?

**What to get.** No. `suspend` means "this function knows how to suspend". Which thread it
actually runs on is decided by the dispatcher, not the keyword.

**The key difference:** suspension frees up the thread, blocking occupies it. The test shows
this in numbers: a hundred suspending tasks finish in about the time of one, a hundred
blocking tasks are capped by the number of threads.

---

## 5. Cold and hot flows — `ColdAndHot.kt`

**Question.** Two subscribers on the same `Flow` — do they get the same data, or each their own?

**What to get.** A cold `Flow` is a recipe: it starts over for every subscriber. The test
counts this — the start counter equals the number of collections.

A hot one (`StateFlow`, `SharedFlow`) exists independently of its subscribers. `StateFlow`
always has a current value, and it **doesn't keep history**: three quick changes in a row leave
a subscriber with only the last one.

**On an interview they'll ask:** why `StateFlow` for screen state and `SharedFlow` for events.
Because state has a "current value" and survives a screen rotation, while an event is shown
once and shouldn't repeat on recreation.
