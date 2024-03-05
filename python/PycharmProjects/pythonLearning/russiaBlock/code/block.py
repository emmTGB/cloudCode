import pygame

from const import *
from utils import *


class Block(pygame.sprite.Sprite):
    def __init__(self, blockType, blockShape, blockRot, blockGroupIdx, baseRowIdx, baseColIdx, width, height, relPos):
        super().__init__()
        self.rect = None
        self.image = None

        self.blockType = blockType
        self.blockRot = blockRot
        self.blockShape = blockShape
        self.blockGroupIdx = blockGroupIdx
        self.baseRowIdx = baseRowIdx
        self.baseColIdx = baseColIdx
        self.width = width
        self.height = height
        self.relPos = relPos

        self.blink = False
        self.blinkTime = None
        self.blinkCount = 0

        self.loadImage()
        self.updateImagePos()

    def loadImage(self):
        self.image = pygame.image.load(BLOCK_RES[self.blockType])
        self.image = pygame.transform.scale(self.image, (self.width, self.height))

    def updateImagePos(self):
        self.rect = self.image.get_rect()
        self.rect.left = self.relPos[0] + self.width * self.colIdx
        self.rect.top = self.relPos[1] + self.height * self.rowIdx

    def startBlink(self):
        self.blink = True
        self.blinkTime = getCurrentTime()

    def update(self):
        if self.blink:
            diffTime = getCurrentTime() - self.blinkTime
            self.blinkCount = int(diffTime // 50)

    def draw(self, surface):  # 绘制方块
        self.updateImagePos()
        if self.blink and self.blinkCount % 2 == 0:
            return
        surface.blit(self.image, self.rect)

    def getIndex(self):
        return int(self.rowIdx), int(self.colIdx)

    def getNextIndex(self, mDir):
        if mDir == 0:
            self.doRotate()
        return self.rowIdx + MOVE_DIRECTION[mDir][0], self.colIdx + MOVE_DIRECTION[mDir][1]

    def getBlockConfigIndex(self):
        return BLOCK_SHAPE[self.blockShape][self.blockRot][self.blockGroupIdx]

    def doLeft(self):
        self.baseColIdx -= 1

    def doRight(self):
        self.baseColIdx += 1

    def drop(self):
        self.baseRowIdx += 1

    def doRotate(self):
        self.blockRot += 1
        if self.blockRot >= len(BLOCK_SHAPE[self.blockShape]):
            self.blockRot = 0

    def undoRotate(self):
        self.blockRot -= 1
        if self.blockRot < 0:
            self.blockRot = len(BLOCK_SHAPE[self.blockShape]) - 1

    @property
    def rowIdx(self):
        return self.baseRowIdx + self.getBlockConfigIndex()[0]

    @property
    def colIdx(self):
        return self.baseColIdx + self.getBlockConfigIndex()[1]
