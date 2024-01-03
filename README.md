# City Puzzle

## Background
Hi there, there are some city name puzzles in our database. Please help me find the city names by resolving the puzzle
and expose them through API `http://localhost:8080/cities` and also post them to our configured message queue. Thank you!

## Project Structure
```
com.example.city
    |----CityApplication: Spring Boot project configuration **DON"T CHANGE IT**
    |----City: donmain entity **DON"T CHANGE IT**
    |----QueueConsumer: Activemq consumer **DON"T CHANGE IT**
    |----Controller: the REST controller where your code will be placed
```

## Database
There is one table **CITY** in the pre-configured in-memory database.
There are two columns in this table: **ID** and **PUZZLE**.
You will need to write the code to read those puzzles from it.

## The PUZZLE
Each puzzle contains multiple hints, looks like this: 
```
P->U,U->N,N->E
```

Each hint `P->U` means that letter `P` is followed by letter `U` in that city name. `P` and `U` adjacent.
So the city name of this puzzle example is `PUNE`. All the hints should be used to solve the puzzle.
The length of each hint is 4. 

## Requirements
1. Read all the puzzles from database. Solve the puzzle in the `Controller`, and expose all the resolved `City` through endpoint of `GET http://localhost:8080/cities`
The response should look like this:
```json
    curl --request GET --url http://localhost:8080/cities
     
    [
	  {
		"id": 1,
		"puzzle": "P->U,U->N,N->E",
		"name": "PUNE"
	  },
	  {
		"id": 2,
		"puzzle": "A->B,X->Y",
		"name": "INVALID"
	  }
    ]

```
2. If there are multiple possible names, join them with comma.
3. If the puzzle cannot be resolved by using up all the hints, then set the city name to `INVALID`.
4. If the city name is not INVALID, then please send the name to the pre-configured in-memory queue.
5. Feel free to add more classes to the project.
6. Feel free to use Google to help you finish the project. However, don't search the puzzle solution directly.
7. You will have 50 min to finish the project.

## NOW YOU CAN START CODING. PLEASE ALSO GIVE YOUR ANSWERS TO THE QUESTIONS BELOW. YOU HAVE 50 MIN IN TOTAL. ANY QUESTIONS BEFORE YOU START?

## Two more questions (don't google the answers)
 1. Please list the ways to improve the queue efficient (more throughput, less waiting time) from consumer side?
```
<your answer>
```

2. Please write the output logged by the Typescript code below.
```Typescript
let a: { x: number; y: string; z?: number } = {
  x: 1,
  y: "2"
};
a.x = 2;
console.log(a);
console.log(a.z);
```
```
<your answer>
```

