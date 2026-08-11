> 承接《线程池练习-施工图纸》Step 1-4（手动 ThreadPoolExecutor 构造、注册为 Spring 单例 Bean、CountDownLatch 计数动态化、单线程 vs 线程池性能对比）已完成的基础上，进行本步骤。

# Step 5：异常处理 + `CompletableFuture` 改写

## 5.1 先做一个实验，亲眼看到问题

在动手改代码之前，**先制造一次故障**，亲眼观察现象，比直接讲道理更有说服力。

自己在 `threadPool()` 方法里，找一个分片任务的 lambda 内部，**故意** `throw` 一个运行时异常（比如故意写一行 `int x = 1/0` 触发除零异常，或者手动 `throw new RuntimeException("测试异常")`），塞在 `filter` 或 `forEach` 逻辑附近。

跑一遍，观察几件事：

1. 控制台有没有打印出这个异常的堆栈信息？
有
2. `threadPool()` 方法本身有没有因为这个异常而中断、抛出到调用方？
有中断，但是不理解抛出调用方是什么意思
3. `countDownLatch.await()` 有没有卡住不动（一直等，程序像是"卡死"了一样）？
直接程序都没有run起来

**先自己观察现象，想一下为什么会这样，再往下看原理。**

## 5.2 原理：`submit()` 吞掉异常 vs `execute()` 暴露异常

自己去查一下（或者回忆之前提过一句）`ThreadPoolExecutor.submit()` 和 `.execute()` 这两个方法签名上的区别：

- `execute(Runnable)` 没有返回值。任务里如果抛异常，**这个异常会顺着线程往上抛，最终由线程的"未捕获异常处理器"接住**，默认行为是打印堆栈到控制台——你能在日志里看到它。
- `submit(Runnable/Callable)` 会返回一个 `Future<T>`。任务里如果抛异常，**这个异常不会立刻被打印或抛出，而是被"存"进了这个 `Future` 对象里**——只有当你**主动调用 `future.get()`** 的时候，这个异常才会被重新包装成 `ExecutionException` 抛给你。**如果压根不接收 `submit()` 的返回值（现在代码里写的是 `threadPoolExecutor.submit(() -> {...})`，返回值直接被扔了），这个异常就永远没有机会被任何人看到——它被"吞"了。**

**这也解释了实验里会观察到的现象**：某个分片任务在 `filter`/`forEach` 执行到一半时抛了异常，**这一行之后的代码（包括 `countDownLatch.countDown()`）不会被执行到**——因为异常发生后，这个 lambda 剩下的代码直接中断跳出，不会"跳过异常继续往下走"。这就导致 `countDownLatch` 少减了一次，永远到不了 0，`await()` 会**永久卡住**——这是个比"任务失败"更严重的问题：**一个任务的异常，拖垮了整个方法**。

## 5.3 动手修复：让异常不再被吞（CountDownLatch 版本）

自己实现，思路提示：

1. 把 `countDownLatch.countDown()` 这一行，用 **`try...finally`** 包裹整个任务逻辑——想一下为什么要用 `finally` 而不是放在 `try` 的最后一行：`finally` 块**不管 try 里有没有抛异常，都保证会执行**，这样即使这个分片任务处理过程中出错了，`countDown()` 依然会被执行，不会拖累其他分片、不会让 `await()` 永久卡死。
2. 在 `try` 块里，或者单独用一个 `catch (Exception e)` 分支，把异常**记录下来**（用日志打印，而不是让它默默消失）——这样至少知道"哪个分片、什么原因失败了"，而不是数据莫名其妙少了一部分却毫无察觉。

### 自查标准

改完之后，回到刚才那个"故意抛异常"的实验，重新跑一遍。这次应该观察到：
- 那一个分片确实失败了（日志里有记录）
- 但其他分片正常完成
- `countDownLatch.await()` 正常放行，方法能跑完，不再卡死

这一步做完，`submit()` "静默吞异常" 这个坑就被亲手堵上了。

---

## 5.4 用 `CompletableFuture` 重写一遍，做个对比

`CountDownLatch` 这套写法能用，但应该已经感觉到了——**手动维护一个计数器、手动 `try/finally` 兜底、还要单独处理异常**，代码写起来比较啰嗦。`CompletableFuture` 是 Java 8 之后提供的一套更现代的"异步任务编排"API，同样的效果能写得更简洁。

自己去查一下这几个方法，想清楚它们的用途，再动手写：

- **`CompletableFuture.runAsync(Runnable, Executor)`**：提交一个任务到指定线程池异步执行，返回一个 `CompletableFuture<Void>`——注意这里**第二个参数要传自己的 `threadPoolExecutor`**，不要漏掉（不传的话，`CompletableFuture` 会用一个全局默认的公共线程池，那就不是"自己在管理线程池"了）
- 每个分片对应一个 `CompletableFuture`，会得到一个 `List<CompletableFuture<Void>>`
- **`CompletableFuture.allOf(...)`**：接收多个 `CompletableFuture`，返回一个新的 `CompletableFuture<Void>`，**它会在所有传入的任务都完成后才算完成**——这就是替代 `CountDownLatch.await()` 的手段。想一下这个方法的参数类型是什么（是 `List` 还是数组，如果是数组，`List` 怎么转成数组）
- **异常处理**：`CompletableFuture` 有个专门的方法叫 **`exceptionally(...)`**（或者 `handle(...)`），可以给某个任务链挂一个"如果出错了该怎么办"的回调，不需要手写 `try/catch`——自己查一下这个方法怎么用，试着接住任务里的异常并打印日志，效果对齐上面 `CountDownLatch` 版本做的事

写完之后，**自己对比一下两版代码**：哪一版可读性更好？哪一版在"等待所有任务完成"这件事上写起来更省心？

### 提交前自查

1. 每个分片的任务提交时，有没有把 `threadPoolExecutor` 作为参数传进去（而不是用默认线程池）？
2. 所有分片都提交完之后，有没有用 `allOf(...)` 等待它们全部完成，再继续往下走（而不是提交完立刻往下执行，任务还没跑完就误以为结束了）？
3. 故意在某个分片里制造一次异常，`CompletableFuture` 版本能不能像 `CountDownLatch` 版本一样：这个分片失败被记录下来，但不影响其他分片、也不会让整个方法卡死？

这次不给完整自查清单当"标准答案"了，按自己的节奏写完贴过来，从写的过程里能看出理解到了什么程度，再决定要不要继续深挖（比如 `CompletableFuture` 的链式编排 `thenApply`/`thenCombine` 这类进阶用法）。