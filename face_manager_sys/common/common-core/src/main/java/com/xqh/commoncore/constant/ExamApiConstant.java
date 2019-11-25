package com.xqh.commoncore.constant;

/**
 * 考试常量
 *
 * @author ye
 * @date 2019/3/17 12:08
 */
public class ExamApiConstant {
    /**
     * 待考试
     */
    public static final int STATUS_NOT_EXAM= 0;

    /**
     * 待考试
     */
    public static final int STATUS_NOT_EXAM_ONLINE= 5;

    /**
     * 待重考
     */
    public static final int STATUS_NEED_MAKEUP= 30;

    /**
     * 考试中
     */
    public static final int STATUS_EXAM = 50;

    /**
     * 通过
     */
    public static final int STATUS_PASS= 100;

    /**
     * 未合格
     */
    public static final int STATUS_NOT_PASS = 200;

    /**
     * 完成状态集合
     */
    public static final int[] FINISH_STATUS_ARR = new int[]{STATUS_NEED_MAKEUP,STATUS_PASS,STATUS_NOT_PASS};

    public static final int SEARCH_WRONG = 0;

    public static final int SEARCH_ALL = 1;

    /**
     * 未合格
     */
    public static final String GRADE_NOT_PASS= "未合格";

    /**
     * 通过
     */
    public static final String GRADE_PASS= "通过";

    /**
     * 优秀
     */
    public static final String GRADE_EXCELLENT = "优秀";




    /**
     * 题型-选择
     */
    public static final String QUESTION_TYPE_CHOICE="选择";

    /**
     * 题型-多选
     */
    public static final String QUESTION_TYPE_MULTI="多选";

    /**
     * 题型-填空
     */
    public static final String QUESTION_TYPE_FILLING="填空";

    /**
     * 题型-判断
     */
    public static final String QUESTION_TYPE_JUDGE="判断";

    /**
     * 导入题目表头
     */

    protected static final String[] QUESTION_HEAD = new String[]{"serial","type","content","op_one","op_two","op_three","op_four","answer"};

    public static final String[] QUESTION_CHOICE_HEAD = new String[]{"serial","category","content","op_one","op_two","op_three","op_four","answer"};

    public static final String[] QUESTION_MULTI_HEAD = new String[]{"serial","category","content","op_one","op_two","op_three","op_four","answer"};

    public static final String[] QUESTION_FILLING_HEAD = new String[]{"serial","category","content","answer"};

    public static final String[] QUESTION_JUDGE_HEAD = new String[]{"serial","category","content","answer"};

    public static final String JUDGE_TRUE = "对";

    public static final String JUDGE_FALSE = "错";

    public static final String HEAD_TYPE = "type";

    public static final String ANSWER_JUDGE_TRUE = "T";

    public static final String ANSWER_JUDGE_FALSE = "F";

    public static final int QUESTION_CHOICE_TYPE = 0;

    public static final int QUESTION_MULTI_TYPE = 1;

    public static final int QUESTION_FILLING_TYPE = 2;

    public static final int QUESTION_JUDGE_TYPE = 3;

    public static final String QUESTION_FIELD_SERIAL = "序号";

    public static final String QUESTION_FIELD_CATEGORY = "类型";
    public static final String QUESTION_FIELD_CONTENT = "题干";
    public static final String QUESTION_FIELD_OPTION_A = "选项A";
    public static final String QUESTION_FIELD_OPTION_B = "选项B";
    public static final String QUESTION_FIELD_OPTION_C = "选项C";
    public static final String QUESTION_FIELD_OPTION_D = "选项D";
    public static final String QUESTION_FIELD_OPTION_E = "选项E";
    public static final String QUESTION_FIELD_OPTION_F = "选项F";
    public static final String QUESTION_FIELD_ANSWER = "正确答案";

    public static final String EXCEL_SERIAL = "serial";
    public static final String EXCEL_TYPE = "type";
    public static final String EXCEL_CATEGORY = "category";
    public static final String EXCEL_CONTENT = "content";
    public static final String EXCEL_OPTION_A = "op_one";
    public static final String EXCEL_OPTION_B = "op_two";
    public static final String EXCEL_OPTION_C = "op_three";
    public static final String EXCEL_OPTION_D = "op_four";
    public static final String EXCEL_OPTION_E = "op_five";
    public static final String EXCEL_OPTION_F = "op_six";
    public static final String EXCEL_ANSWER = "answer";

    public static final String QUESTION_CHOICE = "单选题";
    public static final String QUESTION_MULTI = "多选题";
    public static final String QUESTION_FILLING = "填空题";
    public static final String QUESTION_JUDGE = "判断题";


}
