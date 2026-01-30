import pandas as pd

filename = '2024.3月'

# 读取原始Excel文件的所有sheet，不包含标题
df_dict = pd.read_excel(f'{filename}.xlsx', sheet_name=None, header=None)

# 初始化三个空的DataFrame用于存储结果


# 写入新的Excel文件
with (pd.ExcelWriter(f'申请表{filename}.xlsx') as writer1,
      pd.ExcelWriter(f'领料单{filename}.xlsx') as writer2,
      pd.ExcelWriter(f'入库单{filename}.xlsx') as writer3):
    for sheet_name, df in df_dict.items():
        # 删除前5行和最后3行（保留6行到倒数第4行）
        try:
            processed_df = df.iloc[5:-3].reset_index(drop=True)
        except:
            processed_df = pd.DataFrame()

        # 删除第二列为空的行
        processed_df = processed_df.dropna(subset=[1])

        if processed_df.empty:
            continue

        # 提取所需列并重新排列（自动填充缺失列为NaN）
        # result_sheet1 = pd.concat([
        #     result_sheet1,
        #     processed_df.reindex(columns=[0, 2, 1])
        # ], ignore_index=True)
        #
        # result_sheet2 = pd.concat([
        #     result_sheet2,
        #     processed_df.reindex(columns=[0, 1, 3, 4])
        # ], ignore_index=True)
        #
        # result_sheet3 = pd.concat([
        #     result_sheet3,
        #     processed_df.reindex(columns=[0, 2, 4])
        # ], ignore_index=True)
        result_sheet1 = processed_df.reindex(columns=[1, 2, 3, 4])

        result_sheet2 = processed_df.reindex(columns=[0, 1, 2, 3, 4])

        result_sheet3 = processed_df.reindex(columns=[0, 1, 2, 3, 4, 7, 8, 9])

        result_sheet1.to_excel(writer1, sheet_name='申请表 ' + sheet_name, index=False, header=False)
        result_sheet2.to_excel(writer2, sheet_name='领料单 ' + sheet_name, index=False, header=False)
        result_sheet3.to_excel(writer3, sheet_name='入库单 ' + sheet_name, index=False, header=False)

print("处理完成，结果已保存至output.xlsx")