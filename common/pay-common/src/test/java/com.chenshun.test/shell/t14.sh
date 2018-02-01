#!/bin/bash
variable="这是输出内容"
echo ${variable} > /Users/mew/Desktop/a.txt

append="这是追加内容"
echo ${append} >> /Users/mew/Desktop/a.txt

echo -e '\n1>' -------------------------------------------
cat < /Users/mew/Desktop/a.txt

echo -e '\n2>' -------------------------------------------
more < /Users/mew/Desktop/a.txt

echo -e '\n3>' -------------------------------------------
tail < /Users/mew/Desktop/a.txt

echo -e '\n3>' -------------------------------------------

