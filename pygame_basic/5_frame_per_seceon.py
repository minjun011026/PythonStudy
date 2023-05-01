import pygame
pygame.init()

screen_width = 480
screen_height = 640
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Nado Game")
clock = pygame.time.Clock()

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
to_y = 0
character_speed  = 0.6

running = True

while running:
    dt = clock.tick(30)

    print("FPS : " + str(clock.get_fps()))
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                to_x -= character_speed
            elif event.key == pygame.K_RIGHT:
                to_x += character_speed
            elif event.key == pygame.K_UP:
                to_y -= character_speed
            elif event.key == pygame.K_DOWN:
                to_y += character_speed
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                to_x = 0
            elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                to_y = 0

    character_x_pos += to_x * dt
    character_y_pos += to_y * dt
    if character_x_pos < 0:
        character_x_pos = 0
    elif character_x_pos > screen_width - character_width:
        character_x_pos = screen_width - character_width
    if character_y_pos < 0:
        character_y_pos = 0
    elif character_y_pos > screen_height - character_height:
        character_y_pos = screen_height - character_height

    screen.blit(backgroud, (0, 0))  # 배경 그리기
    screen.blit(character, (character_x_pos, character_y_pos))
    pygame.display.update()  # 게임 화면 다시 그리기
pygame.quit()
