package dev.aziz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class City {
    String name;
    List<Edge> neighbors;

    public City(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addEdge(int neighborIndex, int cost) {
        neighbors.add(new Edge(neighborIndex, cost));
    }
}

class Edge {
    int neighborIndex;
    int cost;

    public Edge(int neighborIndex, int cost) {
        this.neighborIndex = neighborIndex;
        this.cost = cost;
    }
}

public class SecondTask {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine().trim());

        while (testCases-- > 0) {
            // Read number of cities
            int n = Integer.parseInt(scanner.nextLine().trim());

            // Create city list
            Map<String, Integer> cityIndexMap = new HashMap<>();
            List<City> cities = new ArrayList<>();

            // Read cities and their connections
            for (int i = 0; i < n; i++) {
                String cityName = scanner.nextLine().trim();
                cityIndexMap.put(cityName, i);
                City city = new City(cityName);
                cities.add(city);

                // Read neighbors for the city
                int numNeighbors = Integer.parseInt(scanner.nextLine().trim());
                for (int j = 0; j < numNeighbors; j++) {
                    String[] connection = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(connection[0]) - 1; // converting to 0-based index
                    int cost = Integer.parseInt(connection[1]);
                    city.addEdge(neighborIndex, cost);
                }
            }

            // Process queries
            int numQueries = Integer.parseInt(scanner.nextLine().trim());
            for (int q = 0; q < numQueries; q++) {
                String[] query = scanner.nextLine().split(" ");
                String sourceCity = query[0];
                String destinationCity = query[1];

                int sourceIndex = cityIndexMap.get(sourceCity);
                int destIndex = cityIndexMap.get(destinationCity);

                // Find the minimum cost between source and destination
                int minCost = dijkstra(cities, sourceIndex, destIndex);
                System.out.println();
                System.out.println(minCost);
            }

//            if (testCases > 0) {
//                scanner.nextLine();
//            }
        }

        scanner.close();
    }

    private static int dijkstra(List<City> cities, int sourceIndex, int destIndex) {
        int citiesSize = cities.size();
        int[] dist = new int[citiesSize];
        Arrays.fill(dist, INF);
        dist[sourceIndex] = 0;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        priorityQueue.add(new int[]{sourceIndex, 0});

        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();
            int currentNode = curr[0];
            int cost = curr[1];

            if (cost > dist[currentNode]) continue;

            for (Edge edge : cities.get(currentNode).neighbors) {
                int neighborNode = edge.neighborIndex;
                int newDist = dist[currentNode] + edge.cost;

                if (newDist < dist[neighborNode]) {
                    dist[neighborNode] = newDist;
                    priorityQueue.add(new int[]{neighborNode, newDist});
                }
            }
        }

        return dist[destIndex] == INF ? -1 : dist[destIndex];
    }
}