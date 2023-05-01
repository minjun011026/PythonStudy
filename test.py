import math

n = 123456    # 2부터 1,000까지의 모든 수에 대하여 소수 판별
# 처음에는 모든 수가 소수(True)인 것으로 초기화 (0과 1은 제외)
array = [True for i in range((n*2) + 2)]

    # scores 알고리즘
for i in range(2, int(math.sqrt((n*2))) + 1):   # 2부터 n의 제곱근까지의 모든 수를 확인하며
    if array[i]:                            # i가 소수인 경우 (남은 수인 경우)
        for j in range(i + i, 2*n+1, i):        # i의 배수 모두 False로 변환
            array[j] = False
nums = []

while(1) :
    # 모든 소수 출력
    num = int(input())
    if num == 0 :
        break
    nums.append(num) 
for x in nums:
    cnt = 0
    for i in range(x+1, (2*x)+1):
            if array[i]:
                cnt+=1
    print(cnt)

