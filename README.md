# To Do List Application

## General Information
ToDoList is a standalone command line application which allows to add, display and delete text tasks and their priorities.

## How to run
1. mvn install
2. java -jar .\target\ToDoList-1.0-SNAPSHOT.jar

## Features

- get all [Gets all tasks from the list]
- get [Get single task by ID from the list]
- delete [Removes given task by ID from the list]
- add [Adds given task to the list; accepts a task text and an optional task priority: high, average (default), low]
- exit [Closes the program]
- quit [Closes the program]
- q [Closes the program]
- help [Displays all available commands]

## ToDOs

- to add task edit support
- to add task ordering
- to add task filtering
- to add database integration, persist the tasks in a database table