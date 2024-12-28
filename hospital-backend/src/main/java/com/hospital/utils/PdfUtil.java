package com.hospital.utils;

import cn.hutool.core.date.DateUtil;
import com.hospital.entity.vo.user.ArrangeDoctorVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.hospital.entity.po.Orders;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * PDF工具类
 *
 * @author: ShanZhu
 * @date: 2023-11-15
 */
public class PdfUtil {

    /**
     * 导出病例单
     *
     * @param response http response
     * @param order 挂号单信息
     * @throws Exception 异常
     */
    public static void exportPatientOrder(HttpServletResponse response, Orders order) throws Exception {

        //告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        //设置中文
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        //创建一个文档
        Document document = new Document(PageSize.A4);
        //创建第一个段落
        Paragraph titleParagraph = new Paragraph();
        //支持中文
        titleParagraph.setFont(new Font(bfChinese, 20, Font.NORMAL));
        //设置居中显示
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.add("医生诊断单");
        //创建第二个段落
        Paragraph tipsParagraph = new Paragraph();
        tipsParagraph.setFont(new Font(bfChinese, 10, Font.NORMAL));
        tipsParagraph.setAlignment(Element.ALIGN_CENTER);
        tipsParagraph.setLeading(tipsParagraph.getTotalLeading() + 10);
        tipsParagraph.add("打印时间：" + DateUtil.now());

        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        // 打开文档
        document.open();
        //设置文档标题
        document.addTitle("医院");
        //设置文档作者
        document.addAuthor("xxxx");
        document.addCreationDate();
        //设置关键字
        document.addKeywords("iText");
        document.addLanguage("中文");
        //增加段落进入文档
        document.add(titleParagraph);
        document.add(tipsParagraph);
        //表格
        PdfPTable tableMessage = new PdfPTable(4);
        tableMessage.setSpacingBefore(8f);
        tableMessage.setSpacingAfter(8f);
        //设置表格无边框
        tableMessage.getDefaultCell().setBorder(0);
        //设置表格宽度
        tableMessage.setTotalWidth(new float[]{30, 120, 30, 120});
        tableMessage.addCell(new Paragraph("姓名", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPName(), FontChinese));
        tableMessage.addCell(new Paragraph("性别", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPGender(), FontChinese));
        tableMessage.addCell(new Paragraph("年龄", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPAge() + " 岁", FontChinese));
        tableMessage.addCell(new Paragraph("单号", FontChinese));
        tableMessage.addCell(String.valueOf(order.getOId()));
        tableMessage.addCell(new Paragraph("日期", FontChinese));
        tableMessage.addCell(order.getOEnd());
        tableMessage.addCell(new Paragraph("电话", FontChinese));
        tableMessage.addCell(order.getPatient().getPPhone());
        document.add(tableMessage);

        //病情表格
        PdfPTable tableOrder = new PdfPTable(1);
        //设置表格无边框
        tableOrder.getDefaultCell().setBorder(0);
        tableOrder.setSpacingBefore(30f);
        tableOrder.setSpacingAfter(10f);

        PdfPCell cell1 = new PdfPCell(new Paragraph("症状", new Font(bfChinese, 14, Font.NORMAL)));
        cell1.setFixedHeight(25);
        cell1.setBorder(0);
        PdfPCell cell2 = new PdfPCell(new Paragraph(order.getORecord(), new Font(bfChinese, 10, Font.NORMAL)));
        cell2.setFixedHeight(30);
        cell2.setBorder(0);
        cell2.setPaddingLeft(10);
        PdfPCell cell3 = new PdfPCell(new Paragraph("检查项目及价钱", new Font(bfChinese, 14, Font.NORMAL)));
        cell3.setFixedHeight(25);
        cell3.setBorder(0);
        PdfPCell cell4 = new PdfPCell(new Paragraph(order.getOCheck(), new Font(bfChinese, 10, Font.NORMAL)));
        cell4.setFixedHeight(30);
        cell4.setBorder(0);
        cell4.setPaddingLeft(10);
        PdfPCell cell5 = new PdfPCell(new Paragraph("药物及价钱", new Font(bfChinese, 14, Font.NORMAL)));
        cell5.setFixedHeight(25);
        cell5.setBorder(0);
        PdfPCell cell6 = new PdfPCell(new Paragraph(order.getODrug(), new Font(bfChinese, 10, Font.NORMAL)));
        cell6.setFixedHeight(30);
        cell6.setBorder(0);
        cell6.setPaddingLeft(10);
        PdfPCell cell7 = new PdfPCell(new Paragraph("诊断/医生意见", new Font(bfChinese, 14, Font.NORMAL)));
        cell7.setFixedHeight(25);
        cell7.setBorder(0);
        PdfPCell cell8 = new PdfPCell(new Paragraph(order.getOAdvice(), new Font(bfChinese, 10, Font.NORMAL)));
        cell8.setFixedHeight(100);
        cell8.setBorder(0);
        cell8.setPaddingLeft(10);

        tableOrder.addCell(cell1);
        tableOrder.addCell(cell2);
        tableOrder.addCell(cell3);
        tableOrder.addCell(cell4);
        tableOrder.addCell(cell5);
        tableOrder.addCell(cell6);
        tableOrder.addCell(cell7);
        tableOrder.addCell(cell8);
        document.add(tableOrder);

        //设置pdf底部版权说明
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        cb.beginText();
        cb.setFontAndSize(bf, 11);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "该报告单仅供参考", 300, 40, 0);
        cb.setFontAndSize(bf, 13);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "版权医院所有", 300, 20, 0);
        cb.endText();

        document.close();
    }

        /**
         * 导出医生排班表
         *
         * @param response http response
         * @param ArrangeDoctors 值班信息列表
         * @throws Exception 异常
         */
        public static void exportDoctorSchedule(HttpServletResponse response, List<ArrangeDoctorVo> ArrangeDoctors) throws Exception {
            // 检查数据是否为空
            if (ArrangeDoctors == null || ArrangeDoctors.isEmpty()) {
                throw new IllegalArgumentException("排班信息列表为空");
            }

            System.out.println("要写入数据"+ArrangeDoctors);


            //告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/pdf");
            //设置中文
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            Font fontBig = new Font(bfChinese, 14, Font.NORMAL);

            //创建一个文档
            Document document = new Document(PageSize.A4);
            // 创建文档并写入响应流
            try {
                //创建第一个段落
                Paragraph titleParagraph = new Paragraph();
                //支持中文
                titleParagraph.setFont(new Font(bfChinese, 20, Font.NORMAL));
                //设置居中显示
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.add("医生排班表");
                //创建第二个段落
                Paragraph tipsParagraph = new Paragraph();
                tipsParagraph.setFont(new Font(bfChinese, 10, Font.NORMAL));
                tipsParagraph.setAlignment(Element.ALIGN_CENTER);
                tipsParagraph.setLeading(tipsParagraph.getTotalLeading() + 10);
                tipsParagraph.add("打印时间：" + DateUtil.now());

                PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

                // 创建文档并写入响应流
                PdfWriter.getInstance(document, response.getOutputStream());

                if (document==null){
                    throw new RuntimeException("创建pdf文档失败");
                }
                // 打开文档
                document.open();
                if (!document.isOpen()){
                    throw new RuntimeException("打开pdf文档失败");
                }
                //设置文档标题
                document.addTitle("医生排班表");
                //设置文档作者
                document.addAuthor("lisiwen");
                document.addCreationDate();
                //设置关键字
                document.addKeywords("iText");
                document.addLanguage("中文");
                //增加段落进入文档
                document.add(titleParagraph);
                document.add(tipsParagraph);

                // 添加表格
                PdfPTable table = new PdfPTable(4); // 4列
                table.setWidthPercentage(100);
                table.setWidths(new float[]{2, 2, 3, 3}); // 列宽比例
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                System.out.println("添加表格成功");

                // 添加表头
                table.addCell(new PdfPCell(new Paragraph("值班时间",fontBig)));
                table.addCell(new PdfPCell(new Paragraph("医生ID", fontBig)));
                table.addCell(new PdfPCell(new Paragraph("医生姓名", fontBig)));
                table.addCell(new PdfPCell(new Paragraph("医生科室", fontBig)));

                System.out.println("添加表头成功");


                // 填充数据
                for (ArrangeDoctorVo ArrangeDoctor : ArrangeDoctors) {
                    table.addCell(new PdfPCell(new Paragraph(ArrangeDoctor.getArTime(), FontChinese)));
                    table.addCell(new PdfPCell(new Paragraph(String.valueOf(ArrangeDoctor.getDId()), FontChinese)));
                    table.addCell(new PdfPCell(new Paragraph(ArrangeDoctor.getDName(), FontChinese)));
                    table.addCell(new PdfPCell(new Paragraph(ArrangeDoctor.getDSection(), FontChinese)));
                }

                System.out.println("填充数据成功");
                // 添加表格到文档
                document.add(table);
                System.out.println("添加表格到文档成功");

                // 添加底部版权说明
                Paragraph footer = new Paragraph("该排班表仅供参考，版权归医院所有。", new Font(bfChinese, 10, Font.ITALIC));
                footer.setAlignment(Element.ALIGN_CENTER);
                footer.setSpacingBefore(20f);
                document.add(footer);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("生成医生排班表失败：" + e.getMessage());
            } finally {
                if (document != null) {
                    document.close();
                }
            }
        }


}
