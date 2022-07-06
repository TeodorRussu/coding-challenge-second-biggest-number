**The task:** 
"Please create a new Java or Kotlin project using Maven or Gradle. Then design and implement a function which
accepts any structure able to iterate over integers and returns the 2nd biggest element on that structure. Note that
there might be millions of elements".

**More clarifications:**

_Question_: What is the expected behavior if the input has the maximum element duplicated:
for example, for the input: '{1, 2, 3, 9, 9}', do we expect the output 9 or 3?

_Answer_: 9

_Question_: "a function which accepts any structure able to iterate over integers":
does that mean that the function must support as input all types of data structures that implement the Iterable interface (Collection, List, Set, Queue, etc), or can I choose and focus on one Collection type that can iterate over its values, e.g. List?

_Answer_: you can choose a structure which makes sense to you.


**Solution**:

The input method for solving the problem is SecondBiggestNumberFinder.findSecondBiggestNumber(List<Integer> input)

The method accepts as input a list of integers.

If the list is smaller than 1000 elements, the program searches for the two largest values, traversing the array only once.

If the list is larger, it is virtually divided into subviews and the views are handled in parallel by multiple threads(workers). Each worker finds the 2 largest values in the sublist.

When all jobs are finished the results are joined, and then from the final subset the program finds the 2 highest values and returns the second highest.

The parallelism is achieved by using the @Async anonymity in combination with the CompletableFuture data type, returned by the asynchronous executed method.


**Note**: There are some examples of running the program in test-cases, also in the Application.run() method, where a comparison between linear vs parallel speed is also made. Surprisingly the speed difference between the two approaches is relatively close because we are just looking for numbers. If for each entry in the list some operation would be performed that would bring some latency, the performance of the parallel solution is significant compared to the linear one.

