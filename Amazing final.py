# Setup variables
global height, width, maze, noVisit, oneVisit, twoVisit, threeVisit, fourVisit, start, current, facing
height = 0
width = 0
maze = []
noVisit = []
oneVisit = []
twoVisit = []
threeVisit = []
fourVisit = []
start = (2, 0)
current = start
facing = 'right'

# Opens the text file containing the maze, extracting the size and contents of
# the maze then assigns the size to varibles and the contents to a suitably
# sized nested list structure


def processInput():
    inputFile = open("testdata.txt", "r")
    dimensions = inputFile.readline().split()
    height = int(dimensions[0]) - 1
    width = int(dimensions[1]) - 1

    for i in range(3):
        newlist = []
        for i in inputFile.readline():
            if i != '\n':
                newlist.append(int(i))
            # else:
                # dont append anything (not necessary to have an else)
        global maze
        maze.append(newlist)

    return maze, height, width

# Iterates through the maze list and adds the coordinates of every tile that
# contains a 0 to the list noVisit


def setupLists():
    for i in range(len(maze)):
        for j in range(len(maze[i])):
            if maze[i][j] == 0:
                noVisit.append((i, j))
            else:
                pass

# Generic functions for moving the robot one square in a given direction


def moveN():
    global current, facing
    current = (current[0] - 1, current[1])
    facing = 'up'


def moveE():
    global current, facing
    current = (current[0], current[1] + 1)
    facing = 'right'


def moveS():
    global current, facing
    current = (current[0] + 1, current[1])
    facing = 'down'


def moveW():
    global current, facing
    current = (current[0], current[1] - 1)
    facing = 'left'

# Iterates through the lists that show how many times each square was visited
# and promotes the current square through the lists as needed


def checkLists():
    global current
    if current in threeVisit:
        threeVisit.remove(current)
        fourVisit.append(current)
    elif current in twoVisit:
        twoVisit.remove(current)
        threeVisit.append(current)
    elif current in oneVisit:
        oneVisit.remove(current)
        twoVisit.append(current)
    elif current in noVisit:
        noVisit.remove(current)
        oneVisit.append(current)

# Checks a direction and returns whether or not the space 1 space in that
# direction is free


def look(direction):
    global maze
    if direction == 'N':
        newSquare = (current[0] - 1, current[1])
        if not 0 <= newSquare[0] <= 2 or not 0 <= newSquare[1] <= 4:
            return False
        elif maze[newSquare[0]][newSquare[1]] == 1:
            return False
        else:
            return True

    elif direction == 'S':
        newSquare = (current[0] + 1, current[1])
        if not 0 <= newSquare[0] <= 2 or not 0 <= newSquare[1] <= 4:
            return False
        elif maze[newSquare[0]][newSquare[1]] == 1:
            return False
        else:
            return True

    elif direction == 'E':
        newSquare = (current[0], current[1] + 1)
        if not 0 <= newSquare[0] <= 2 or not 0 <= newSquare[1] <= 4:
            return False
        elif maze[newSquare[0]][newSquare[1]] == 1:
            return False
        else:
            return True

    elif direction == 'W':
        newSquare = (current[0], current[1] - 1)
        if not 0 <= newSquare[0] <= 2 or not 0 <= newSquare[1] <= 4:
            return False
        elif maze[newSquare[0]][newSquare[1]] == 1:
            return False
        else:
            return True

# Until the robot reaches the start point again, repeatably add the current
# square to the lists and then move according to its rules


def traverseMaze():
    amHome = False
    haveMoved = False
    while amHome == False:
        checkLists()
        if facing == 'right':
            if look('S'):
                moveS()
            elif look('E'):
                moveE()
            elif look('N'):
                moveN()
            elif look('W'):
                moveW()
        elif facing == 'up':
            if look('E'):
                moveE()
            elif look('N'):
                moveN()
            elif look('W'):
                moveW()
            elif look('S'):
                moveS()
        elif facing == 'left':
            if look('N'):
                moveN()
            elif look('W'):
                moveW()
            elif look('S'):
                moveS()
            elif look('E'):
                moveE()
        elif facing == 'down':
            if look('W'):
                moveW()
            elif look('S'):
                moveS()
            elif look('E'):
                moveE()
            elif look('N'):
                moveN()
        if current != start:
            haveMoved = True
        elif haveMoved and current == start:
            amHome = True

# Prints output


def giveOutput():
    output = str(len(noVisit)) + '  ' + str(len(oneVisit)) + '  ' + \
        str(len(twoVisit)) + '  ' + str(len(threeVisit)) + \
        '  ' + str(len(fourVisit))

    print(output)


processInput()
setupLists()
traverseMaze()
giveOutput()
