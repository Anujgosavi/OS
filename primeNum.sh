#!/bin/bash

echo "Enter a number:"
read n

if [ $n -le 1 ]; then
    echo "$n is NOT a prime number"
    exit 0
fi

for (( i=2; i*i<=n; i++ ))
do
    if [ $((n%i)) -eq 0 ]; then
        echo "$n is NOT a prime number"
        exit 0
    fi
done

echo "$n IS a prime number"
