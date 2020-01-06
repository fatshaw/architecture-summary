
## Thread pool
下列三种业务，应该如何使用线程池
- 高并发、任务执行时间短的业务
- 并发不高、任务执行时间长的业务
- 并发高、业务执行时间长的业务

## Thread Pool 问题
1. 队列大小没有限定
2. 线程数量没有限定
3. 高并发情况下，队列加锁冲突增加，性能下降

## 解决
1. 使用java8 newWorkStealingPools
2. 使用disruptor 队列
2. 使用coroutines

## Reference
http://baddotrobot.com/blog/2013/06/01/optimum-number-of-threads/
https://martinfowler.com/articles/lmax.html