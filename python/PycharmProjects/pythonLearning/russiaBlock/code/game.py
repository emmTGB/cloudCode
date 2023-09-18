from pygame.locals import *

from blockGroup import *
from const import *


class Game(pygame.sprite.Sprite):
    def __init__(self, surface):
        super().__init__()
        self.surface = surface
        self.fixedBlockGroup = BlockGroup(BlockGroupType.FIXED, BLOCK_WIDTH, BLOCK_HEIGHT, [], self.getRelPos())
        self.droppingBlockGroup = None
        self.nextBlockGroup = None
        self.droppingConf = None

        self.isCombining = False
        self.combineDelay = 300
        self.combineTime = None

        self.scoreFont = pygame.font.Font(None, FONT_SIZE)
        self.score = 0

        self.gameOverImage = pygame.image.load('../res/OVER.png')
        self.gameOverImage = pygame.transform.scale(self.gameOverImage, (5 * BLOCK_WIDTH, 5 * BLOCK_HEIGHT))
        self.isGameOver = False

        self.generateNextBlockGroup()

    def generateDroppingBlockGroup(self):
        self.droppingBlockGroup = BlockGroup(BlockGroupType.DROPPING, BLOCK_WIDTH, BLOCK_HEIGHT,
                                             self.droppingConf, self.getRelPos())

    def generateNextBlockGroup(self):
        self.droppingConf = BlockGroup.generateBlockGroupConfig(0, GAME_COL / 2 - 1)
        self.nextBlockGroup = BlockGroup(BlockGroupType.DROPPING, BLOCK_WIDTH, BLOCK_HEIGHT,
                                         self.droppingConf, self.getPrePos())

    def detectCollision(self, mDir):
        idxHash = {}
        allIndexes = self.fixedBlockGroup.getBlockIndexes()
        for idx in allIndexes:
            idxHash[idx] = 1
        nextIndexes = self.droppingBlockGroup.getNextBlockIndexes(mDir)

        for nextIdx in nextIndexes:
            if idxHash.get(nextIdx):
                return True
            if mDir == 1 and nextIdx[0] >= GAME_ROW:
                return True
            if nextIdx[1] < 0 or nextIdx[1] >= GAME_COL:
                return True
        return False

    def keyDownHandler(self, blockGroup):
        pressed = pygame.key.get_pressed()
        if pressed[K_LEFT] and blockGroup.checkAndSetPressedTime(K_LEFT):
            if self.detectCollision(2):
                return
            for blk in blockGroup.blocks:
                blk.doLeft()
        elif pressed[K_RIGHT] and blockGroup.checkAndSetPressedTime(K_RIGHT):
            if self.detectCollision(3):
                return
            for blk in blockGroup.blocks:
                blk.doRight()
        if pressed[K_DOWN]:
            blockGroup.dropInterval = 50
        else:
            blockGroup.dropInterval = 500
        if pressed[K_UP] and blockGroup.checkAndSetPressedTime(K_UP):
            if self.detectCollision(0):
                for blk in blockGroup.blocks:
                    blk.undoRotate()

    def update(self):
        if self.isGameOver:
            return
        self.fixedBlockGroup.update()
        if self.fixedBlockGroup.isEliminatingFun():
            return
        if self.droppingBlockGroup:
            self.keyDownHandler(self.droppingBlockGroup)
            if self.detectCollision(1):
                if not self.isCombining:
                    self.combineTime = getCurrentTime()
                    self.isCombining = True
                elif getCurrentTime() - self.combineTime >= self.combineDelay:
                    self.doCombine()
                    self.isCombining = False
            else:
                self.droppingBlockGroup.update()
                self.isCombining = False
        else:
            self.generateDroppingBlockGroup()
            self.generateNextBlockGroup()
        self.checkGameOver()

    def doCombine(self):
        blocks = self.droppingBlockGroup.getBlocks()
        for blk in blocks:
            self.fixedBlockGroup.addBlock(blk)
        self.droppingBlockGroup.clearBlocks()
        self.droppingBlockGroup = None
        rows = self.fixedBlockGroup.processEliminate()
        if rows != 0:
            self.countReward(rows)

    def checkGameOver(self):
        allIndexes = self.fixedBlockGroup.getBlockIndexes()
        for idx in allIndexes:
            if idx[0] < 2:
                self.isGameOver = True

    def countReward(self, rows):
        appreciate = 1
        for i in range(rows):
            self.score += BLOCK_VALUE * GAME_COL * appreciate
            appreciate += APPRECIATION_RATE

    def draw(self):
        self.fixedBlockGroup.draw(self.surface)
        if self.droppingBlockGroup:
            self.droppingBlockGroup.draw(self.surface)
        if self.nextBlockGroup:
            self.nextBlockGroup.draw(self.surface)

        if self.isGameOver:
            rect = self.gameOverImage.get_rect()
            rect.centerx = WINDOW_WIDTH // 2
            rect.centery = WINDOW_HEIGHT // 2
            self.surface.blit(self.gameOverImage, rect)

        scoreTextImage = self.scoreFont.render('Score:' + str(int(self.score)), True, FONT_COLOR)
        self.surface.blit(scoreTextImage, (10, 20))

    @staticmethod
    def getRelPos():
        return EXTRA_PIX_LEFT, GAME_START

    @staticmethod
    def getPrePos():
        return WINDOW_WIDTH - (EXTRA_COL_RIGHT + GAME_COL // 2 - 2) * BLOCK_WIDTH, GAME_START
