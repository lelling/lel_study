package com.excel.easyexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.excel.easyexcel.model.MultiLineHeadModel;
import com.excel.easyexcel.model.SimpleModel;
import com.lel.utils.json.GsonUtils;

public class EasyExcelTest {

	public static void main(String[]args) throws Exception{
		
//		writeMultipleWithoutHead();
//		noModelMultipleSheet();
		
//		writeSimpleModel();
//		writeMultiLineHeadModel();
		
//		readSimpleModelFromSheet();
		
		readMultiLineHeadModelFromSheet();
		
	}
	
	/**
	 * 读多行头excel
	 */
	public static void readMultiLineHeadModelFromSheet() throws Exception{
		InputStream inputStream = new FileInputStream(new File("D://writeMultiLineHeadModel.xlsx"));
		try {
			ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX,null,
					new AnalysisEventListener<MultiLineHeadModel>() {

						@Override
						public void invoke(MultiLineHeadModel model, AnalysisContext context) {
							System.out.println("当前sheet序号：" + context.getCurrentSheet().getSheetNo()
									+ "--" + context.getCurrentSheet().getSheetName()
									+ "---当前行" + context.getCurrentRowNum()+"- data = " + GsonUtils.toJson(model));
						}

						@Override
						public void doAfterAllAnalysed(AnalysisContext context) {
							// TODO Auto-generated method stub
							
						}
				
					});
			reader.read(new Sheet(1, 3, MultiLineHeadModel.class));
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				inputStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 读单行头excel
	 */
	public static void readSimpleModelFromSheet() throws Exception{
		InputStream inputStream = new FileInputStream(new File("D://writeSimpleModel.xlsx"));
		try {
			ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX,null,
					new AnalysisEventListener<SimpleModel>() {

						@Override
						public void invoke(SimpleModel simpleModel, AnalysisContext context) {
							System.out.println("当前sheet序号：" + context.getCurrentSheet().getSheetNo()
									+ "--" + context.getCurrentSheet().getSheetName()
									+ "---当前行" + context.getCurrentRowNum()+"- data = " + GsonUtils.toJson(simpleModel));
						}

						@Override
						public void doAfterAllAnalysed(AnalysisContext context) {
							// TODO Auto-generated method stub
							
						}
				
					});
			reader.read(new Sheet(1, 1, SimpleModel.class));
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				inputStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 读无头excel
	 */
	public static void noModelMultipleSheet() throws Exception{
		InputStream inputStream = new FileInputStream(new File("D://withoutHead.xlsx"));
		try {
			ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX,null,
					new AnalysisEventListener<List<String>>() {

						@Override
						public void invoke(List<String> object, AnalysisContext context) {
							System.out.println("当前sheet序号：" + context.getCurrentSheet().getSheetNo()
									+ "--" + context.getCurrentSheet().getSheetName()
									+ "--列数：" + object.size()  + "---当前行" + context.getCurrentRowNum()+"- data = " + object);
						}

						@Override
						public void doAfterAllAnalysed(AnalysisContext context) {
							// TODO Auto-generated method stub
							
						}
				
					});
			reader.read();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				inputStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 根据模板类生成excel
	 * @throws Exception
	 */
	public static void writeMultiLineHeadModel() throws Exception {
		OutputStream out = new FileOutputStream("D://writeMultiLineHeadModel.xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		Sheet sheet1 = new Sheet(1, 0, MultiLineHeadModel.class);
		sheet1.setSheetName("示例");
		
		List<MultiLineHeadModel> data = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			MultiLineHeadModel dbModel = new MultiLineHeadModel();
			dbModel.setId(i);
			dbModel.setName("n"+i);
			dbModel.setSize(i+3);
			dbModel.setInPerMonth(new BigDecimal(i+4));
			dbModel.setCreateDate(new Date(2018-1900, 9, i));
			data.add(dbModel);
		}
		
		writer.write(data, sheet1);
		
		writer.finish();
	}
	
	/**
	 * 根据模板类生成excel
	 * @throws Exception
	 */
	public static void writeSimpleModel() throws Exception {
		OutputStream out = new FileOutputStream("D://writeSimpleModel.xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		Sheet sheet1 = new Sheet(1, 0, SimpleModel.class);
		sheet1.setSheetName("示例");
		
		List<SimpleModel> data = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			SimpleModel dbModel = new SimpleModel();
			dbModel.setId(i);
			dbModel.setName("n"+i);
			dbModel.setSize(i+3);
			dbModel.setInPerMonth(new BigDecimal(i+4));
			dbModel.setCreateDate(new Date(2018-1900, 9, i));
			data.add(dbModel);
		}
		
		writer.write(data, sheet1);
		
		writer.finish();
	}
	
	/**
	 * 写无头excel
	 * @throws Exception
	 */
	public static void writeMultipleWithoutHead() throws Exception {
		OutputStream out = new FileOutputStream("D://withoutHead.xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
		Sheet sheet1 = new Sheet(1, 0);
		sheet1.setSheetName("示例");
		
		List<List<String>> data = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> item = new ArrayList<>(3);
			item.add("item0" + i);
			item.add("item1" + i);
			item.add("item2" + i);
			data.add(item);
		}
		writer.write0(data, sheet1);
		
		Sheet sheet2 = new Sheet(2, 0);
		sheet2.setSheetName("空sheet");
		writer.write0(null, sheet2);
		
		writer.finish();
	}
}
