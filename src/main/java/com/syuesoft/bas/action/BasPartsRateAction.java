package com.syuesoft.bas.action;

public class BasPartsRateAction
{

    /*
     * BasPartsRateService logic = null; BasPartsRate baspartsrate = new
     * BasPartsRate();
     *//**
     * 配件加价系数删除
     * 
     * @return
     */
    /*
     * public String del() { try { logic.del(baspartsrate); } catch (Exception
     * e) { } return null; }
     *//**
     * 配件加价系数修改
     * 
     * @return
     */
    /*
     * public String update() { try { logic.update(baspartsrate); } catch
     * (Exception e) {
     * 
     * } return null; }
     *//**
     * 配件加价系数增加
     * 
     * @return
     */
    /*
     * public String add() { try { logic.add(baspartsrate); } catch (Exception
     * e) { return ERROR; } return SUCCESS; }
     *//**
     * 配件加价系数查询
     * 
     * @return
     */
    /*
     * public String search() { try { BasPartsRate bcc =
     * logic.search(baspartsrate); if (bcc != null) { List list = new
     * ArrayList(); Map map = new HashMap(); map.put("partsId",
     * bcc.getPartsId()); map.put("partsStyle", bcc.getPartsStyle());
     * map.put("partsStartamount", bcc.getPartsStartamount());
     * map.put("partsEndamount", bcc.getPartsEndamount()); map.put("partsRate",
     * bcc.getPartsRate()); map.put("partsRemark", bcc.getPartsRemark());
     * list.add(map); Gson gson = new Gson(); Json json = new Json();
     * json.setRows(list); json.setTotal(1); super.writeJson(json); } else {
     * Gson gson = new Gson(); Msg msg = new Msg(); msg.setMsg("error");
     * msg.setSuccess(false); super.writeJson(msg); } } catch (Exception e) { }
     * return null; }
     *//**
     * 配件加价系数显示
     * 
     * @return
     */
    /*
     * public String view() { try { List<BasPartsRate> baspartsraterecord =
     * logic.getAllByPage(baspartsrate);//记录当前页面配件加价率 List<BasPartsRate>
     * baspartsrateall = logic.findAll(); //记录全部页面加价率 Gson gson = new Gson();
     * Json json = new Json(); json.setTotal(baspartsrateall.size());
     * json.setRows(baspartsraterecord); super.writeJson(json); } catch
     * (Exception e) { e.printStackTrace(); return null; } return null; }
     * 
     * public BasPartsRate getModel() { return baspartsrate; }
     * 
     * public BasPartsRateService getLogic() { return logic; }
     * 
     * public void setLogic(BasPartsRateService logic) { this.logic = logic; }
     */
}
