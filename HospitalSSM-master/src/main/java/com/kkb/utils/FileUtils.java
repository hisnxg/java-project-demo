package com.kkb.utils;

import com.kkb.bean.Cpregister;
import com.kkb.bean.Doctor;
import com.kkb.bean.Drug;
import com.kkb.bean.Hosregister;
import com.kkb.constant.ProjectConstant;
import com.kkb.myenum.HospitalClosePriceStatusEnum;
import com.kkb.myenum.HosrStateEnum;
import com.kkb.vo.hospitalsettle.resp.HospitalSettleResp;
import com.kkb.vo.hospzation.resp.BeHospitalResp;
import com.kkb.vo.registration.req.BeHosregisterResp;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author mj
 * @create 2021-03-22 16:16
 * @describe:
 */
public class FileUtils {

    /**
     * 创建doctorExcel
     * @param
     * @param doctorList
     * @return
     * @throws IOException
     */
    public static String createDoctorExcel(String[] titles,List<Doctor> doctorList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("doctor");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < doctorList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                Doctor doctor = doctorList.get(i);
                if ("医生编号".equals(title)){
                    cell.setCellValue(doctor.getdId());
                }else if ("医生姓名".equals(title)){
                    cell.setCellValue(doctor.getdName());
                }else if ("入院时间".equals(title)){
                    cell.setCellValue(DateUtils.dateToStr(doctor.getdIntime()));
                }else if ("所属科室".equals(title)){
                    cell.setCellValue(doctor.getdKeshi());
                }
            }
        }


//        //设置值
//        cell.setCellValue(new Date());
//        //创建单元格样式
//        HSSFCellStyle style=workbook.createCellStyle();
//        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//        cell.setCellStyle(style);
//        //设置保留2位小数--使用Excel内嵌的格式
//        HSSFCell cell1 = row.createCell(1);
//        cell1.setCellValue(12.3456789);
//        style=workbook.createCellStyle();
//        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//        cell1.setCellStyle(style);
//        //设置货币格式--使用自定义的格式
//        HSSFCell cell2 = row.createCell(2);
//        cell2.setCellValue(12345.6789);
//        style=workbook.createCellStyle();
//        style.setDataFormat(workbook.createDataFormat().getFormat("￥#,##0"));
//        cell2.setCellStyle(style);
//        //设置百分比格式--使用自定义的格式
//        HSSFCell cell3 = row.createCell(3);
//        cell3.setCellValue(0.123456789);
//        style=workbook.createCellStyle();
//        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
//        cell3.setCellStyle(style);
//        //设置中文大写格式--使用自定义的格式
//        HSSFCell cell4 = row.createCell(4);
//        cell4.setCellValue(12345);
//        style=workbook.createCellStyle();
//        style.setDataFormat(workbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
//        cell4.setCellStyle(style);
//        //设置科学计数法格式--使用自定义的格式
//        HSSFCell cell5 = row.createCell(5);
//        cell5.setCellValue(12345);
//        style=workbook.createCellStyle();
//        style.setDataFormat(workbook.createDataFormat().getFormat("0.00E+00"));
//        cell5.setCellStyle(style);

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.DOCTOR_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.DOCTOR_FILE_NAME;
    }

    /**
     * 创建hospitalExcel
     * @param titles
     * @param hospitalList
     * @return
     * @throws IOException
     */
    public static String createHospitalExcel(String[] titles,List<BeHospitalResp> hospitalList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("hospital");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < hospitalList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                BeHospitalResp beHospitalResp = hospitalList.get(i);
                if ("病历号".equals(title)){
                    cell.setCellValue(beHospitalResp.getBehId());
                }else if ("姓名".equals(title)){
                    cell.setCellValue(beHospitalResp.getHosrName());
                }else if ("床位号".equals(title)){
                    cell.setCellValue(beHospitalResp.getBehPatbed());
                }else if ("联系电话".equals(title)){
                    cell.setCellValue(beHospitalResp.getHosrPhone());
                }else if ("押金".equals(title)){
                    cell.setCellValue(beHospitalResp.getBehAntecedent().toString());
                }else if ("主治医生".equals(title)) {
                    cell.setCellValue(beHospitalResp.getDoctorName());
                }else if ("入院时间".equals(title)) {
                    cell.setCellValue(beHospitalResp.getBehIntime());
                }else if ("科室".equals(title)) {
                    cell.setCellValue(beHospitalResp.getKeShi());
                }else if ("状态".equals(title)) {
                    Integer behState = beHospitalResp.getHosrState();
                    cell.setCellValue(HosrStateEnum.intToStr(behState));
                }
            }
        }

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.HOSPITAL_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.HOSPITAL_FILE_NAME;
    }

    /**
     * 创建hospitalExcel
     * @param titles
     * @param hospitalSettleRespList
     * @return
     * @throws IOException
     */
    public static String createHospitalSettleExcel(String[] titles,List<HospitalSettleResp> hospitalSettleRespList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("hospitalSettle");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < hospitalSettleRespList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                HospitalSettleResp resp = hospitalSettleRespList.get(i);

                if ("病历号".equals(title)){
                    cell.setCellValue(resp.getBehId());
                }else if ("姓名".equals(title)){
                    cell.setCellValue(resp.getHosrName());
                }else if ("押金".equals(title)){
                    cell.setCellValue(resp.getBehAntecedent().toString());
                }else if ("当前余额".equals(title)){
                    cell.setCellValue(resp.getBalance());
                }else if ("状态".equals(title)){
                    if (resp.getBehState()!=null) {
                        cell.setCellValue(HospitalClosePriceStatusEnum.intToStr(resp.getBehState()));
                    }else {
                        cell.setCellValue("");
                    }
                }
            }
        }

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.SETTLE_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.SETTLE_FILE_NAME;
    }

    /**
     * 创建drugExcel
     * @param titles
     * @param drugList
     * @return
     * @throws IOException
     */
    public static String createDrugExcel(String[] titles,List<Drug> drugList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("drug");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < drugList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                Drug drug = drugList.get(i);
                if ("药品编号".equals(title)){
                    cell.setCellValue(drug.getDrId());
                }else if ("药品名称".equals(title)){
                    cell.setCellValue(drug.getDrName());
                }else if ("药品类型".equals(title)){
                    cell.setCellValue(drug.getDrType());
                }
            }
        }

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.DRUG_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.DRUG_FILE_NAME;
    }

    /**
     * 创建cpRegisterExcel
     * @param titles
     * @param cpregisterList
     * @return
     * @throws IOException
     */
    public static String createCPRegisterExcel(String[] titles,List<Cpregister> cpregisterList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("cpregister");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < cpregisterList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                Cpregister cpregister = cpregisterList.get(i);
                if ("病历号".equals(title)){
                    cell.setCellValue(cpregister.getHosrId());
                }else if ("病人姓名".equals(title)){
                    cell.setCellValue(cpregister.getHosrName());
                }else if ("收费项目".equals(title)){
                    cell.setCellValue(cpregister.getChapName());
                }else if ("收费金额".equals(title)){
                    cell.setCellValue(cpregister.getChapMoney());
                }else if ("检查日期".equals(title)){
                    cell.setCellValue(DateUtils.dateToStrMiao(cpregister.getCprTime()));
                }
            }
        }

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.CPREGISTER_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.CPREGISTER_FILE_NAME;
    }

    //创建hosregisterExcel
    public static String hosregisterExcel(String[] titles,List<BeHosregisterResp> beHosregisterRespList) throws IOException {
        //创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建Excel工作表对象
        HSSFSheet sheet = workbook.createSheet("hosregisterExcel");
        //创建标题栏
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //创建单元格
            HSSFCell cell=titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建正文数据
        for (int i = 0; i < beHosregisterRespList.size(); i++) {
            //创建行
            HSSFRow row = sheet.createRow(i+1);
            for (int j = 0; j < titles.length; j++) {
                //创建单元格
                HSSFCell cell=row.createCell(j);
                String title = titles[j];
                BeHosregisterResp beHosregisterResp = beHosregisterRespList.get(i);
                if ("病历号".equals(title)){
                    cell.setCellValue(beHosregisterResp.getHosrId());
                }else if ("姓名".equals(title)){
                    cell.setCellValue(beHosregisterResp.getHosrName());
                }else if ("身份证号".equals(title)){
                    cell.setCellValue(beHosregisterResp.getHosrIdcar());
                }else if ("社保号".equals(title)){
                    cell.setCellValue(beHosregisterResp.getHosrMedical());
                }else if ("联系电话".equals(title)){
                    cell.setCellValue(beHosregisterResp.getHosrPhone());
                }
            }
        }

        //文档输出
        FileOutputStream out = new FileOutputStream(ProjectConstant.DOWNLOAD_PATH+ProjectConstant.HOSREGISTER_FILE_NAME);
        workbook.write(out);
        out.close();

        return ProjectConstant.HOSREGISTER_FILE_NAME;
    }

}
