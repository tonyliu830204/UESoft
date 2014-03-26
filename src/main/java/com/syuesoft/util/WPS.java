package com.syuesoft.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.syuesoft.base.vo.BaseBeanVo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.BooleanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WPS
{
  public static String webappsCharacteristic = "webapps/";
  public static String webinfCharacteristic = "WEB-INF/";

  public static String getRootPath()
  {
    String pathtem = null;
    URL ut = Thread.currentThread().getContextClassLoader().getResource("");
    pathtem = ut.toString();
    pathtem = pathtem.replace("%20", " ");
    pathtem = pathtem.substring(6, pathtem.length());
    pathtem = pathtem.substring(0, pathtem.indexOf(webinfCharacteristic));
    return pathtem;
  }

  public static String createWPS(String path, String html, String fileName)
    throws Exception
  {
    Document doc = Jsoup.parse(html);
    Element body = doc.body();
    Elements headElements = body.getElementsByTag("thead");
    List headList = null;
    if ((headElements != null) && (headElements.size() > 0)) {
      Element headElement = headElements.get(0);
      Elements spanElements = headElement.select("tr > th > span");
      if ((spanElements != null) && (spanElements.size() > 0)) {
        headList = new ArrayList();
        for (int j = 0; j < spanElements.size(); j++) {
          Element spanElement = spanElements.get(j);
          String spanText = spanElement.text();
          headList.add(spanText);
        }
      }
    }

    Elements bodyElements = body.getElementsByTag("tbody");
    List bodyList = null;
    if ((bodyElements != null) && (bodyElements.size() > 0)) {
      Element bodyElement = bodyElements.get(0);
      Elements trElements = bodyElement.select("tr");
      if ((trElements != null) && (trElements.size() > 0)) {
        bodyList = new ArrayList();
        for (int i = 0; i < trElements.size(); i++) {
          Element trElement = trElements.get(i);
          Elements tdElements = trElement.select("td");
          List tdList = null;
          if ((tdElements != null) && (tdElements.size() > 0)) {
            tdList = new ArrayList();
            for (int j = 0; j < tdElements.size(); j++) {
              Element tdElement = tdElements.get(j);
              String tdText = tdElement.text();
              tdList.add(tdText);
            }
          }
          if (tdList != null) {
            bodyList.add(tdList);
          }
        }
      }
    }

    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet1 = wb.createSheet("new sheet");

    if ((headList != null) && (headList.size() > 0)) {
      HSSFRow row = sheet1.createRow(0);
      for (int i = 0; i < headList.size(); i++) {
        HSSFCell cell = row.createCell(i);
        cell.setCellValue((String)headList.get(i));
      }
    }

    if ((bodyList != null) && (bodyList.size() > 0)) {
      for (int i = 0; i < bodyList.size(); i++) {
        int rownumber = i + 1;
        HSSFRow row = sheet1.createRow(rownumber);
        List tdList = (List)bodyList.get(i);
        if (tdList != null) {
          for (int j = 0; j < tdList.size(); j++) {
            HSSFCell cell = row.createCell(j);
            cell.setCellValue((String)tdList.get(j));
          }
        }
      }

    }

    File f = new File(path);
    if (!f.exists())
      f.mkdirs();
    path = path + File.separator + fileName;
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fileOut = new FileOutputStream(file);
    wb.write(fileOut);
    fileOut.flush();
    fileOut.close();

    return path;
  }

  public static Map<String, Object> readExcel(File filePath, String _fieldNames)
  {
    List list = new ArrayList();
    Map map = new HashMap();
    try {
      String fileName = filePath.getName();
      if ((fileName.endsWith(".xls")) || (fileName.endsWith(".xlsx"))) {
        paretExcel(filePath, _fieldNames, list, map);
        if (!map.containsKey("success")) {
          Json json = new Json();
          json.setTotal(((Map)list.get(0)).size());
          json.setRows(list);
          map.put("message", "解析成功");
          map.put("object", JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd", new SerializerFeature[0]));
          map.put("success", Boolean.valueOf(true));
        }
      } else {
        map.put("message", "文件类型不匹配");
        map.put("object", null);
        map.put("success", Boolean.valueOf(false));
        return map;
      }
    } catch (Exception e) {
      e.printStackTrace();
      map.put("message", "解析出错");
      map.put("object", null);
      map.put("success", Boolean.valueOf(false));
    }
    return map;
  }

  private static void paretExcel(File filePath, String _fieldNames, List<Map<String, Object>> listRow, Map<String, Object> map) throws Exception {
    Workbook wb = null;
    Sheet sheet = null;
    Row row = null;
    Cell cell = null;
    String[] fieldNames = _fieldNames.split(",");
    InputStream is = new FileInputStream(filePath);
    if (filePath.getName().endsWith(".xls"))
      wb = new HSSFWorkbook(is);
    else if (filePath.getName().endsWith(".xlsx")) {
      wb = new XSSFWorkbook(is);
    }
    if (wb != null)
    {
      int sheetNum = 1;
      if (sheetNum > 0)
        for (int numSheet = 0; numSheet < sheetNum; numSheet++) {
          sheet = wb.getSheetAt(numSheet);
          if (sheet == null) {
            continue;
          }
          int HSSFRowNum = sheet.getLastRowNum();
          if (HSSFRowNum <= 0)
            continue;
          for (int rowNum = 0; rowNum <= HSSFRowNum; rowNum++) {
            row = sheet.getRow(rowNum);
            if (row == null) {
              continue;
            }
            int HSSFCellNum = row.getLastCellNum();
            if (HSSFCellNum > 0)
            {
              Map HSSFRowMap = new HashMap();
              for (int cellNum = 0; cellNum <= fieldNames.length; cellNum++) {
                cell = row.getCell(cellNum);
                if (cell == null) {
                  continue;
                }
                getXlsValue(cell, HSSFRowMap, cellNum, fieldNames);
              }

              listRow.add(HSSFRowMap);
            } else {
              map.put("message", "选择的列与Excel列个数不匹配");
              map.put("object", null);
              map.put("success", Boolean.valueOf(false));
              return;
            }
          }
        }
    }
  }

  private static void getXlsValue(Cell cell, Map<String, Object> HSSFRowMap, int cellNum, String[] fieldNames) throws Exception
  {
    String reault = null;
    if ((cell != null) && (!"".equals(cell.toString()))) {
      switch (cell.getCellType()) {
      case 1:
        reault = cell.getStringCellValue();
        break;
      case 0:
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          Date date = cell.getDateCellValue();
          if (date != null)
            reault = new SimpleDateFormat("yyyy-MM-dd").format(date);
          else
            reault = "";
        }
        else {
          reault = new DecimalFormat("0").format(cell.getNumericCellValue());
        }
        break;
      case 2:
        if (!cell.getStringCellValue().equals(""))
          reault = cell.getCellFormula();
        else {
          reault = "";
        }
        break;
      case 3:
        reault = "";
        break;
      case 5:
        reault = "";
        break;
      case 4:
        reault = cell.getBooleanCellValue() ? "true" : "false";
        break;
      default:
        reault = "";
      }
    }
    HSSFRowMap.put(fieldNames[(fieldNames.length - cellNum - 1)], reault);
  }

  public static List<Object> paert(List<Object[]> downloadlist, Object bean) throws Exception {
    int rows_ = 0;
    int index = 0;
    List list = null;
    if ((downloadlist != null) && (downloadlist.size() > 0)) {
      Class cls = bean.getClass();
      list = new ArrayList();
      for (Object[] objs : downloadlist) {
        int rows = Integer.parseInt(objs[2].toString());
        if (rows_ == rows) {
          paret(cls, bean, objs);
        } else {
          if (rows_ != 0)
            list.add(bean);
          rows_ = rows;
          bean = (BaseBeanVo)cls.newInstance();
          paret(cls, bean, objs);
        }
        if (downloadlist.size() - 1 == index)
          list.add(bean);
        index++;
      }
    }
    return list;
  }

  private static void paret(Class<?> cls, Object bean, Object[] objs) throws Exception, NoSuchMethodException {
    Field[] fields = cls.getDeclaredFields();
    for (Field field : fields) {
      String fieldName = field.getName();
      if (objs[0].toString().equals(fieldName)) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getMethodName = "get" + firstLetter + fieldName.substring(1);
        String setMethodName = "set" + firstLetter + fieldName.substring(1);
        Method getMethod = cls.getMethod(getMethodName, new Class[0]);
        Method setMethod = cls.getMethod(setMethodName, new Class[] { getMethod.getReturnType() });
        Class returnType = getMethod.getReturnType();
        if (returnType == Long.class) { setMethod.invoke(bean, new Object[] { Long.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? Long.valueOf(objs[1].toString()).longValue() : 0L) }); break; }
        if (returnType == Integer.class) { setMethod.invoke(bean, new Object[] { Integer.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? Integer.valueOf(objs[1].toString()).intValue() : 0) }); break; }
        if (returnType == Short.class) { setMethod.invoke(bean, new Object[] { Short.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? Short.valueOf(objs[1].toString()).shortValue() : 0) }); break; }
        if (returnType == Double.class) { setMethod.invoke(bean, new Object[] { Double.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? Double.valueOf(objs[1].toString()).doubleValue() : 0.0D) }); break; }
        if (returnType == Float.class) { setMethod.invoke(bean, new Object[] { Float.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? Float.valueOf(objs[1].toString()).floatValue() : 0.0F) }); break; }
        if (returnType == Boolean.class) { setMethod.invoke(bean, new Object[] { Boolean.valueOf((objs[1] != null) && (!"".equals(objs[1].toString())) ? BooleanUtils.toBoolean(objs[1].toString()) : false) }); break; }
        setMethod.invoke(bean, new Object[] { (objs[1] != null) && (!"".equals(objs[1].toString())) ? returnType.cast(objs[1].toString()) : null });
        break;
      }
    }
  }
}