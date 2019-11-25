//package com.xqh.severshiro.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.*;
//
///**
// * @author gjf
// * @date 2019/3/18 18:20
// * @version 1.0
// */
//@Slf4j
//public class ExcelUtil {
//    public static byte[] createCSVUtil(List<CellModel> cellNameList, List<LinkedHashMap> exportData, LinkedHashMap rowMapper) {
//
//        int cellRow = 0;
//        //表格创建&命名
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("统计");
//        sheet.setColumnWidth(0,15000);
//        //boolean judgeColor = true;
//        HSSFCellStyle cellStyle2;
//        HSSFRow row = sheet.createRow(cellRow);
//        for (CellModel cellModel : cellNameList) {
//            //遍历插入表头
//            HSSFCell cell = row.createCell(cellModel.getStartColumn());
//            cellStyle2 = workbook.createCellStyle();
//            //自动换行*重要*
//            cellStyle2.setWrapText(true);
//            cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
////            if(judgeColor){
////                cellStyle2.setFillForegroundColor(HSSFColor.WHITE.index);
////            }else{
////                cellStyle2.setFillForegroundColor(HSSFColor.GREEN.index);
////            }
//            //cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//            cell.setCellValue(cellModel.getCellName());
//            cell.setCellStyle(cellStyle2);
//            //judgeColor = !judgeColor;
//        }
//
//        for (CellModel cellModel : cellNameList) {
//            sheet.addMergedRegion(new CellRangeAddress(cellModel.getStartRow(), cellModel.getEndRow(), cellModel.getStartColumn(), cellModel.getEndColumn()));
//        }
//
//        iterRow(rowMapper, sheet, ++cellRow, workbook);
//
//        for (LinkedHashMap hashMap : exportData) {
//            iterRow(hashMap, sheet, ++cellRow, workbook);
//        }
//        ByteArrayOutputStream outputStream ;
//
//        try {
//            outputStream = new ByteArrayOutputStream();
//            workbook.write(outputStream);
//        }catch (IOException e){
//            throw new BaseException(CodeEnum.CREATE_EXCEL_FAILED);
//        }
//        return outputStream.toByteArray();
//    }
//
//    @SuppressWarnings("unchecked")
//    private static void iterRow(LinkedHashMap hashMap, HSSFSheet sheet, Integer cellRow,HSSFWorkbook workbook){
//        HSSFRow rowValue = sheet.createRow(cellRow);
//        //boolean judgeColor = true;
//        HSSFCellStyle cellStyle;
//        for (Map.Entry entryRow : (Iterable<Map.Entry>) hashMap.entrySet()) {
//            cellStyle = workbook.createCellStyle();
//            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
////            if(judgeColor){
////                cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
////            }else{
////                cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
////            }
//            //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//            Integer key = Integer.valueOf(entryRow.getKey().toString());
//            String value = entryRow.getValue().toString();
//            HSSFCell cellValue = rowValue.createCell(key - 1);
//            cellValue.setCellValue(value);
//            cellValue.setCellStyle(cellStyle);
//            //judgeColor = !judgeColor;
//        }
//    }
//
//    public static void getExcelArtifactSheetByModel(HSSFWorkbook workbook,HSSFSheet artifactSheet, List<ExcelArtifactRowModel> result, LinkedHashMap<Integer,IssueType> positiveIssueTypeMap, LinkedHashMap<Integer,IssueType> negativeIssueTypeMap, LinkedHashMap<Integer,Priority> priorityMap){
//        HSSFFont font = workbook.createFont();
//        font.setFontHeightInPoints((short)10);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        //第一层
//        HSSFCellStyle nameStyle = workbook.createCellStyle();
//        nameStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        nameStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        nameStyle.setWrapText(true);
//        nameStyle.setFont(font);
//
//        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cellStyle.setFont(font);
//
//        HSSFCellStyle headerStyle = workbook.createCellStyle();
//        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        headerStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
//        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setFont(font);
//
//
//        int mergeStart = 1;
//        CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 0);
//        List<String> headerFirst = new LinkedList<>();
//        headerFirst.add("pictureName");
//        headerFirst.add("artifact");
//        cra.setFirstColumn(mergeStart);
//        cra.setFirstRow(0);
//        cra.setLastRow(0);
//
//        for (int i = 1;i<negativeIssueTypeMap.size()*priorityMap.size();i++){
//            headerFirst.add(StringUtils.EMPTY);
//        }
//        cra.setLastColumn(negativeIssueTypeMap.size()*priorityMap.size());
//        artifactSheet.addMergedRegion(cra);
//        headerFirst.add("Score");
//        //第二层
//        List<String> headerSecond = new LinkedList<>();
//        headerSecond.add(StringUtils.EMPTY);
//        cra.setFirstRow(1);
//        cra.setLastRow(1);
//        mergeStart = 0;
//
//        for (Map.Entry<Integer,IssueType> issueTypeEntry : negativeIssueTypeMap.entrySet()){
//            mergeStart++;
//            cra.setFirstColumn(mergeStart);
//            headerSecond.add(issueTypeEntry.getValue().getTypeName());
//            for (int i = 1;i<priorityMap.size();i++){
//                mergeStart++;
//                headerSecond.add(StringUtils.EMPTY);
//            }
//            cra.setLastColumn(mergeStart);
//            artifactSheet.addMergedRegion(cra);
//        }
//        headerSecond.add(StringUtils.EMPTY);
//        //第三层
//        List<String> headerThird = new LinkedList<>();
//        headerThird.add(StringUtils.EMPTY);
//        cra.setFirstRow(2);
//        cra.setLastRow(2);
//        mergeStart = 0;
//        for (Map.Entry<Integer,IssueType> issueTypeEntry : negativeIssueTypeMap.entrySet()){
//            mergeStart++;
//            cra.setFirstColumn(mergeStart);
//            headerThird.add(issueTypeEntry.getValue().getWeight().toString());
//            for (int i = 1;i<priorityMap.size();i++){
//                mergeStart++;
//                headerThird.add(StringUtils.EMPTY);
//            }
//            cra.setLastColumn(mergeStart);
//            artifactSheet.addMergedRegion(cra);
//        }
//
//        //第四层
//        List<String> headerFourth = new LinkedList<>();
//        headerFourth.add(StringUtils.EMPTY);
//        for (Map.Entry<Integer,IssueType> issueTypeEntry : negativeIssueTypeMap.entrySet()){
//            for (Map.Entry<Integer,Priority> priorityEntry : priorityMap.entrySet()){
//                headerFourth.add(priorityEntry.getValue().getPriorityName() + "_"+priorityEntry.getValue().getScore());
//            }
//        }
//        headerFourth.add(StringUtils.EMPTY);
//        cra.setFirstRow(0);
//        cra.setLastRow(3);
//        cra.setFirstColumn(0);
//        cra.setLastColumn(0);
//        artifactSheet.addMergedRegion(cra);
//        cra.setFirstColumn(1+negativeIssueTypeMap.size()*priorityMap.size());
//        cra.setLastColumn(1+negativeIssueTypeMap.size()*priorityMap.size());
//        artifactSheet.addMergedRegion(cra);
//        HSSFRow headFirst = artifactSheet.createRow(0);
//        for (int i = 0; i < headerFirst.size(); i++){
//            HSSFCell cell = headFirst.createCell(i);
//            cell.setCellValue(headerFirst.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//        HSSFRow headSecond = artifactSheet.createRow(1);
//        for (int i = 0; i < headerSecond.size(); i++){
//            HSSFCell cell = headSecond.createCell(i);
//            cell.setCellValue(headerSecond.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//        HSSFRow headThird = artifactSheet.createRow(2);
//        for (int i = 0; i < headerThird.size(); i++){
//            HSSFCell cell = headThird.createCell(i);
//            cell.setCellValue(headerThird.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//        HSSFRow headFourth = artifactSheet.createRow(3);
//        for (int i = 0; i < headerFourth.size(); i++){
//            HSSFCell cell = headFourth.createCell(i);
//            cell.setCellValue(headerFourth.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//
//        //数据
//        for(int i = 0;i < result.size();i++){
//            HSSFRow row = artifactSheet.createRow(4+i);
//            HSSFCell nameCell = row.createCell(0);
//            nameCell.setCellValue(result.get(i).getPictureName());
//            nameCell.setCellStyle(nameStyle);
//            int j = 1;
//            for (Map.Entry<Integer,IssueType> issueTypeEntry : negativeIssueTypeMap.entrySet()){
//                for (Map.Entry<Integer,Priority> priorityEntry : priorityMap.entrySet()){
////                    result.get(i).getNagativeMap().get(issueTypeEntry.getKey()).get(priorityEntry.getKey())
//                    HSSFCell cell = row.createCell(j++);
//                    cell.setCellStyle(cellStyle);
//                    cell.setCellValue(Optional.ofNullable(result.get(i)).map(ExcelArtifactRowModel::getNagativeMap).map(o->o.get(issueTypeEntry.getKey())).map(o->o.get(priorityEntry.getKey())).orElse(0L));
//                }
//            }
//            HSSFCell cell = row.createCell(j);
//            cell.setCellStyle(cellStyle);
//            cell.setCellValue(Optional.ofNullable(result.get(i).getNegativeScore()).orElse((double)0));
//        }
//        artifactSheet.setColumnWidth(0,50*256);
//        for (int i =1;i< negativeIssueTypeMap.size()*priorityMap.size()+2;i++){
//            artifactSheet.setColumnWidth(i,12 * 256);
//        }
//
//    }
//
//    public static void getExcelStatisticSheetByModel(HSSFWorkbook workbook,HSSFSheet artifactSheet, ExcelModel model, LinkedHashMap<Integer,IssueType> positiveIssueTypeMap){
//        HSSFFont font = workbook.createFont();
//        font.setFontHeightInPoints((short)10);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//
//        HSSFCellStyle nameStyle = workbook.createCellStyle();
//        nameStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        nameStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        nameStyle.setWrapText(true);
//        nameStyle.setFont(font);
//
//        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cellStyle.setFont(font);
//
//        HSSFCellStyle headerStyle = workbook.createCellStyle();
//        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        headerStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
//        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setFont(font);
//
//        double totalWeight = positiveIssueTypeMap.values().stream().mapToDouble(IssueType::getWeight).sum();
//        //第一层
//        List<String> headerFirst = new LinkedList<>();
//        headerFirst.add("pictureName");
//        for (Map.Entry<Integer,IssueType> issueTypeEntry:positiveIssueTypeMap.entrySet()){
//            headerFirst.add(issueTypeEntry.getValue().getTypeName());
//        }
//
//        headerFirst.add("Artifact");
//        headerFirst.add("Score");
//        //第二层
//        List<String> headerSecond = new LinkedList<>();
//        headerSecond.add(StringUtils.EMPTY);
//        for (Map.Entry<Integer,IssueType> issueTypeEntry:positiveIssueTypeMap.entrySet()){
//            headerSecond.add(model.getPositiveScoreProportion()*issueTypeEntry.getValue().getWeight()/totalWeight+"%");
//        }
//        headerSecond.add((100-model.getPositiveScoreProportion())+"%");
//        headerSecond.add(StringUtils.EMPTY);
//
//        CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 0);
//        artifactSheet.addMergedRegion(cra);
//        cra.setFirstColumn(positiveIssueTypeMap.size()+2);
//        cra.setLastColumn(positiveIssueTypeMap.size()+2);
//        artifactSheet.addMergedRegion(cra);
//        HSSFRow headFirst = artifactSheet.createRow(0);
//        for (int i = 0; i < headerFirst.size(); i++){
//            HSSFCell cell = headFirst.createCell(i);
//            cell.setCellValue(headerFirst.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//        HSSFRow headSecond = artifactSheet.createRow(1);
//        for (int i = 0; i < headerSecond.size(); i++){
//            HSSFCell cell = headSecond.createCell(i);
//            cell.setCellValue(headerSecond.get(i));
//            cell.setCellStyle(headerStyle);
//        }
//        //数据
//        for(int i = 0;i < model.getModelList().size();i++){
//            HSSFRow row = artifactSheet.createRow(2+i);
//            HSSFCell nameCell = row.createCell(0);
//            nameCell.setCellValue(model.getModelList().get(i).getPictureName());
//            nameCell.setCellStyle(nameStyle);
////            row.createCell(0).setCellValue(model.getModelList().get(i).getPictureName());
//            int j = 1;
//            for (Map.Entry<Integer,IssueType> issueTypeEntry : positiveIssueTypeMap.entrySet()){
//                HSSFCell cell = row.createCell(j++);
//                cell.setCellStyle(cellStyle);
//                cell.setCellValue(Optional.ofNullable(model.getModelList().get(i)).map(ExcelArtifactRowModel::getPositiveMap).map(o->o.get(issueTypeEntry.getKey())).orElse((double)0));
//            }
//            HSSFCell cell1 = row.createCell(j++);
//            cell1.setCellStyle(cellStyle);
//            cell1.setCellValue(Optional.ofNullable(model.getModelList().get(i).getNegativeScore()).orElse((double)0));
//            HSSFCell cell2 = row.createCell(j);
//            cell2.setCellStyle(cellStyle);
//            cell2.setCellValue(Optional.ofNullable(model.getModelList().get(i).getTotalScore()).orElse((double)0));
//        }
//        artifactSheet.setColumnWidth(0,50*256);
//        for (int i =1;i< positiveIssueTypeMap.size()+2;i++){
//            artifactSheet.setColumnWidth(i,12 * 256);
//        }
//
//    }
//}
