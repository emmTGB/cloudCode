import random

from block import *
from const import *
from utils import *


class BlockGroup(object):
    @staticmethod
    def generateBlockGroupConfig(baseRowIdx, baseColIdx):
        shapeIdx = random.randint(0, len(BLOCK_SHAPE) - 1)
        bType = random.randint(0, BlockType.BLOCKMAX - 1)
        configList = []
        rotIdx = 0
        for i in range(len(BLOCK_SHAPE[shapeIdx][rotIdx])):
            config = {
                'blockType': bType,
                'baseRowIdx': baseRowIdx,
                'baseColIdx': baseColIdx,
                'blockGroupIdx': i,
                'blockRot': rotIdx,
                'blockShape': shapeIdx
            }
            configList.append(config)
        return configList

    def __init__(self, blockGroupType, width, height, blockConfigList, relPos):
        super().__init__()

        self.blocks = []
        self.time = 0
        self.blockGroupType = blockGroupType
        self.pressTime = {}
        self.dropInterval = 300

        self.curMoveDir = None

        self.isEliminating = False
        self.eliminateTime = None
        self.eliminateRow = []

        for config in blockConfigList:
            blk = Block(config['blockType'], config['blockShape'], config['blockRot'],
                        config['blockGroupIdx'], config['baseRowIdx'], config['baseColIdx'], width, height, relPos)
            self.blocks.append(blk)

    def draw(self, surface):
        for b in self.blocks:
            b.draw(surface)

    def update(self):
        oldTime = self.time
        curTime = getCurrentTime()
        diffTime = curTime - oldTime
        if self.blockGroupType == BlockGroupType.DROPPING:
            if diffTime >= self.dropInterval:
                self.time = getCurrentTime()
                for b in self.blocks:
                    b.drop()

        for blk in self.blocks:
            blk.update()

        if self.isEliminatingFun():
            if getCurrentTime() - self.eliminateTime > 500:
                for i in range(0, len(self.eliminateRow)):
                    tmpBlocks = []
                    row = self.eliminateRow[i] + i
                    for blk in self.blocks:
                        if blk.getIndex()[0] != row:
                            if blk.getIndex()[0] < row:
                                blk.drop()
                            tmpBlocks.append(blk)
                    self.blocks = tmpBlocks
                self.setEliminate(False)
                self.eliminateRow = []

    def doEliminate(self, row):
        self.eliminateTime = getCurrentTime()
        self.setEliminate(True)
        eliminateRow = {}
        for col in range(0, GAME_COL):
            idx = (row, col)
            eliminateRow[idx] = 1
        for blk in self.blocks:
            if eliminateRow.get(blk.getIndex()):
                blk.startBlink()

    def processEliminate(self):
        rowHash = {}
        allIndexes = self.getBlockIndexes()
        for idx in allIndexes:
            rowHash[idx] = 1
        for row in range(GAME_ROW - 1, -1, -1):  # 对每一行进行检查
            full = True
            for col in range(0, GAME_COL):
                idx = (row, col)
                if not rowHash.get(idx):
                    full = False
                    break
            if full:
                self.eliminateRow.append(row)
        for row in self.eliminateRow:
            self.doEliminate(row)
        return len(self.eliminateRow)

    def setEliminate(self, el):
        self.isEliminating = el

    def isEliminatingFun(self):
        return self.isEliminating

    def getBlockIndexes(self):
        return [block.getIndex() for block in self.blocks]

    def getNextBlockIndexes(self, mDir):
        return [block.getNextIndex(mDir) for block in self.blocks]

    def getBlocks(self):
        return self.blocks

    def clearBlocks(self):
        self.blocks = []

    def addBlock(self, blk):
        self.blocks.append(blk)

    def checkAndSetPressedTime(self, key):
        ret = False
        if getCurrentTime() - self.pressTime.get(key, 0) > 50:
            ret = True
        self.pressTime[key] = getCurrentTime()
        return ret
