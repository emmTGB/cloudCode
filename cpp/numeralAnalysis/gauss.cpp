#include <stdio.h>

#define N 3 // 未知数的数量

void printMatrix(float matrix[N][N + 1]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <= N; j++) {
            printf("%.2f\t", matrix[i][j]);
        }
        printf("\n");
    }
}

void gaussElimination(float matrix[N][N + 1]) {
    for (int i = 0; i < N; i++) {
        // 将当前列的第i行元素缩放为1
        float divisor = matrix[i][i];
        for (int j = 0; j <= N; j++) {
            matrix[i][j] /= divisor;
        }

        // 将其他行的第i列元素消为0
        for (int k = 0; k < N; k++) {
            if (k != i) {
                float factor = matrix[k][i];
                for (int j = 0; j <= N; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
        }
    }
}

int main() {
    float coefficients[N][N + 1] = {
        {2, -1, 1, 8},
        {-3, -1, 2, -11},
        {-2, 1, 2, -3}
    };

    printf("原始矩阵:\n");
    printMatrix(coefficients);

    gaussElimination(coefficients);

    printf("\n高斯消去后的矩阵:\n");
    printMatrix(coefficients);

    printf("\n解:\n");
    for (int i = 0; i < N; i++) {
        printf("x%d = %.2f\n", i + 1, coefficients[i][N]);
    }

    return 0;
}
