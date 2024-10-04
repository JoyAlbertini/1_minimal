**Joy Albertini**

Java Implementation of one minimal algorithm for delta debugging.

### OneMinimal algorithm

The ```oneMinimal``` algorithm is designed to find the minimal input that reproduces a failure (bug) by systematically reducing the size of the input while ensuring that the failure still occurs.

### Examples HtmlPage
```ExampleWebPage```

For example, in a web page with multiple <\SELECT> elements and options, the delta debugging process would simplify the input to find the smallest subset of HTML that still causes a segmentation fault or browser crash.
Real-world Scenario

### Examples of a program
```Example42```

 a program crashes when its input contains 42. By applying delta debugging with character granularity, you progressively minimize the input until finding that "42" is the minimal input that causes the crash.



