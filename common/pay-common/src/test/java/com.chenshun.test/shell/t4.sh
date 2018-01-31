#!/usr/bin/env bash
a=10
b=20

if [[ ${a} -lt 100 && ${b} -gt 100 ]]
then
   echo "${a} 小于 100 && ${b} 大于 100 返回 true"
else
   echo "${a} 小于 100 && ${b} 大于 100 返回 false"
fi

if [[ ${a} -lt 100 || ${b} -gt 100 ]]
then
   echo "${a} 小于 100 || ${b} 大于 100 返回 true"
else
   echo "${a} 小于 100 || ${b} 大于 100 返回 false"
fi
