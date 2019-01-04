package events;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChooseFileActionListener implements ActionListener{

    /**
     * 监听事件
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹:"+file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件:"+file.getAbsolutePath());
        }
        try {
            InputStream is = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            if (workbook == null) {
                System.err.println("未找到文件！");
                return;
            }
            ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = workbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // 对于每个sheet，读取其中的每一行
                for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow == null) continue;
                    ArrayList<String> curarr=new ArrayList<String>();
                    for(int columnNum = 0 ; columnNum<xssfRow.getLastCellNum() ; columnNum++){
                        XSSFCell cell = xssfRow.getCell(columnNum);

                        curarr.add(getValue(cell));
                    }
                    ans.add(curarr);
                }
            }

            for (List<String> list: ans) {
                for (String cell: list) {
                    System.out.println(cell);
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static String getValue(XSSFCell xssfRow) {
        if(xssfRow==null){
            return "---";
        }
        System.out.println(xssfRow.getCellType() + " " + CellType.BOOLEAN);
        if (xssfRow.getCellType().equals(CellType.BOOLEAN)) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType().equals(CellType.NUMERIC)) {
            double cur=xssfRow.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if(Double.parseDouble(longVal + ".0") == cur)
                inputValue = longVal;
            else
                inputValue = cur;
            return String.valueOf(inputValue);
        } else if(xssfRow.getCellType().equals(CellType.BLANK )|| xssfRow.getCellType().equals(CellType.ERROR)){
            return "---";
        } else if (xssfRow.getCellType().equals(CellType.FORMULA)) {
            try {
                return String.valueOf(xssfRow.getNumericCellValue());
            } catch (Exception e) {
                return "--";
            }
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

}
