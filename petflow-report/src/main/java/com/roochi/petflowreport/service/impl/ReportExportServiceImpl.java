package com.roochi.petflowreport.service.impl;

import com.roochi.petflowreport.service.ReportExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Service
public class ReportExportServiceImpl
        implements ReportExportService {

    @Override
    public byte[] exportToExcel(
            String sheetName,
            List<?> data
    ) {

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream()
        ) {

            Sheet sheet =
                    workbook.createSheet(
                            sheetName
                    );

            if (data == null ||
                    data.isEmpty()) {

                workbook.write(outputStream);

                return outputStream.toByteArray();
            }

            Object first =
                    data.get(0);

            Field[] fields =
                    first.getClass()
                            .getDeclaredFields();

            Row header =
                    sheet.createRow(0);

            for (int i = 0;
                 i < fields.length;
                 i++) {

                fields[i].setAccessible(true);

                Cell cell =
                        header.createCell(i);

                cell.setCellValue(
                        fields[i].getName()
                );
            }

            for (int rowIndex = 0;
                 rowIndex < data.size();
                 rowIndex++) {

                Object item =
                        data.get(rowIndex);

                Row row =
                        sheet.createRow(
                                rowIndex + 1
                        );

                for (int columnIndex = 0;
                     columnIndex < fields.length;
                     columnIndex++) {

                    Field field =
                            fields[columnIndex];

                    field.setAccessible(true);

                    Object value =
                            field.get(item);

                    Cell cell =
                            row.createCell(
                                    columnIndex
                            );

                    writeCellValue(
                            cell,
                            value
                    );
                }
            }

            for (int i = 0;
                 i < fields.length;
                 i++) {

                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Failed to export report.",
                    e
            );
        }
    }

    private void writeCellValue(
            Cell cell,
            Object value
    ) {

        if (value == null) {
            cell.setBlank();
            return;
        }

        if (value instanceof Number number) {

            cell.setCellValue(
                    number.doubleValue()
            );

            return;
        }

        if (value instanceof Boolean bool) {

            cell.setCellValue(bool);

            return;
        }

        cell.setCellValue(
                value.toString()
        );
    }
}
