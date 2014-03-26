package com.syuesoft.fbk.vo;

public class FbkDcserveyNameVo
{

    private String dcNameId; // 项目id

    private String serveyName; // 项目名称

    private String evaluate;

    private String score;

    private String memo; // 回访项目备注

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getDcNameId()
    {
        return dcNameId;
    }

    public void setDcNameId(String dcNameId)
    {
        this.dcNameId = dcNameId;
    }

    public String getServeyName()
    {
        return serveyName;
    }

    public void setServeyName(String serveyName)
    {
        this.serveyName = serveyName;
    }

    public String getEvaluate()
    {
        return evaluate;
    }

    public void setEvaluate(String evaluate)
    {
        this.evaluate = evaluate;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

}
