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
echo "数组的元素为: ${array_name[*]}"
echo "数组的元素为: ${array_name[@]}"
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
echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第1个参数为：$1";
echo "第2个参数为：$2";
echo "第3个参数为：$3";
echo "第4个参数为：$4";
echo "第5个参数为：$5";
echo "第6个参数为：$6";
echo "第7个参数为：$7";
echo "第8个参数为：$8";
echo "第9个参数为：$9";
echo "第10个参数为：$10";
echo "第11个参数为：$11";

echo "-- \$* 演示 ---"
for i in "$*"; do
    echo ${i}
done

echo "-- \$@ 演示 ---"
for i in "$@"; do
    echo ${i}
done


echo -e '\n8>' -------------------------------------------
my_arry=(a b "c","d" abc)
echo "-------FOR循环遍历输出数组--------"
for i in ${my_arry[@]}
do
  echo ${i}
done

echo "-------::::WHILE循环输出 使用 let i++ 自增:::::---------"
j=0
while [ ${j} -lt ${#my_arry[@]} ]
do
  echo ${my_arry[${j}]}
  let j++
done

echo "--------:::WHILE循环输出 使用 let  "n++ "自增: 多了双引号，其实不用也可以:::---------"
n=0
while [ ${n} -lt ${#my_arry[@]} ]
do
  echo ${my_arry[${n}]}
  let "n++"
done

echo "---------::::WHILE循环输出 使用 let m+=1 自增,这种写法其他编程中也常用::::----------"
m=0
while [ ${m} -lt ${#my_arry[@]} ]
do
  echo ${my_arry[${m}]}
  let m+=1
done

echo "-------::WHILE循环输出 使用 a=$[$a+1] 自增,个人觉得这种写法比较麻烦::::----------"
a=0
while [ ${a} -lt ${#my_arry[@]} ]
do
 echo ${my_arry[${a}]}
 a=$[$a+1]
done


echo -e '\n9>' -------------------------------------------
val=`expr 2 + 2`
echo "两数之和为 : $val" # 输出 : 两数之和为 : 4


echo -e '\n10>' -------------------------------------------
a=10
b=20

val=`expr ${a} + ${b}`
echo "a + b : $val"

val=`expr ${a} - ${b}`
echo "a - b : $val"

val=`expr ${a} \* ${b}`
echo "a * b : $val"

val=`expr ${b} / ${a}`
echo "b / a : $val"

val=`expr ${b} % ${a}`
echo "b % a : $val"

if [ ${a} == ${b} ]
then
   echo "a 等于 b"
fi
if [ ${a} != ${b} ]
then
   echo "a 不等于 b"
fi