#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import csv
import matplotlib.pyplot as plt
import numpy as np

def individual_plot(x, y, player_number):
    plt.scatter(x, y, color='black', marker='+')
    plt.xlabel("X", loc ='right', fontdict={'color': 'blue', 'size': 10})
    plt.ylabel("Y", rotation = 0, loc = 'top', fontdict={'color': 'blue', 'size': 10})
    plt.xlim(-4, 4)
    plt.ylim(-4, 4)
    plt.xticks(range(-4, 5, 1))
    plt.yticks(range(-4, 5, 1))
    ax = plt.gca()
    ax.spines[['left', 'bottom']].set_position('center') 
    ax.spines[['top', 'right']].set_visible(False)  
    ax.set_title(f"Dart Hits for Player {player_number}", fontdict={'color':"red"}, pad=20 )
    ax.tick_params(direction='inout', length=5, width=1, colors='b',
                grid_color='b', grid_alpha=0.5)
    return plt.show()

def group_plot(x1, y1, x2, y2, x3, y3):
    plt.scatter(x1, y1, color='black', marker='+')
    plt.scatter(x2, y2, color='green', marker='x')
    plt.scatter(x3, y3, color='blue')
    plt.xlim(-4, 4)
    plt.ylim(-4, 4)
    plt.xticks(range(-4, 5, 1))
    plt.yticks(range(-4, 5, 1))
    plt.xlabel("X", loc ='right', fontdict={'color': 'blue', 'size': 10})
    plt.ylabel("Y", rotation = 0, loc = 'top', fontdict={'color': 'blue', 'size': 10})
    ax = plt.gca()
    ax.spines[['left', 'bottom']].set_position('center') 
    ax.spines[['top', 'right']].set_visible(False)  
    ax.set_title("Dart Hits for All Three players", fontdict={'color':"red"}, pad=20 )
    ax.tick_params(direction='inout', length=5, width=1, colors='b',
                grid_color='b', grid_alpha=0.5)
    return plt.show()

def average_displacement(player_data):
    total_displacement_x = 0
    total_displacement_y = 0
    for element in player_data:
        total_displacement_x += element[1]  # Sum of x-coordinates
        total_displacement_y += element[2] 
    num_dots = len(player_data)
    average_displacement_x = total_displacement_x / num_dots
    average_displacement_y =  total_displacement_y / num_dots
    print(f"Average Displacement from Origin: ({average_displacement_x}, {average_displacement_y})")

try:
    with open("dart_hits.csv", newline='') as csv_file:
        csv_reader = csv.reader(csv_file)
        next(csv_reader)
        imported_data = [[float(val) for val in row] for row in csv_reader]
        player_1_data = [element for element in imported_data if element[0]== 1]
        player_2_data = [element for element in imported_data if element[0]==2]
        player_3_data = [element for element in imported_data if element[0]==3]
        
        player_1_x, player_1_y = [element[1] for element in player_1_data], [element[2] for element in player_1_data]
        player_2_x, player_2_y = [element[1] for element in player_2_data], [element[2] for element in player_2_data]
        player_3_x, player_3_y = [element[1] for element in player_3_data], [element[2] for element in player_3_data]
        
        player_1_tens = [element for element in player_1_data if ((element[1] ** 2) + (element[2]**2)) <= 1]
        player_1_fives = [element for element in player_1_data if  (element[1] ** 2 + element[2]**2) <= 4 and (element[1] ** 2 + element[2]**2) > 1]
        player_1_ones = [element for element in player_1_data if (element[1] ** 2 + element[2]**2) <= 9 and (element[1] ** 2 + element[2]**2) > 4]
        player_1_zeros = [element for element in player_1_data if (element[1] ** 2 + element[2]**2) > 9]

        player_2_tens = [element for element in player_2_data if (element[1] ** 2 + element[2]**2) <= 1]
        player_2_fives = [element for element in player_2_data if  (element[1] ** 2 + element[2]**2) <= 4 and (element[1] ** 2 + element[2]**2) > 1]
        player_2_ones = [element for element in player_2_data if (element[1] ** 2 + element[2]**2) <= 9 and (element[1] ** 2 + element[2]**2) > 4]
        player_2_zeros = [element for element in player_2_data if (element[1] ** 2 + element[2]**2) > 9]

        player_3_tens = [element for element in player_3_data if (element[1] ** 2 + element[2]**2) <= 1]
        player_3_fives = [element for element in player_3_data if  (element[1] ** 2 + element[2]**2) <= 4 and (element[1] ** 2 + element[2]**2) > 1]
        player_3_ones = [element for element in player_3_data if (element[1] ** 2 + element[2]**2) <= 9 and (element[1] ** 2 + element[2]**2) > 4]
        player_3_zeros = [element for element in player_3_data if (element[1] ** 2 + element[2]**2) > 9]
        
    while True:
        print('Choose an option: \n1. Plots\n2. Analyze Data\n3. Exit')
        user_input = input('Enter your choice: ')
        if user_input == '1':
            while True:
                print('Choose an option:\n1. Scatter plot for Player 1\n2. Scatter plot for Player 2\n3. Scatter plot for Player 3\n'
                    +'4. Scatter plot for all three players\n5. Stacked bar chart showing score distribution for each player\n6. Go back')      
                user_input_2 = input('Enter your choice: ')
                if user_input_2 == '1':
                    individual_plot(player_1_x, player_1_y, 1)               
                    exit_code = input("Click any button to continue")
                    if type(exit_code) != str :
                        break
                elif user_input_2 == '2':
                    individual_plot(player_2_x, player_2_y, 2)
                    exit_code = input("Click any button to continue")
                    if type(exit_code) != str :
                        break
                elif user_input_2 == '3':
                    individual_plot(player_3_x, player_3_y, 3)
                    exit_code = input("Click any button to continue")
                    if type(exit_code) != str :
                        break
                elif user_input_2 == '4':
                    group_plot(player_1_x, player_1_y, player_2_x, player_2_y, player_3_x, player_3_y)
                    exit_code = input("Click any button to continue")
                    if type(exit_code) != str :
                        break
                elif user_input_2 == '5':
                    players = ['Player 1', 'Player 2', 'Player 3']
                    y1 = np.array([len(player_1_tens), len(player_2_tens), len(player_3_tens)])
                    y2 = np.array([len(player_1_fives), len(player_2_fives), len(player_3_fives)])
                    y3 = np.array([len(player_1_ones), len(player_2_ones), len(player_3_ones)])
                    y4 = np.array([len(player_1_zeros), len(player_2_zeros), len(player_3_zeros)])

                    plt.bar(players, y1, color='red')
                    plt.bar(players, y2, bottom=y1, color='blue')
                    plt.bar(players, y3, bottom=y1+y2, color='green')
                    plt.bar(players, y4, bottom=y1+y2+y3, color='purple')
                    plt.ylim(0, 50)
                    plt.xlabel("Players")
                    plt.ylabel("Frequency")
                    plt.legend(["10", "5", "1", "0"], loc = 'upper right')
                    plt.title("Frequency of hits")
                    plt.show()

                    exit_code = input("Click any button to continue")
                    if type(exit_code) != str :
                        break
                if user_input_2 == '6':
                    print('Returning to the main menu...')
                    break
                
        elif user_input == '2':
            player_1_overall_score = len(player_1_tens) * 10 + len(player_1_fives) * 5 + len(player_1_ones) * 1
            player_2_overall_score = len(player_2_tens) * 10 + len(player_2_fives) * 5 + len(player_2_ones) * 1
            player_3_overall_score = len(player_3_tens) * 10 + len(player_3_fives) * 5 + len(player_3_ones) * 1
            
            print("Data Analysis:")
            print("Player 1:")
            print("Overall Score:", player_1_overall_score)
            average_displacement(player_1_data)
            print("Player 2:")
            print("Overall Score:", player_2_overall_score)
            average_displacement(player_2_data)
            print("Player 3:")
            print("Overall Score", player_3_overall_score)
            average_displacement(player_3_data)
            contestants = {"Player 1": player_1_overall_score, "Player 2": player_2_overall_score, "Player 3": player_3_overall_score}
            the_winner = max(contestants, key=contestants.get)
            highest_score = contestants[the_winner]
            print(f"The winner: {the_winner} with the highest score of {highest_score}")
            
            exit_code = input("Click any button to go back")
            break
        elif user_input == '3':
            print('Exiting...')
            break
        else:
            print("Option Does Not Exist! Enter valid numbers!\n")
        
except FileNotFoundError:
    print("The file is not found in that directory")


# In[ ]:




