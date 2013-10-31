package com.cary.stq;

public class STQConstant {
    //0:canceled,1:apply, 2:verified,3:testing,4:reporting,5:csComfirmed,6:printed,9:reject,99:csDisagree
    //取消申请
    public final static String STATUS_CANCEL = "0";
    //申请草稿，未提交
    public final static String STATUS_APPPLY_DRAFT = "1";
    //申请提交（待审核）
    public final static String STATUS_APPPLY_SUBMIT = "1";
    //审核通过（）
    public final static String STATUS_VERIFIED = "2";
    //审核未通过
    public final static String STATUS_REJECTED = "9";

    //测试中
    public final static String STATUS_TESTING = "3";
    //正在出报告
    public final static String STATUS_REPORTING = "4";
    //客户已确认
    public final static String STATUS_CSCOMFIRMED = "5";
    //已打印
    public final static String STATUS_PRINTED = "6";
    //流程结束
    public final static String STATUS_FINISH = "99";

    public static String getStatusDesc (String code){
        String result = "";
        switch (code){
            case ("1"):{
                result = "草稿";
                break;
            }
            case ("2"):{
                result = "待审批";
                break;
            }
            case ("3"):{
                result = "测试中";
                break;
            }
            case ("4"):{
                result = "报告中";
                break;
            }
            case ("5"):{
                result = "待客户确认";
                break;
            }
            case ("6"):{
                result = "修改中";
                break;
            }
            case ("7"):{
                result = "客户已确认";
                break;
            }
            case ("8"):{
                result = "待打印";
                break;
            }case ("9"):{
                result = "结束";
                break;
            }
        }
        return result;
    }

}
