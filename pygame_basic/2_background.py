import pygame
pygame.init()

screen_width = 480
screen_height = 640
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Nado Game")

backgroud = pygame.image.load("C:\\Users\\Minjun\\Desktop\\PythonStudy\\pygame_basic\\BackGround.png")

running = True

while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    screen.blit(backgroud, (0,0)) #배경 그리기
    pygame.display.update() #게임 화면 다시 그리기
pygame.quit()
