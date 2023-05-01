import pygame
import random
################################################
#기본 초기화 (반드시 해야하는 것들)
pygame.init()

#화면 크기 설정
screen_width = 480
screen_height = 640
screen = pygame.display.set_mode((screen_width, screen_height))
#화면 타이틀 설정
pygame.display.set_caption("똥 피하기")
#FPS
clock = pygame.time.Clock()
################################################

#1.사용자 게임 초기화 (배경화면, 게임 이미지, 좌표, 속도, 폰트 등)
backgroud = pygame.image.load(
    "C:\\Users\\Minjun\\Desktop\\PythonStudy\\pygame_basic\\BackGround.png")

character = pygame.image.load(
    "C:\\Users\\Minjun\\Desktop\\PythonStudy\\pygame_basic\\Character.png")
character_size = character.get_rect().size
character_width = character_size[0]
character_height = character_size[1]
character_x_pos = screen_width / 2 - (character_width/2)
character_y_pos = screen_height - character_height
to_x = 0
speed = 0.6

class Poop :
    def __init__(self,x_pos,y_pos) :
        self.y_pos = y_pos
        self.enemy = pygame.image.load(
            "C:\\Users\\Minjun\\Desktop\\PythonStudy\\pygame_basic\\Enemy.png")
        self.size = self.enemy.get_rect().size
        self.width = self.size[0]
        self.height = self.size[1]
        self.x_pos = random.randrange(x_pos - self.size[0])

running = True

poopoo = Poop(screen_width,0)

while running:
    dt = clock.tick(60)

    #2. 이벤트 처리(키보드, 마우스 등)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                to_x -= speed
            elif event.key == pygame.K_RIGHT:
                to_x += speed
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                to_x = 0
    
    character_x_pos += to_x * dt
    poopoo.y_pos += speed * dt
    #3. 게임 캐릭터 위치 정의
    character_rect = character.get_rect()
    character_rect.left = character_x_pos
    character_rect.top = character_y_pos
    if character_x_pos < 0:
        character_x_pos = 0
    elif character_x_pos > screen_width - character_width:
        character_x_pos = screen_width - character_width

    enemy_rect = poopoo.enemy.get_rect()
    enemy_rect.left = poopoo.x_pos
    enemy_rect.top = poopoo.y_pos
    if poopoo.y_pos > screen_height:
        del poopoo
        poopoo = Poop(screen_width, 0)
    #4.충돌 처리
    if character_rect.colliderect(enemy_rect):
        print("충돌 했어요")
        running = False
    #5. 화면에 그리기
    screen.blit(backgroud, (0, 0))  # 배경 그리기
    screen.blit(character, (character_x_pos, character_y_pos))
    screen.blit(poopoo.enemy, (poopoo.x_pos, poopoo.y_pos))  # 적 그리기
    pygame.display.update()  # 게임 화면 다시 그리기

pygame.quit()
