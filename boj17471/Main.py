import sys
from itertools import combinations
from collections import deque

input = sys.stdin.readline

n = int(input())

people = list(map(int, input().split()))
people_sum = sum(people)

graph = [[0]]

result = 1e9

q = deque()

for i in range(n):
    graph.append(list(map(int,input().split())))

for i in range(1, n//2):
    for j in combinations(people, i):
        if result > people_sum - sum(j):
            visited = [False] * (n+1)
            q.append(j[0])
            
            while(q):
                num = q.popleft()

                visited[num] = True

                if graph[num][0] != 0:
                    for k in range(1,len(graph[num])):
                        if (k in j) and visited[k] == False :
                            q.append(k)
                            visited[k] = True

            for idx in range(n):
                if idx not in j:
                    q.append(idx)
                    break
            
            while(q):
                num = q.popleft()

                visited[num] = True

                if graph[num][0] != 0:
                    for k in range(1,len(graph[num])):
                        if (k in j) and visited[k] == False :
                            q.append(k)
                            visited[k] = True
            
            if False not in visited:
                result = people_sum - sum(j)

if result == 1e9:
    print(-1)
else :
    print(result)