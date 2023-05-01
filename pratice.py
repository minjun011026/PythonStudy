N = int(input())
picture = []
isend = False
for _ in range(N) :
    colors = input()
    colors = list(colors)
    picture.append(colors)

def search(temp) :
    if temp <N : #제일 윗줄
        if x % N != 0:
            if picture[temp+N] ==picture[temp] :
                search[temp+N]
    if x%N != 0 : #왼쪽줄
        if picture[temp-1] == picture[temp]:
            search[temp-1]
    if x%N !=N-1 :#오른쪽줄
        if picture[temp+1] == picture[temp]:
            search[temp+1]
    if x>=N*(N-1) :#맨밑줄
        if picture[temp-N] == picture[temp]:
            search[temp-N]
    picture[temp] = 'X'
#아예 케이스마다 나눠서 처리후 재귀직전에 X로 
cnt = 0
for x in range(N) :
    if picture[x] != 'X' :
        temp = x
        search(temp)
        cnt+=1
print(cnt)
#재귀 써서 탐색 돌리고 cnt +1 
