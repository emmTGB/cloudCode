BLOCK_WIDTH = 32
BLOCK_HEIGHT = 32
BLOCK_VALUE = 10
APPRECIATION_RATE = 0.5  # 增值率

FONT_SIZE = 32
FONT_COLOR = (255, 255, 255)

GAME_ROW = 22
GAME_COL = 14
GAME_START = 50
EXTRA_PIX_LEFT = FONT_SIZE * 4
EXTRA_COL_RIGHT = 6

WINDOW_WIDTH = EXTRA_PIX_LEFT + BLOCK_WIDTH * (GAME_COL + EXTRA_COL_RIGHT)
WINDOW_HEIGHT = BLOCK_HEIGHT * GAME_ROW + GAME_START


class BlockType:
    RED = 0
    ORANGE = 1
    YELLOW = 2
    GREEN = 3
    CYAN = 4
    BLUE = 5
    PURPLE = 6
    BLOCKMAX = 7


class BlockGroupType:
    FIXED = 0
    DROPPING = 1


BLOCK_RES = {
    BlockType.RED: "../res/0.jpg",
    BlockType.ORANGE: "../res/1.jpg",
    BlockType.YELLOW: "../res/2.jpg",
    BlockType.GREEN: "../res/3.jpg",
    BlockType.CYAN: "../res/4.jpg",
    BlockType.BLUE: "../res/5.jpg",
    BlockType.PURPLE: "../res/6.jpg"
}


BLOCK_SHAPE = [
    [((0, 0), (0, 1), (1, 0), (1, 1)), ],  # 方
    [((0, 0), (0, 1), (0, 2), (0, 3)), ((0, 0), (1, 0), (2, 0), (3, 0))],  # 条
    [((0, 0), (0, 1), (1, 1), (1, 2)), ((0, 1), (1, 0), (1, 1), (2, 0))],  # 左Z
    [((0, 1), (0, 2), (1, 0), (1, 1)), ((0, 0), (1, 0), (1, 1), (2, 1))],  # 右Z
    [((0, 1), (1, 0), (1, 1), (1, 2)), ((0, 1), (1, 1), (1, 2), (2, 1)),
     ((1, 0), (1, 1), (1, 2), (2, 1)), ((0, 1), (1, 0), (1, 1), (2, 1))],  # 飞机
    [((0, 0), (1, 0), (1, 1), (1, 2)), ((0, 0), (0, 1), (1, 0), (2, 0)),
     ((0, 0), (0, 1), (0, 2), (1, 2)), ((0, 1), (1, 1), (2, 0), (2, 1))],  # 左L
    [((0, 2), (1, 0), (1, 1), (1, 2)), ((0, 0), (1, 0), (2, 0), (2, 1)),
     ((0, 0), (0, 1), (0, 2), (1, 0)), ((0, 0), (0, 1), (1, 1), (2, 1))]  # 右L
]


MOVE_DIRECTION = [  # 上下左右
    (0, 0),
    (1, 0),
    (0, -1),
    (0, 1)
]