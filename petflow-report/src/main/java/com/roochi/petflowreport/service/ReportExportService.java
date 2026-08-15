package com.roochi.petflowreport.service;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface ReportExportService {

    byte[] exportToExcel(
            String sheetName,
            List<?> data
    );
}
