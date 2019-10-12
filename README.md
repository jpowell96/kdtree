# kdtree
A personal project to implement a very basic kd-tree that supports spatial data.
It's a re-do of a project I did when attending university.

## Implementation
The KdTree supports the following operations
- nearesNeighbor: Find the nearest neighbor to the given location
- kNearestNeighbors: Given k and a location, find the k nearest neighbors to the location
- radiusSearch: Given a radius r and a location, find all nodes within the radius of that location


The implementation of this project is based on the description from this class project:
https://cs.brown.edu/courses/cs0320/projects/stars.html

These links were helpful for understanding how to go about implementaion:
https://en.wikipedia.org/wiki/K-d_tree
http://www.cs.cornell.edu/courses/cs4780/2017sp/lectures/lecturenote16.html

## Expanding
- Add iterator to class
- Implement other forms of trees that extend the KdTree interface
- Consider creating an AbstractKdTree class that other all subclasses inherit from



