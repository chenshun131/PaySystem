#!/usr/bin/env bash

echo "AAAA"

echo -e '\n1>' -------------------------------------------
your_name='qinjx'
str="Hello, I know your are \"${your_name}\"! \n"
echo ${str} # Hello, I know your are "qinjx"! \n


echo -e '\n2>' -------------------------------------------
your_name="qinjx"
greeting="hello, "${your_name}" !"
greeting_1="hello, ${your_name} !"
echo ${greeting} ${greeting_1}


echo -e '\n3>' -------------------------------------------
string="abcd"
echo ${#string} #输出 4


echo -e '\n4>' -------------------------------------------
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo


echo -e '\n5>' -------------------------------------------
a="aaabbb"
b="bbb"
subIndex=`awk 'BEGIN{print match("'${a}'","'${b}'")}'`
echo ${subIndex}


echo -e '\n6>' -------------------------------------------
array_name=(value0 value1 value2
value3)
array_name[0]='a'
array_name[1]='b'
array_name[2]='c'
array_name[3]='d'
echo ${array_name[1]} # 输出 b
# 获取数组的长度
# 取得数组元素的个数
length=${#array_name[@]}
echo "length=${length}" # 输出 length=4
# 或
length=${#array_name[*]}
echo "length=${length}" # 输出 length=4
# 取得数组单个元素的长度
lengthn=${#array_name[n]}
echo "length=${length}" # 输出 length=4


echo -e '\n7>' -------------------------------------------


