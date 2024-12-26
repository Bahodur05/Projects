#!/usr/bin/env python
# coding: utf-8

# In[4]:


request = input("Please input the data as a raw string:")
request_list_split = request.split(",")  # This method splits a string into substrings based on a delimiter and returns a list of these substrings.
float_list = []  # Creating a list that will contain numbers converted to float after for loop
request_list = [stripping.strip() for stripping in request_list_split]  # List comprehension for cutting empty spaces from start and end of each element in the list

for test_float in request_list:
    check1, check2, check3 = True, True, True
    is_float = True
    check1 = '.' in test_float and test_float.count('.') == 1 and (
        test_float.index('.') < 0 or (test_float.index('.') == 0 and test_float[1:].isdigit())
    )
    if test_float.count('-') != 1 or (test_float.index('-') != 0 or test_float.count('.') > 1):
        check2 = False
    for character in test_float:
        if not (character.isdigit() or (character == '.' and test_float.count('.') <= 1)):
            check3 = False
            break
    if check1 == check2 == check3 == False:
        is_float = False
    if is_float:
        float_number = float(test_float)  # Stores each digit in variable float_number
        float_list.append(float_number)  # Joins each of the digit that has gone through checks in float_list variable that was initially created
    else:
        print(f"Error for: {test_float}, input only numbers")

print(f"Converted float list: {float_list}")

# 2. Calculating mean using the formula given in the instructions
counts = 0
for numbers in float_list:
    counts += 1
mean = sum(float_list) / counts
print(f"The mean is: {mean}")

# 3. Calculating the mode
frequency_list = [[value, 0] for value in set(float_list)]  # Create a list to store frequencies as lists [value, frequency]
for value in float_list:  # Counting the frequency of each value in float_list
    for sublist in frequency_list:
        if sublist[0] == value:
            sublist[1] += 1
            break
max_frequency = max(sublist[1] for sublist in frequency_list)  # Finding the maximum frequency

# Finding the modes (values with maximum frequency)
modes = [sublist[0] for sublist in frequency_list if sublist[1] == max_frequency]  # Finding the modes (values with maximum frequency)

# Printing the mode(s)
if len(modes) == 1:
    print("Mode:", modes[0])
else:
    print("Modes:", modes)

# 4. Calculating Standard Deviation using the formula given in the instructions
squared_difference = [(digits - mean) ** 2 for digits in float_list]
standard_deviation = (sum(squared_difference) / len(float_list)) ** 0.5
print("Standard deviation:", standard_deviation)

# 5. Bubble Sorting (Based on the instructions)
for floats in range(len(float_list) - 1):  # Outer loop iterating over the indices of float_list except the last one
    for floats_floats in range(len(float_list) - 1):  # Inner loop iterating over the indices of float_list except the last one
        if float_list[floats_floats] > float_list[floats_floats + 1]:  # Comparing adjacent elements in the list
            float_list[floats_floats], float_list[floats_floats + 1] = (
                float_list[floats_floats + 1],
                float_list[floats_floats],
            )  # Swapping elements if they are in the wrong order

# Printing the sorted list
print(f"Bubble Sorted list: {float_list}")


# In[ ]:




