package com.example.mycalculator.service.function;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;

/**
 * 表格工具类
 *
 * @author 林书浩
 * @date 2020/07/27
 */
public class TableFunction<T> {
    /**
     * 设置表格行间隔交替变色
     */
    public void tableColor(SmartTable<T> logInformationSmartTable, final int color){
        logInformationSmartTable.getConfig().setContentCellBackgroundFormat(new ICellBackgroundFormat<CellInfo>() {
            @Override
            public void drawBackground(Canvas canvas, Rect rect, CellInfo cellInfo, Paint paint) {
                int changeColor = 2;
                if (cellInfo.row%changeColor == 0){
                    paint.setColor(color);
                    canvas.drawRect(rect,paint);
                }
            }
            @Override
            public int getTextColor(CellInfo cellInfo) {
                return 0;
            }
        });
    }

}
