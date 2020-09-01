# Drawing program

Here is a console application allowing user to create a canvas and insert **Lines** and **Rectangles**.

## Technical stack
  - Java 8
  
  
## How to run the project ?
  1. Clone the project locally
  2. Run the following commands :
  ```bash
  mvn clean install
  ```
  
  ```bash
  mvn exec:java
  ```
  
## How to use the application ?
 - To create a Canvas :
 ```bash
   C width height
    example: C 2 3 - Create a Canvas of size 2x3
   ```

- To create a Line :
 ```bash
   L x1 y1 x2 y2
    (x1, y1) is the starting point / (x2, y2) is the last point
    You can also create a Line of size 1 which is represented by a Point
    example : L 1 1 4 1 - Create a horizontal Line of size 4
   ```

- To create a Rectangle :
 ```bash
   R x1 y1 x2 y2
    (x1, y1) is the top-left corner point / (x2, y2) is the bottom-right corner point
    example R 1 1 3 3 - Will create a square
            R 1 1 1 1 - Will create a point as the height and width are equal to 1
            R 1 1 7 1 - Will create a line as the height is equal to 1
   ```

- To Fill a zone with specified symbol :
 ```bash
   B x1 y1 symbol
    (x1, y1) is the point from where the filling will start
    example B 1 1 g - Will fill all the empty points which are adjacent to point (1,1) using the symbol 'g'
   ```

- To quit the application :
 ```bash
   Q
   ```