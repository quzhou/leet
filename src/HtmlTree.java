/**
 写一个handle html Tag class，可以给 tag加class name, 然后实现function, given one class name, find all tags,
 然后再实现一个function，给一个class name，和另一个class name，找这两class是不是nested
 */
/*
1. 白人小哥，人很nice，先是让我看了下一个HTML代码，然后问我如何在程序里表示HTML源代码的结构，实际上就是让我写了一个
HtmlTag Tree，就是写一个class表示HTML Tag，每个tag都有个name，比如html head a 之类的，还有一些attribute，
tag下面还会有些sub tag，就是一个多叉树结构。面了1个小时，小哥不断的提出各种feature要求去完善这个class，
要求很多，现在没法回忆出来了，不过都不是很难，最后的目标是给一个你写的Tree的root节点，在terminal
print出他给的HTML代码。当时写得还比较顺，除了print时候出了一个bug找了半天，其他feature lz基本都是很快写出来，
然后还剩一点时间跟小哥愉快的聊了一会。
解法：这个就是纯拼coding基本功的，因为每个tag的sub tag的数量不一样，所以要用ArrayList或者vector去存sub tag，
print的话用DFS就行。然后很重要的一点是coding style一定要好，小哥明确说了，而且有些地方变量名还跟我argue了一下。
 */
public class HtmlTree {
}
