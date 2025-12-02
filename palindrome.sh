#!/bin/bash

echo "Enter a string:"
read str

# Reverse the string
revStr=$(echo "$str" | rev)

# Compare original and reversed
if [ "$str" = "$revStr" ]; then
    echo "The string is a palindrome."
else
    echo "The string is NOT a palindrome."
fi
