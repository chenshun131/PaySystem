#!/usr/bin/env bash

echo -e '\n1>' -------------------------------------------
num1=100
num2=100
if test $[num1] -eq $[num2]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi

echo -e '\n2>' -------------------------------------------
a=5
b=6
result=$[a+b] # 注意等号两边不能有空格
echo "result 为： ${result}"

echo -e '\n3>' -------------------------------------------
num1="ru1noob"
num2="runoob"
if test $num1 = $num2
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi

echo -e '\n4>' -------------------------------------------
cd /bin
if test -e ./bash
then
    echo '文件已存在!'
else
    echo '文件不存在!'
fi

echo -e '\n5>' -------------------------------------------
cd /bin
if test -e ./notFile -o -e ./bash
then
    echo '至少有一个文件存在!'
else
    echo '两个文件都不存在'
fi

echo -e '\n6>' -------------------------------------------
for loop in 1 2 3 4 5
do
    echo "The value is: ${loop}"
done

echo -e '\n7>' -------------------------------------------
int=1
while(( $int<=5 ))
do
    echo ${int}
    let "int++"
done

echo -e '\n8>' -------------------------------------------
echo '按下 <CTRL-D> 退出'
echo -n '输入你最喜欢的网站名: '
while read FILM
do
    echo "是的！${FILM} 是一个好网站"
done
