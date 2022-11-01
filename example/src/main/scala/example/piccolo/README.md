Piccolo is a data-centric programming model for parallel in-memory computation. It offers a distributed, shared-memory abstraction via key-value table interfaces. In this example, we show how to implement Piccolo easily in our framework through a mini example (PageRank). 
(see https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Power.pdf)

Programs in Piccolo consist of *control functions* and *kernel functions*. Control functions create shared tables and launch multiple instances of kernel. Both the control and kernel functions are single-threaded. Operations to the shared mutable table are atomic. Users define update method for resolving write-write conflict.

We can express table partitions and kernel functions as agents. A kernel has multiple instances that share distributed state using in-memory tables. We can express each kernel as an agent that is colocated with shared table partitions that  offer get and put RPC methods. The control function corresponds to the driver that initializes the kernel functions and colocates them with table partitions.
