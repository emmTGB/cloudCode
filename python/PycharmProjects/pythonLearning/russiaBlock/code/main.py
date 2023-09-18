import sys

from const import *
from game import *

pygame.init()

DISPLAYPORT = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
game = Game(DISPLAYPORT)

while True:
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()

    game.update()
    DISPLAYPORT.fill((0, 0, 0))
    game.draw()

    pygame.display.update()
