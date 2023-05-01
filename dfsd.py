from collections import deque

N = int(input())
queue = deque()
for x in range(N) :
    queue.append(x+1)
while len(queue) != 1 :
    queue.popleft()
    queue.append(queue[0])
    queue.popleft()
print(queue[0])